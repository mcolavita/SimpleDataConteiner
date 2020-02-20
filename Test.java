import java.util.Iterator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        System.out.println("Benvenuto su SecureDataContainer"+"\n"+
            "Digitare 1 o 2 per scegliere tra due strutture dati\n"+
            "1. HashMap\n"+ "2. Vector\n");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String inputLine = "";
        try{
            inputLine = reader.readLine();
        }
        catch(IOException e){
            System.out.println(e);
            System.exit(1);
        }
        SecureDataConteiner b;
        if (inputLine.equals("1"))
            b = new MysecureDataConteniner2<Integer>();
        else
            b = new MySecureDataContainer<Integer>();
        Integer idx = new Integer(0);
        // inserimento di 40 utenti
        System.out.println("inserimento di 10 utenti nel datacontainer");
        for (idx = 0 ; idx < 10 ; idx++) {
            try {
                b.createUser("user_"+idx.toString(), idx.toString()+idx.toString()+idx.toString());
                System.out.println("Utente: user_"+idx.toString() + "creato con successo");
            } catch (ImpossibleToPerform e) {
                System.out.println("Username già esistente\n");
            }
        }

        //provo a creare un utente che già esiste
        System.out.println("\nProva: creazione di un utente che già esiste");
        try {
            b.createUser("user_0", "000");
            System.out.println("Utente: user_0" + "creato con successo\n");
        } catch (ImpossibleToPerform e) {
            System.out.println("ImpossibleToPerform Username già esistente\n");
        }

        //provo a creare un utente con nome esistente e password diversa
        System.out.println("Prova: creazione di un utente con username già esistente e password diversa");
        try {
            b.createUser("user_0", "001");
            System.out.println("Utente: user_0" + "creato con successo\n");
        } catch (ImpossibleToPerform e) {
            System.out.println("ImpossibleToPerform Username già esistente\n");
        }

        // prova corretto funzionamento remove
        System.out.println("Prova: rimozione user_0");
        try {
            b.removeUser("user_0","000");
            System.out.println("Rimosso con successo\n");
        } catch (ImpossibleToPerform e) {
            System.out.println("Utente o passowrd sbagliate");
        }

        System.out.println("Prova: rimozione utente non esistente");
        try {
            b.removeUser("user_45","454545");
            System.out.println("Rimosso con successo\n");
        } catch (ImpossibleToPerform e) {
            System.out.println("Utente o passowrd sbagliate\n");
        }


        System.out.println("Prova: inserimento di dati nei 10 utenti");
        for(idx=0; idx<10;idx++) {
            try {
                b.put("user_"+idx.toString(),idx.toString()+idx.toString()+idx.toString(),idx);
                System.out.println(" Inserimento corretto in user_"+idx.toString());
            } catch (UnauthorizedAccessException e) {
                System.out.println("login errato!, user_"+idx.toString()+" non presente");
            }
        }

        System.out.println("\nProva: aggiungo un elemento in user_1, user_4 e user_5");
        try {
            b.put("user_1", "111", 50);
            System.out.println("Inserimento corretto");
        } catch (UnauthorizedAccessException e){
            System.out.println("login errato!\n");
        }
        try {
            b.put("user_4", "444", 544);
            System.out.println("Inserimento corretto");
        } catch (UnauthorizedAccessException e){
            System.out.println("login errato!\n");
        }
        try {
            b.put("user_5", "555", 84);
            System.out.println("Inserimento corretto\n");
        } catch (UnauthorizedAccessException e){
            System.out.println("login errato\n!");
        }


        System.out.println("Prova: dimensione collezione utenti");
        for(idx = 1; idx < 10 ; idx ++){
            try{
                System.out.println("Numero oggetti user_"+idx.toString()+":"+b.getSize("user_"+idx.toString(),idx.toString()+idx.toString()+idx.toString()));
            }
            catch (UnauthorizedAccessException e) {
                System.out.println("login errato!\n");
            }
        }


        System.out.println("\nProva: restituisce il dato 1 dell'user_1");
        try {
            System.out.println(b.get("user_1", "111", 1)+" (corretto!) \n");
        }
        catch (NoFindData e){
            System.out.println("Non esiste il dato\n");
        }
        catch (UnauthorizedAccessException e ){
            System.out.println("login errato!\n");
        }


        System.out.println("Prova: non trova il dato 44 in user_3 ");
        try {
            System.out.println(b.get("user_3", "333", 44)+" (corretto!) \n");
        }
        catch (NoFindData e){
            System.out.println("Non esiste il dato 44\n");
        }
        catch (UnauthorizedAccessException e ){
            System.out.println("login errato!");
        }


        System.out.println("Prova: non trova l'utente_60 con dato 5");
        try {
            System.out.println(b.get("user_60", "333", 5)+" (corretto!) \n");
        }
        catch (NoFindData e){
            System.out.println("Non esiste il dato 5\n");
        }
        catch (UnauthorizedAccessException e ){
            System.out.println("login errato!\n");
        }




        System.out.println("Prova: utente e dato corretto, ma password sbagliata");
        try {
            System.out.println(b.get("user_1", "545104", 50)+" (corretto!) \n");
        }
        catch (NoFindData e){
            System.out.println("Non esiste il dato 50\n");
        }
        catch (UnauthorizedAccessException e ){
            System.out.println("login errato!\n");
        }

        System.out.println("Prova: copia il dato 84 in user_5");
        try{
            b.copy("user_5","555",84);
            System.out.println("Copia corretta\n");
        }
        catch (UnauthorizedAccessException e){
            System.out.println("login errato!\n");
        }
        catch (NoFindData e){
            System.out.println("Non esiste il dato 84\n");
        }


        System.out.println("Prova: copia di un dato non esistente in user_5");
        try{
            b.copy("user_5","555",888);
        }
        catch (UnauthorizedAccessException e){
            System.out.println("login errato!\n");
        }
        catch (NoFindData e){
            System.out.println("Non esiste il dato 888\n");
        }


        System.out.println("Prova: login errato da parte di user_5 ");
        try{
            b.copy("user_5","554",84);
        }
        catch (UnauthorizedAccessException e){
            System.out.println("login errato!\n");
        }
        catch (NoFindData e){
            System.out.println("Non esiste il dato 84\n");
        }

        System.out.println("Prova: condivisione di 84 di user_5 con user_9");
        try{
            b.share("user_5","555","user_9",84);
            System.out.println("condivisione andata a buon fine!\n");
        }
        catch(UnauthorizedAccessException e){
            System.out.println("login errato!\n");
        }
        catch (NoFindData e ){
            System.out.println("Dato non esistente\n");
        }
        catch (ImpossibleToPerform e){
            System.out.println("Utente non esistente\n");
        }



        System.out.println("Prova: condivisione di 84 di user_5 con utente non esistente");
        try{
            b.share("user_5","555","user_10",84);
            System.out.println("condivisione andata a buon fine!\n");
        }
        catch(UnauthorizedAccessException e){
            System.out.println("login errato!\n");
        }
        catch (NoFindData e ){
            System.out.println("Dato non esistente\n");
        }
        catch (ImpossibleToPerform e){
            System.out.println("Utente non esistente\n");
        }

        System.out.println("Prova: iteratore oggetti di user_9");
        try{
            Iterator<Integer> it=b.getIterator("user_9","999");
            while(it.hasNext()){
                System.out.println(it.next());
            }
        }
        catch (UnauthorizedAccessException e){
            System.out.println("login errato");
        }

        System.out.println("\nProva: iteratore su utente non loggato correttamente");
        try{
            Iterator<Integer> it = b.getIterator("user_11","111");
            while(it.hasNext()){
                System.out.println(it.next());
            }
        }
        catch (UnauthorizedAccessException e) {
            System.out.println("login errato!");
        }
    }

}