import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class MysecureDataConteniner2<E extends Comparable<E>> implements SecureDataConteiner <E> {

    //Implementazione con HashMap
    private HashMap<User, Vector<E>> user;

    /*

    AF: c(x) = {for all i <=0, i<c.size  (c.k,c.user.get(k)) | for each k:key in user}

    IR(c) = c.user!=null

           //Utenti non nulli
           (for each k : key in user => c.k != null )

           //Ogni dato dell'utente è diverso da null
           for each k: key in user => c.user.get(k)!=null

           //Ogni utente è distinto nella tabella
           for each k,j : key  t.c k != j in hash => k.getName() != j.getName()

     */

    //Inizializzazione della HashMap
    public MysecureDataConteniner2(){
        this.user= new HashMap<>();
    }

    //Aggiunge il nuovo utente all'insieme degli utenti
    public void createUser(String Id, String passw) throws ImpossibleToPerform, NullPointerException {
        if(Id == null || passw == null)
            throw new NullPointerException();
        User new_user = findName(Id);
        if(new_user == null) {
            new_user = new User(Id, passw);
            this.user.put(new_user,new Vector<>());
        }
        else
            throw new ImpossibleToPerform("L'utente è gia stato inserito");

    }

    // Rimuove un untente dall'insieme degli utenti
    public void removeUser(String Id, String passw) throws ImpossibleToPerform, NullPointerException {
        if(Id == null || passw == null)
            throw new NullPointerException();
        User new_user = findUser(Id,passw);
        if(!(this.user.containsKey(new_user)))
            throw new ImpossibleToPerform("L'utente non è presente");
        else
            this.user.remove(new_user);
    }

    // Resituisce la grandezza della collezione di Owner
    public int getSize(String Owner, String passw) throws UnauthorizedAccessException, NullPointerException {
        if(Owner == null || passw == null)
            throw new NullPointerException();
        User new_user = findUser(Owner,passw);
        if(!(this.user.containsKey(new_user)))
            throw new UnauthorizedAccessException("Login errato");
        else return  user.get(new_user).size();
    }

    //Restituisce True se l'inserimento è andato a buon fine, lancia eccezione altrimenti
    public boolean put(String Owner, String passw, E Data) throws UnauthorizedAccessException, NullPointerException {
        if(Owner == null || passw == null || Data == null)
            throw new NullPointerException();
        User new_user = findUser(Owner,passw);
        if(!(this.user.containsKey(new_user)))
            throw new UnauthorizedAccessException("Login errato");
        user.get(new_user).add(Data);
        return true;
    }

    //Restituisce una copia di data, se data compare nella collezione di Owner
    public E get(String Owner, String passw, E data) throws UnauthorizedAccessException, NullPointerException, NoFindData {
        if (Owner == null || passw == null || data == null)
            throw new NullPointerException();
        User new_user = findUser(Owner, passw);
        if(!this.user.containsKey(new_user))
            throw new UnauthorizedAccessException("Login errato");
        Vector<E> new_vect = user.get(new_user);
        if(findData(new_vect,data)==-1)
            throw new NoFindData("Dato non presente nella collezione");
        int index = findData(new_vect,data);
        return user.get(new_user).get(index);
    }

    //Rimuove data, se data compare nella collezione di Owner
    public E remove(String Owner, String passw, E data) throws UnauthorizedAccessException, NullPointerException, NoFindData {
        if(Owner == null || passw == null || data == null)
            throw new NullPointerException();
        User new_user = findUser(Owner,passw);
        if(!this.user.containsKey(new_user))
            throw new UnauthorizedAccessException("Login errato");
        Vector<E> new_vect = user.get(new_user);
        if(findData(new_vect,data)==-1)
            throw new NoFindData("Dato non presente nella collezione");
        int index = findData(new_vect,data);
        return user.get(new_user).remove(index);
    }

    // L'oggetto data viene copiato nella collezione di Owner
    public void copy(String Owner, String passw, E data) throws UnauthorizedAccessException, NullPointerException, NoFindData {
        if(Owner == null || passw == null || data == null)
            throw new NullPointerException();
        User new_user = findUser(Owner,passw);
        if(!this.user.containsKey(new_user))
            throw new  UnauthorizedAccessException("Login errato");
        Vector<E> new_vect = user.get(new_user);
        if(findData(new_vect,data) == -1)
            throw new NoFindData("Dato non presente nella collezione");
        user.get(new_user).add(data);
    }


    public void share(String Owner, String passw, String Other, E data) throws UnauthorizedAccessException, NullPointerException, NoFindData, ImpossibleToPerform {
        if(Owner == null || passw == null || Other == null || data == null)
            throw new NullPointerException();
        User new_user = findUser(Owner,passw);
        if(!(this.user.containsKey(new_user)))
            throw new UnauthorizedAccessException("Login errato");
        int index = findData(user.get(new_user),data);
        if(index == -1)
            throw new NoFindData("Dato non presente nella collezione");
        User x = findName(Other);
        if( x== null)
            throw new ImpossibleToPerform("L'utente non è presente nella collezione");
        user.get(x).add(data);
    }


    public Iterator<E> getIterator(String Owner, String passw) throws UnauthorizedAccessException, NullPointerException {
        if(Owner == null || passw == null)
            throw new NullPointerException();
        User new_user = findUser (Owner,passw);
        if(!this.user.containsKey(new_user))
            throw new UnauthorizedAccessException("Login errato");
        User it = findName(Owner);
        MyIterator<E> new_it = new MyIterator<E>(user.get(it));
        return new_it;
    }


    //Restituisce l'indice della posizione in cui si trova l'oggetto, -1 altrimenti
    public int findData(Vector<E> vect, E data) {
        boolean finder = false;
        int index = -1;
        int i = 0;
        while(finder==false && i < vect.size()){
            if(vect.get(i).compareTo(data) == 0)
                finder=true;
            else i++;
        }
        if(finder==true)
            index=i;
        return index;
    }

    //Resituisce l'utente se si trova nella collezione, null altrimenti
    public User findUser(String Other,String passw){
        Iterator<User> keys= user.keySet().iterator();
        while(keys.hasNext()){
            User x = keys.next();
            if(x.getName().equals(Other))
                if(x.getPassw().equals(passw))
                return x;
        }
        return null;
    }


    public User findName(String Other){
        Iterator<User> keys= user.keySet().iterator();
        while(keys.hasNext()){
            User x = keys.next();
            if(x.getName().equals(Other))
                return x;
        }
        return null;
    }
}
