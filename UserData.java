import java.util.Vector;

//CLASSE UTENTE CONTENENTE UN VECTOR PER LE COLLEZIONI DI OGGETTI
public class UserData<E> {
	private String Name;
	private String passw;
	private Vector<E> collection;


	/*

	Overview: Tipo di dato modificabile che rappresenta un utente che rappresenta un singolo utente avente
			  un Id una password ed una collezione di oggetti.

	Typical element [(Id,passw,(el1,el2,...,elN))]
					rappresenta un singolo utente con una propria collezione di oggetti

	AF c(x) = {c.getName(),c.getPassw(),c.getCollection()}


	IR(c) =  //Nome utente non nullo
			c.getName()!= null

			//Password non nulla
			c.getPassw()!=null

			//Ogni dato della collezione diverso da null
			for all i<=0 && i<c.getCollection.size() c.getCollection.get(i) != null
	 */




	public UserData(String Name, String passw) {
		this.Name=Name;
		this.passw=passw;
		this.collection= new Vector<E>();

	}
	public String getName() {
		return Name;
	}
	
	public String getPassw() {
		return passw;
	}
	
	public Vector<E> getCollection(){
		return collection;
	}
	

	
	public int getSizeCollection() {
		return collection.size();
	}
	
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		if(obj == null) 
			return false;
		if(getClass() != obj.getClass())
			return false;
		UserData other = (UserData) obj;
		if(Name == null) {
			if(other.Name != null)
				return false;
		}
		else if(!Name.equals(other.Name))
			return false;
		if(passw != other.passw)
				return false;
		return true;
	}
}
