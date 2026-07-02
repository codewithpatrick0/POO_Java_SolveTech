import base_de_datos.Conexion;
import view.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrarMenu();
        Conexion.cerrar();
    }
}