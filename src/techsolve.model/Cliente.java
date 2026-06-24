public class Cliente extends Persona{
    //Atributos propios
    private String correo;
    private String direccion;

    //Constructor
    public Cliente(int id, String nombre, String telefono, String correo, String direccion){
        super(id, nombre, telefono); //Llamamos a super para inicializar (id, nombre, telefono) sin necesidad del this
        this.correo = correo;
        this.direccion = direccion;
    }

    //GETTERS
    public String getDireccion() {return direccion;}
    public String getCorreo() {return correo;}

    //SETTERS
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public void setCorreo(String correo) {this.correo = correo;}

    //Métodos (Modificamos con @override getInfo del padre)
    @Override
    public String getInfo() {
        return "Cliente(ID: " + id + "| Nombre: " + nombre + "| Teléfono: " + telefono + "| Correo: " + correo + "| Dirección: " + direccion + ")";
    }
}
