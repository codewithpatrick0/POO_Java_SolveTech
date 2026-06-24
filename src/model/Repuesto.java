package model;

//Clase Repuesto, son el o los materiales que se usarán para el servicio.
public class Repuesto {
    //Atributos
    private int idRepuesto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    //Constructor
    public Repuesto(int idRepuesto, String nombre, String descripcion, double precio, int stock) {
        this.idRepuesto = idRepuesto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    //GETTERS
    public int getIdRepuesto() {return idRepuesto;}
    public String getNombre() {return nombre;}
    public String getDescripcion() {return descripcion;}
    public double getPrecio() {return precio;}
    public int getStock() {return stock;}

    //SETTERS
    public void setIdRepuesto(int idRepuesto) {this.idRepuesto = idRepuesto;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setPrecio(double precio) {this.precio = precio;}
    public void setStock(int stock) {this.stock = stock;}

    public String getInfo(){
        return "Repuesto(ID: " + idRepuesto + "| Nombre: " + nombre + "| Descripción: " + descripcion + "| Precio: " + precio + "| Stock: " + stock + ")";
    }
    @Override
    public String toString() {
        return getInfo();
    }
}

