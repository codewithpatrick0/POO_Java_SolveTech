package model;
//Importamos LocalDate para declarar con este tipo de variable a la fecha que será atributo de la clase OrdenServicio
import java.time.LocalDate;

//Clase OrdenServicio, generada cada que se haga un servicio.
public class OrdenServicio {
    private int idOrden;
    private int idCliente;
    private int idTecnico;
    private String descripcion;
    private String estado;
    private LocalDate fecha;

    //Constructor
    public OrdenServicio(int idOrden, int idCliente, int idTecnico, String descripcion, String estado, LocalDate fecha) {
        this.idOrden = idOrden;
        this.idCliente = idCliente;
        this.idTecnico = idTecnico;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
    }

    //GETTERS
    public int getIdOrden() {return idOrden;}
    public int getIdCliente() {return idCliente;}
    public int getIdTecnico() {return idTecnico;}
    public String getDescripcion() {return descripcion;}
    public String getEstado() {return estado;}
    public LocalDate getFecha() {return fecha;}

    //SETTERS
    public void setIdOrden(int idOrden) {this.idOrden = idOrden;}
    public void setIdCliente(int idCliente) {this.idCliente = idCliente;}
    public void setIdTecnico(int idTecnico) {this.idTecnico = idTecnico;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setEstado(String estado) {this.estado = estado;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    //Métodos
    public String getInfo(){
        return "OrdenServicio(ID-Orden: " + idOrden + "| ID-Cliente: " + idCliente + "| ID-Técnico: " + idTecnico + "| Descripción: " + descripcion + "| Estado: " + estado + "| Fecha: " + fecha + ")";
    }
    @Override
    public String toString() {
        return getInfo();
    }
}
