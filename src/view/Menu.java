package view;

import controllers.*;
import model.Cliente;
import model.OrdenServicio;
import model.Repuesto;
import model.Venta;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);
    private final ClienteController clienteCtrl = new ClienteController();
    private final TecnicoController tecnicoCtrl = new TecnicoController();
    private final RepuestoController repuestoCtrl = new RepuestoController();
    private final OrdenController ordenCtrl = new OrdenController();
    private final VentaController ventaCtrl = new VentaController();

    public void mostrarMenu() {
        int opcion;
        //Menu interactivo
        do {
            System.out.println("---TechSolve System---");
            System.out.println("   1. Menú de clientes   ");
            System.out.println("   2. Menú de Técnicos   ");
            System.out.println("   3. Menú de repuestos   ");
            System.out.println("   4. Menú de Órdenes de servicio   ");
            System.out.println("   5. Menú de ventas   ");
            System.out.println("   0. Salir   ");

            System.out.println("\nSeleccione una opción: ");

            try {
                opcion = sc.nextInt();

                //Limpieza de buffer
                sc.nextLine();

                switch (opcion) {
                    case 1 -> menuClientes();
                    case 2 -> menuTecnicos();
                    case 3 -> menuRepuestos();
                    case 4 -> menuOrden();
                    case 5 -> menuVentas();
                    case 0 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("Opción inválida, vuelve a seleccionar.");
                }
            } catch (Exception e) {
                System.out.println("Error, debes ingresar un número");
                //Limpieza de buffer si se introdujo algún str
                sc.nextLine();
                opcion = -1;
            }
        } while (opcion != 0);
    }
    
}


