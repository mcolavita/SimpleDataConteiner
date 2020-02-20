import java.util.Iterator;

public interface SecureDataConteiner<E> {
	/*Overview: Tipo di dato modificabile che rapresenta una specie di Data Storage per la
		        memorizzazione e la condivisione di dati
	
	
	 Typical Element : [<User1,{El1_1,El2_1,...Eln_1}>, <User2,{El1_2,El2_2,...ElN_2}>, ... <UserN,{El1_NEl2_N, ..., ElN_NLEV}>]
	   					è l'insieme degli utenti presenti nel Data Storage con le rispettive collezioni di oggetti */
	
	public void createUser(String Id, String passw)
		throws ImpossibleToPerform, NullPointerException;
	/*
	 REQUIRES: Id != null && passw !=null
	 THROWS: se Id == null o passw == null lancia una NullPointerExeption,
	 		 se l'utente è gia presente lancia una ImpossibleToPerform
	 MODIFIES: this
	 EFFECTS: Aggiunge l'utente all'insieme degli utenti <==> Id non e' presente nella collezione
	 */
	
	public void removeUser(String Id, String passw)
		throws ImpossibleToPerform, NullPointerException;
	/*
	 REQUIRES: Id != null && passw != null && (Id deve appartenere alla collezione)
	 THROWS: se Id == null o passw == null lancia una NullPointerExeption,
	         se l'utente non eiste lancia una ImpossibleToPerform
	 MODIFIES: this
	 EFFECTS: Rimuove l'utente dall'insieme degli utenti  <==> Id e' presente nella collezione
	 */
	
	public int getSize(String Owner, String passw)
		throws UnauthorizedAccessException, NullPointerException;
	/*
	 //Propietario
	 REQUIRES: (Owner && passw != null) && Owner deve appartenere all'insieme degli utenti 
	 THROWS: se Owner == null o passw == null lancia una NullPointerExeption,
	 		 se il nome utente (Owner) o la password non sono corretti lancia una UnauthorizedAccessException
	 MODIFIES: -
	 EFFECTS: Restituisce il numero di oggetti E di cui Owner e' proprietario
	 */
	
	public boolean put(String Owner, String passw, E Data)
		throws UnauthorizedAccessException, NullPointerException;
	/*
	 REQUIRES: (Owner && passw != null) && Owner deve appartenere all'insieme degli utenti
	 			&& Data != null
	 THROWS: se Owner == null o passw == null o Data == null lancia una NullPointerExeption,
	         se il nome utente (Owner) o la password non sono corretti lancia una UnauthorizedAccessException
	 MODIFIES: -
	 EFFECTS: inserisce Data nella collezione di Owner e restituisce True se l'operazione è avvenuta con successo, False altrimenti
	 */	
	
	public E get(String Owner, String passw, E data)
		throws UnauthorizedAccessException, NullPointerException, NoFindData ;
	/*

	 REQUIRES: (Owner && passw != null) && Owner deve appartenere all'insieme degli utenti
	 			&& Data != null && Data deve appartenere all'insieme degli oggetti di Owner
	 THROWS: se Owner == null o passw == null o Data == null lancia una NullPointerExeption,
	         se il nome utente (Owner) o la password non sono corretti lancia una UnauthorizedAccessException,
	         se l'oggetto cercato non è presente nella collezione dell'utente lancia una NoFindData
	 MODIFIES: -
	 EFFECTS: restituisce una copia di Data <==> Data esiste
	 */
	
	public E remove(String Owner, String passw, E data)
		throws UnauthorizedAccessException, NullPointerException,NoFindData;
	/*
	 REQUIRES: (Owner && passw != null) && Owner deve appartenere all'insieme degli utenti
	 			&& Data != null && Data deve appartenere all'insieme degli oggetti di Owner
	 THROWS: se Owner == null o passw == null o Data == null lancia una NullPointerExeption,
	         se il nome utente (Owner) o la password non sono corretti lancia una UnauthorizedAccessException
	 MODIFIES: this
	 EFFECTS: rimuove l'oggetto Data dall'insieme di oggetti di Owner <==> Data esiste 
	 */
	
	public void copy(String Owner, String passw, E data)
		throws UnauthorizedAccessException, NullPointerException, NoFindData;
	/*
	  REQUIRES: (Owner && passw != null) && Owner deve appartenere all'insieme degli utenti
	 			&& Data != null && Data deve appartenere all'insieme degli oggetti di Owner
	  THROWS: se Owner == null o passw == null o Data == null lancia una NullPointerExeption,
	          se il nome utente (Owner) o la password non sono corretti lancia una UnauthorizedAccessException
	  MODIFIES: -
	  EFFECTS: copia data nella collezione di oggetti di Owner <==> Data esiste 
	 */
	
	public void share(String Owner, String passw, String Other, E data)
		throws UnauthorizedAccessException, NullPointerException, NoFindData, ImpossibleToPerform;
	/*
	REQUIRES: (Owner && passw && Other != null) && Owner deve appartenere all'insieme degli utenti
	 			&& Data != null && Data deve appartenere all'insieme degli oggetti di Owner
	THROWS: se Owner == null o passw == null o data == null o Other == null lancia una NullPointerExeption,
	        se Owner o Other non sono presenti lancia una ImpossibleToPerform,
	        se il nome utente (Owner) o la password non sono corretti lancia una UnauthorizedAccessException,
	        se data non è presente nella collezione di Owner lancia una NoFindData
	MODIFIES: -
	EFFECTS: Copia data nella collezione di Other <==> data esiste e Other è presente nell'insieme degli utenti
	 */

	public Iterator<E> getIterator(String Owner, String passw)
			throws UnauthorizedAccessException, NullPointerException;
	/*
	REQUIRES: Owner!= null && passw !=null
	THROWS: se Owner == null o passw == null lancia una NullPointerExeption,
			se il nome utente (Owner) o la password non sono corretti lancia una UnauthorizedAccessExeption
	MODIFIES: -
	EFFECTS: Restituisce
	*/
}
