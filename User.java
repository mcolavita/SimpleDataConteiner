public class User {

    //CLASSE UTENTE (NOME UTENTE, PASSWORD)

    private String name;
    private String passw;

    /*
    Overview: Tipo di dato che rappresenta un utente appartentente al Data Storage avente un Id e
              una password.

    Typical Element: [(Id,Passw)]
                     rappresenta un utente avente un Id e una password associato ad esso

    AF : c(x)= {c.getName(),c.getPassw()}

    IR (c) = //Nome non nullo
             c.getName() != null

             //Password non nulla
             c.getPassw != null
     */

    public User(String name, String passw) {
        this.name = name;
        this.passw = passw;
    }

    public String getName(){
        return this.name;
    }

    public String getPassw(){
        return this.passw;
    }

    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if(name == null) {
            if(other.name != null)
                return false;
        }
        else if(!name.equals(other.name))
            return false;
        if(passw != other.passw)
            return false;
        return true;
    }

}
