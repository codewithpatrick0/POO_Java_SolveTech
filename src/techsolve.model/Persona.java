//Establecemos clase abstracta Persona es una plantilla con características comúnes
public abstract class Persona {
    protected int id;
    protected String nombre;
    protected String telefono;

    //Constructor
    public Persona(int id, String nombre, String telefono){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    //GETTERS
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public String getTelefono() {return telefono;}

    //SETTERS
    public void setId(int id) {this.id = id;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    //Métodos
    public abstract String getInfo();

    @Override
    public String toString() {
        return getInfo();
    }
}
