//Clase heredada de Persona, ROL: Técnico con especialidad asignada.

public class Tecnico extends Persona{
    //Atributo propio
    private String especialidad;

    //Constructor
    public Tecnico(int id, String nombre, String telefono, String especialidad){
        super(id, nombre, telefono);
        this.especialidad = especialidad;
    }

    //GETTER
    public String getEspecialidad() {return especialidad;}

    //SETTER
    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}

    //Métodos
    @Override
    public String getInfo(){
        return "Técnico(ID: " + id + "| Nombre: " + nombre + "| Teléfono: " + telefono + "| Especialidad: " + especialidad + ")";
    }
}
