package model;
import java.time.LocalDate;

//Clase Venta, como su nombre lo dice será generada cada que se produzca una compra.
public class Venta {
    private int idVenta;
    private int idOrden;
    private int idRepuesto;
    private int cantidad;
    private double total;
    private LocalDate fecha;

    //Constructor
    public Venta(int idVenta, int idOrden, int idRepuesto, int cantidad, double total, LocalDate fecha) {
        this.idVenta = idVenta;
        this.idOrden = idOrden;
        this.idRepuesto = idRepuesto;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
    }

    //GETTERS
    public int getIdVenta() {return idVenta;}
    public int getIdOrden() {return idOrden;}
    public int getIdRepuesto() {return idRepuesto;}
    public int getCantidad() {return cantidad;}
    public double getTotal() {return total;}
    public LocalDate getFecha() {return fecha;}

    //SETTERS
    public void setIdVenta(int idVenta) {this.idVenta = idVenta;}
    public void setIdOrden(int idOrden) {this.idOrden = idOrden;}
    public void setIdRepuesto(int idRepuesto) {this.idRepuesto = idRepuesto;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setTotal(double total) {this.total = total;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    //Métodos
    public String getInfo(){
        return "Venta(ID-Venta: " + idVenta + "| ID-Orden: " + idOrden + "| ID-Repuesto: " + idRepuesto + "| Cantidad: " + cantidad + "| Total: " + total + "| Fecha: " + fecha + ")";
    }
    @Override
    public String toString() {
        return getInfo();
    }
}
