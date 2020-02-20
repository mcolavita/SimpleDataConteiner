import java.util.Iterator;
import java.util.Vector;

public class MySecureDataContainer<E extends Comparable<E>> implements SecureDataConteiner<E> {




	//Implementazione con Vector<User<E>>
	private Vector <UserData<E>> users;

/*
	Abstract Function: c(x) = {for all i, i>=0 && i<c.users.size() ( c.users.get(i),c.users.get(i).getCollection()) }

	IR(c) =  //Utenti unici
	 		(for all i, j, 0<=i && 0<=j && i!=j => c.users.get(i).getName() != c.users.get(j).getName())

	 		//Utenti non nulli
			(for all i , i>=0 && i<c.users.size() => c.users.get(i) != null)

			//Vector non nullo
			c.users!= null

 */

	//Costruttore: inizializza il vettore degli utenti
	public MySecureDataContainer (){
		this.users= new Vector<UserData<E>>();
	}
	
	//Aggiunge un nuovo utente se questo non compare nell'insieme
	public void createUser(String Id, String passw) throws ImpossibleToPerform, NullPointerException {
		if(Id== null || passw == null)
			throw new NullPointerException();
		if(this.find(Id))
			throw new ImpossibleToPerform("L'utente e' gia' presente");
		else this.users.add(new UserData<>(Id, passw));
	} 
		
	//Rimuove un utente dall'insieme degli utenti
	public void removeUser(String Id, String passw) throws ImpossibleToPerform,NullPointerException {
		if(Id== null || passw == null)
			throw new NullPointerException();
		if(!this.find(Id))
			throw new ImpossibleToPerform("L'utente cercato non e' presente");
		else{
			int i=0;
			while(!this.users.get(i).getName().equals(Id))
				i++;
			this.users.remove(i);
		}
	}

	//Restituisce il numero di oggetti di Owner
	public int getSize(String Owner, String passw) throws UnauthorizedAccessException,NullPointerException {
		if(Owner== null || passw == null)
			throw new NullPointerException();
		if(!this.LoginUser(Owner,passw))
				throw new UnauthorizedAccessException("Login errato");
		int i=0;
		while(!(users.get(i).getName().equals(Owner)) && i < users.size())
			i++;
		return this.users.get(i).getSizeCollection();
	}
	
	//Restituisce true se l'inserimento è andato a buon fine, lancia eccezione altrimenti 
	public boolean put(String Owner, String passw, E Data) throws UnauthorizedAccessException, NullPointerException {
		if(Owner== null || passw == null || Data == null)
			throw new NullPointerException();
		if(!this.LoginUser(Owner, passw))
			throw new UnauthorizedAccessException("Login errato");
		int i=0;
		while(!(users.get(i).getName().equals(Owner)) && i < users.size())
			i++;
		users.get(i).getCollection().add(Data);
		return true;
	}

	//Resituisce una copia di data, se data compare nella collezione di Owner
	public E get(String Owner, String passw, E data) throws UnauthorizedAccessException, NullPointerException, NoFindData {
		if(Owner== null || passw == null || data == null)
			throw new NullPointerException();
		if(!this.LoginUser(Owner, passw))
			throw new UnauthorizedAccessException("Login errato");
		if(!this.findData(Owner,data))
			throw new NoFindData("Non esiste l'oggetto cercato");
		else {
			int i = 0;
			while (!(users.get(i).getName().equals(Owner)))
				i++;
			int j = 0;
			while (users.get(i).getCollection().get(j).compareTo(data) != 0)
				j++;
			return users.get(i).getCollection().get(j);
		}
	}

	// Rimuove data se data compare nella collezione di Owner
	public E remove(String Owner, String passw, E data) throws UnauthorizedAccessException, NullPointerException,NoFindData {
		if(Owner == null || passw == null || data == null )
			throw new NullPointerException();
		if(!this.LoginUser(Owner,passw))
			throw new UnauthorizedAccessException("Login errato");
		if(!this.findData(Owner,data))
			throw new NoFindData("Non esiste l'oggetto cercato");
		int i=0;
		while(!(users.get(i).getName().equals(Owner)))
			i++;
		int j=0;
		while(users.get(i).getCollection().get(j).compareTo(data)!=0)
			j++;
		return users.get(i).getCollection().remove(j);
	}

	// L'oggetto data viene copiato nella collezione di Owner
	public void copy(String Owner, String passw, E data) throws UnauthorizedAccessException, NullPointerException, NoFindData {
		if(Owner == null || passw == null || data == null )
			throw new NullPointerException();
		if(!this.LoginUser(Owner,passw))
			throw new UnauthorizedAccessException("Login errato");
		if(!this.findData(Owner,data))
			throw new NoFindData("Non esiste l'oggetto cercato");
		int i = 0;
		while(!(users.get(i)).getName().equals(Owner))
			i++;
		users.get(i).getCollection().add(data);
		return;
	}

	//Owner condivide con Other l'oggetto data
	public void share(String Owner, String passw, String Other, E data) throws UnauthorizedAccessException, NullPointerException, NoFindData,ImpossibleToPerform {
		if(Owner == null || passw == null || Other == null || data == null)
			throw new NullPointerException();
		if(!this.LoginUser(Owner,passw))
			throw new UnauthorizedAccessException("Login errato");
		if(!this.findData(Owner,data))
			throw new NoFindData("Il dato da condividere non è presente");
		if(!this.find(Other))
			throw new ImpossibleToPerform("L'utente con cui condividere il dato non è presente");
		int i=0;
		while(!(users.get(i)).getName().equals(Other))
			i++;
		users.get(i).getCollection().add(data);
		return;
	}

	public Iterator<E> getIterator (String Owner, String passw) throws UnauthorizedAccessException, NullPointerException{
		if(Owner == null || passw == null)
			throw new  NullPointerException();
		if(!LoginUser(Owner,passw))
			throw new UnauthorizedAccessException("Login errato");
		int i = 0;
		while(!this.users.get(i).getName().equals(Owner))
			i++;
		MyIterator<E> new_it = new MyIterator<E>(users.get(i).getCollection());
		return new_it;
	}

	//Restituisce true se Id compare nella collezione
	private boolean find(String Id) {
		boolean finder=false;
		for(UserData<E> user : users) {
			if(Id.equals(user.getName()))
				finder=true;
		}
		return finder;
	}
	
	//Restituisce true se il login viene effettuato correttamente
	private boolean LoginUser(String Owner, String passw) {
		boolean finder=false;
		for (UserData<E> user : users) {
			if(Owner.equals(user.getName())) {
				if(passw.equals(user.getPassw()))
					finder=true;
			}
		}
		return finder;
	}

	//Restituisce true se data compare tra gli oggetti di Owner
	private boolean findData(String Owner, E Data) {
		boolean finder=false;
		if(!find(Owner))
			return finder;
		int i=0;
		while(!(users.get(i).getName().equals(Owner)))
			i++;
		int j;
		for(j=0; j < users.get(i).getSizeCollection(); j++) {
			if(users.get(i).getCollection().get(j).compareTo(Data)==0)
				finder=true;
		}
		return finder;
	}

}
