package view;

import Formatters.Formatter;
import controllers.*;
import model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

            System.out.print("\nSeleccione una opción: ");

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

    public void menuClientes() {
        int opcion;
        do {
            System.out.println("--- CLIENTES ---");
            System.out.println("1. Registrar clientes");
            System.out.println("2. Obtener lista de clientes");
            System.out.println("3. Buscar cliente por ID");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Regresar al menú principal");

            System.out.print("Seleccione una opción: ");
            try {
                opcion = sc.nextInt();
                //Limpieza de buffer
                sc.nextLine();

                switch (opcion) {
                    case 1 -> registrarClienteMenu();
                    case 2 -> obtenerClientesMenu();
                    case 3 -> buscarClientePorIdMenu();
                    case 4 -> actualizarClienteMenu();
                    case 5 -> eliminarClienteMenu();
                    case 0 -> System.out.println("Regresando al menú principal...");
                    default -> System.out.println("Opción inválida, vuelve a seleccionar.");
                }
            } catch (Exception e) {
                System.out.println("Error, debes ingresar un número.");
                sc.nextLine();
                opcion = -1;
            }
        } while (opcion != 0);
    }

    public void registrarClienteMenu(){
        System.out.print("Introduce el nombre del cliente a registrar: "); String nombre = sc.nextLine();
        System.out.print("Introduce el número de teléfono del cliente " + Formatter.capitalizar(nombre) + ": "); String telefono = sc.nextLine();
        System.out.print("Introduce el correo: "); String correo = sc.nextLine();
        System.out.print("Introduce la dirección de domicilio: "); String direccion = sc.nextLine();

        //Validamos el registro del cliente
        boolean ok = clienteCtrl.registrarCliente(nombre, telefono, correo, direccion);
        System.out.println(ok ? "Cliente registrado correctamente." : "No se pudo registrar el cliente.");
    }

    public void obtenerClientesMenu(){
        List<Cliente> listaClientes = clienteCtrl.obtenerClientes();
        if (listaClientes.isEmpty()){
            System.out.println("No hay clientes registrados");
            return;
        }
        for (Cliente c : listaClientes){
            System.out.println(c.getInfo());
        }
    }

    public void buscarClientePorIdMenu(){
        System.out.println("Introduce el ID del cliente a buscar: ");
        try {
            int id = sc.nextInt();
            sc.nextLine();
            Cliente clienteEncontrado = clienteCtrl.obtenerClientePorId(id);

            if (clienteEncontrado != null) {
                System.out.println("Cliente encontrado.");
                System.out.println(clienteEncontrado.getInfo());
            } else {
                System.out.println("El cliente no existe.");
            }

        } catch (Exception e){
            System.out.println("Tienes que introducir un número.");
            sc.nextLine();
        }
    }

    public void actualizarClienteMenu(){
        try {
            System.out.print("Introduce el ID del cliente a actualizar: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Introduce el nuevo nombre del cliente: ");
            String nombre = sc.nextLine();
            System.out.print("Introduce el nuevo número de teléfono del cliente " + Formatter.capitalizar(nombre) + ": ");
            String telefono = sc.nextLine();
            System.out.print("Introduce el nuevo correo: ");
            String correo = sc.nextLine();
            System.out.print("Introduce la nueva dirección de domicilio: ");
            String direccion = sc.nextLine();

            //Validamos la actualización del cliente
            boolean ok = clienteCtrl.actualizarCliente(id, nombre, telefono, correo, direccion);
            System.out.println(ok ? "Cliente actualizado correctamente." : "No se pudo actualizar el cliente.");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir un número.");
            sc.nextLine();
        }
    }


    public void eliminarClienteMenu(){
        System.out.print("Introduce el ID del cliente que deseas eliminar: ");
        try {
            int id = sc.nextInt(); sc.nextLine();
            boolean ok = clienteCtrl.eliminarCliente(id);
            System.out.println(ok ? "Cliente eliminado satisfactoriamente." : "No se encontró al cliente");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir un número.");
            sc.nextLine();
        }
    }

    //Técnicos
    public void menuTecnicos() {
        int opcion;
        do {
            System.out.println("\n--- TÉCNICOS ---");
            System.out.println("1. Registrar técnico");
            System.out.println("2. Obtener lista de técnicos");
            System.out.println("3. Buscar técnico por ID");
            System.out.println("4. Actualizar técnico");
            System.out.println("5. Eliminar técnico");
            System.out.println("0. Regresar al menú principal");

            System.out.print("Seleccione una opción: ");
            try {
                opcion = sc.nextInt();
                sc.nextLine(); // Limpieza de buffer

                switch (opcion) {
                    case 1 -> registrarTecnicoMenu();
                    case 2 -> obtenerTecnicosMenu();
                    case 3 -> buscarTecnicoPorIdMenu();
                    case 4 -> actualizarTecnicoMenu();
                    case 5 -> eliminarTecnicoMenu();
                    case 0 -> System.out.println("Regresando al menú principal...");
                    default -> System.out.println("Opción inválida, vuelve a seleccionar.");
                }
            } catch (Exception e) {
                System.out.println("Error, debes ingresar un número.");
                sc.nextLine(); // Limpieza de buffer si ingresan letras
                opcion = -1;
            }
        } while (opcion != 0);
    }

    public void registrarTecnicoMenu() {
        System.out.print("Introduce el nombre del técnico a registrar: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce el número de teléfono del técnico " + Formatter.capitalizar(nombre) + ": ");
        String telefono = sc.nextLine();
        System.out.print("Introduce la especialidad: ");
        String specialty = sc.nextLine();

        // Enviados en el orden exacto de tu controlador: nombre, telefono, especialidad
        boolean ok = tecnicoCtrl.registrarTecnico(nombre, telefono, specialty);
        System.out.println(ok ? "Técnico registrado correctamente." : "No se pudo registrar al técnico.");
    }

    public void obtenerTecnicosMenu() {
        List<Tecnico> listaTecnicos = tecnicoCtrl.obtenerTecnicos();
        if (listaTecnicos.isEmpty()) {
            System.out.println("No hay técnicos registrados");
            return;
        }
        for (Tecnico t : listaTecnicos) {
            System.out.println(t.getInfo());
        }
    }

    public void buscarTecnicoPorIdMenu() {
        System.out.println("Introduce el ID del técnico a buscar: ");
        try {
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer
            Tecnico tecnicoEncontrado = tecnicoCtrl.obtenerTecnicoPorId(id);

            if (tecnicoEncontrado != null) {
                System.out.println("Técnico encontrado.");
                System.out.println(tecnicoEncontrado.getInfo());
            } else {
                System.out.println("El técnico no existe.");
            }
        } catch (Exception e) {
            System.out.println("Tienes que introducir un número.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    public void actualizarTecnicoMenu() {
        try {
            System.out.println("Introduce el ID del técnico a actualizar: ");
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce el nuevo nombre del técnico: ");
            String nombre = sc.nextLine();
            System.out.print("Introduce el nuevo número de teléfono del técnico " + Formatter.capitalizar(nombre) + ": ");
            String telefono = sc.nextLine();
            System.out.print("Introduce la nueva especialidad: ");
            String specialty = sc.nextLine();

            // Validamos la actualización del técnico con tu orden exacto
            boolean ok = tecnicoCtrl.actualizarTecnico(id, nombre, telefono, specialty);
            System.out.println(ok ? "Técnico actualizado correctamente." : "No se pudo actualizar el técnico.");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir un número.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    public void eliminarTecnicoMenu() {
        System.out.print("Introduce el ID del técnico que deseas eliminar: ");
        try {
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            boolean ok = tecnicoCtrl.eliminarTecnico(id);
            System.out.println(ok ? "Técnico eliminado satisfactoriamente." : "No se encontró al técnico");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir un número.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    //Repuestos
    //Repuestos
    public void menuRepuestos() {
        int opcion;
        do {
            System.out.println("\n--- REPUESTOS ---");
            System.out.println("1. Registrar repuesto");
            System.out.println("2. Obtener lista de repuestos");
            System.out.println("3. Buscar repuesto por ID");
            System.out.println("4. Actualizar repuesto");
            System.out.println("5. Eliminar repuesto");
            System.out.println("0. Regresar al menú principal");

            System.out.print("Seleccione una opción: ");
            try {
                opcion = sc.nextInt();
                sc.nextLine(); // Limpieza de buffer

                switch (opcion) {
                    case 1 -> registrarRepuestoMenu();
                    case 2 -> obtenerRepuestosMenu();
                    case 3 -> buscarRepuestoPorIdMenu();
                    case 4 -> actualizarRepuestoMenu();
                    case 5 -> eliminarRepuestoMenu();
                    case 0 -> System.out.println("Regresando al menú principal...");
                    default -> System.out.println("Opción inválida, vuelve a seleccionar.");
                }
            } catch (Exception e) {
                System.out.println("Error, debes ingresar un número.");
                sc.nextLine(); // Limpieza de buffer si ingresan letras
                opcion = -1;
            }
        } while (opcion != 0);
    }

    public void registrarRepuestoMenu() {
        System.out.print("Introduce el nombre del repuesto a registrar: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce la descripción del repuesto " + Formatter.capitalizar(nombre) + " (20-120 caracteres): ");
        String descripcion = sc.nextLine();

        try {
            System.out.print("Introduce el precio del repuesto: ");
            BigDecimal precio = sc.nextBigDecimal();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce el stock disponible: ");
            int stock = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            boolean ok = repuestoCtrl.anadirRepuesto(nombre, descripcion, precio, stock);
            System.out.println(ok ? "Repuesto registrado correctamente." : "No se pudo registrar el repuesto.");
        } catch (Exception e) {
            System.out.println("Error: El precio y el stock deben ser números válidos.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    public void obtenerRepuestosMenu() {
        List<Repuesto> listaRepuestos = repuestoCtrl.listarRepuestos();
        if (listaRepuestos.isEmpty()) {
            System.out.println("No hay repuestos registrados");
            return;
        }
        for (Repuesto r : listaRepuestos) {
            System.out.println(r.getInfo());
        }
    }

    public void buscarRepuestoPorIdMenu() {
        System.out.println("Introduce el ID del repuesto a buscar: ");
        try {
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer
            Repuesto repuestoEncontrado = repuestoCtrl.buscarRepuestoPorId(id);

            if (repuestoEncontrado != null) {
                System.out.println("Repuesto encontrado.");
                System.out.println(repuestoEncontrado.getInfo());
            } else {
                System.out.println("El repuesto no existe.");
            }
        } catch (Exception e) {
            System.out.println("Tienes que introducir un número.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    public void actualizarRepuestoMenu() {
        try {
            System.out.println("Introduce el ID del repuesto a actualizar: ");
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce el nuevo nombre del repuesto: ");
            String nombre = sc.nextLine();
            System.out.print("Introduce la nueva descripción del repuesto " + Formatter.capitalizar(nombre) + " (20-120 caracteres): ");
            String descripcion = sc.nextLine();
            System.out.print("Introduce el nuevo precio: ");
            BigDecimal precio = sc.nextBigDecimal();
            sc.nextLine(); // Limpieza de buffer
            System.out.print("Introduce el nuevo stock: ");
            int stock = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            boolean ok = repuestoCtrl.actualizarRepuesto(id, nombre, descripcion, precio, stock);
            System.out.println(ok ? "Repuesto actualizado correctamente." : "No se pudo actualizar el repuesto.");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir valores numéricos válidos.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    public void eliminarRepuestoMenu() {
        System.out.print("Introduce el ID del repuesto que deseas eliminar: ");
        try {
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            boolean ok = repuestoCtrl.eliminarRepuesto(id);
            System.out.println(ok ? "Repuesto eliminado satisfactoriamente." : "No se encontró el repuesto");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir un número.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    //Órdenes de servicio
    public void menuOrden() {
        int opcion;
        do {
            System.out.println("\n--- ÓRDENES DE SERVICIO ---");
            System.out.println("1. Generar nueva orden");
            System.out.println("2. Obtener lista de órdenes");
            System.out.println("3. Buscar orden por ID");
            System.out.println("4. Actualizar orden");
            System.out.println("5. Eliminar orden");
            System.out.println("0. Regresar al menú principal");

            System.out.print("Seleccione una opción: ");
            try {
                opcion = sc.nextInt();
                sc.nextLine(); // Limpieza de buffer

                switch (opcion) {
                    case 1 -> generarOrdenMenu();
                    case 2 -> obtenerOrdenesMenu();
                    case 3 -> buscarOrdenPorIdMenu();
                    case 4 -> actualizarOrdenMenu();
                    case 5 -> eliminarOrdenMenu();
                    case 0 -> System.out.println("Regresando al menú principal...");
                    default -> System.out.println("Opción inválida, vuelve a seleccionar.");
                }
            } catch (Exception e) {
                System.out.println("Error, debes ingresar un número.");
                sc.nextLine(); // Limpieza de buffer si ingresan letras
                opcion = -1;
            }
        } while (opcion != 0);
    }

    public void generarOrdenMenu() {
        try {
            System.out.print("Introduce el ID del cliente: ");
            int idCliente = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce el ID del técnico: ");
            int idTecnico = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce la descripción del servicio (20-120 caracteres): ");
            String descripcion = sc.nextLine();

            System.out.print("Introduce el estado (pendiente / en proceso / completado / cancelado): ");
            String estado = sc.nextLine();

            System.out.print("Introduce la fecha (yyyy-MM-dd): ");
            String fechaTexto = sc.nextLine();
            LocalDate fecha = LocalDate.parse(fechaTexto);

            boolean ok = ordenCtrl.generarNuevaOrden(idCliente, idTecnico, descripcion, estado, fecha);
            System.out.println(ok ? "Orden generada correctamente." : "No se pudo generar la orden.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: La fecha debe tener el formato yyyy-MM-dd.");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir valores válidos.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    public void obtenerOrdenesMenu() {
        List<OrdenServicio> listaOrdenes = ordenCtrl.listarOrdenes();
        if (listaOrdenes.isEmpty()) {
            System.out.println("No hay órdenes registradas");
            return;
        }
        for (OrdenServicio o : listaOrdenes) {
            System.out.println(o.getInfo());
        }
    }

    public void buscarOrdenPorIdMenu() {
        System.out.println("Introduce el ID de la orden a buscar: ");
        try {
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer
            OrdenServicio ordenEncontrada = ordenCtrl.buscarOrdenPorId(id);

            if (ordenEncontrada != null) {
                System.out.println("Orden encontrada.");
                System.out.println(ordenEncontrada.getInfo());
            } else {
                System.out.println("La orden no existe.");
            }
        } catch (Exception e) {
            System.out.println("Tienes que introducir un número.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    public void actualizarOrdenMenu() {
        try {
            System.out.println("Introduce el ID de la orden a actualizar: ");
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce el nuevo ID del cliente: ");
            int idCliente = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce el nuevo ID del técnico: ");
            int idTecnico = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce la nueva descripción (20-120 caracteres): ");
            String descripcion = sc.nextLine();

            System.out.print("Introduce el nuevo estado (pendiente / en proceso / completado / cancelado): ");
            String estado = sc.nextLine();

            System.out.print("Introduce la nueva fecha (yyyy-MM-dd): ");
            String fechaTexto = sc.nextLine();
            LocalDate fecha = LocalDate.parse(fechaTexto);

            boolean ok = ordenCtrl.actualizarOrden(id, idCliente, idTecnico, descripcion, estado, fecha);
            System.out.println(ok ? "Orden actualizada correctamente." : "No se pudo actualizar la orden.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: La fecha debe tener el formato yyyy-MM-dd.");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir valores válidos.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    public void eliminarOrdenMenu() {
        System.out.print("Introduce el ID de la orden que deseas eliminar: ");
        try {
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            boolean ok = ordenCtrl.eliminarOrden(id);
            System.out.println(ok ? "Orden eliminada satisfactoriamente." : "No se encontró la orden");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir un número.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    //Ventas
    public void menuVentas() {
        int opcion;
        do {
            System.out.println("\n--- VENTAS ---");
            System.out.println("1. Registrar venta");
            System.out.println("2. Obtener lista de ventas");
            System.out.println("3. Buscar venta por ID");
            System.out.println("4. Actualizar venta");
            System.out.println("5. Eliminar venta");
            System.out.println("0. Regresar al menú principal");

            System.out.print("Seleccione una opción: ");
            try {
                opcion = sc.nextInt();
                sc.nextLine(); // Limpieza de buffer

                switch (opcion) {
                    case 1 -> registrarVentaMenu();
                    case 2 -> obtenerVentasMenu();
                    case 3 -> buscarVentaPorIdMenu();
                    case 4 -> actualizarVentaMenu();
                    case 5 -> eliminarVentaMenu();
                    case 0 -> System.out.println("Regresando al menú principal...");
                    default -> System.out.println("Opción inválida, vuelve a seleccionar.");
                }
            } catch (Exception e) {
                System.out.println("Error, debes ingresar un número.");
                sc.nextLine(); // Limpieza de buffer si ingresan letras
                opcion = -1;
            }
        } while (opcion != 0);
    }

    public void registrarVentaMenu() {
        try {
            System.out.print("Introduce el ID de la orden: ");
            int idOrden = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce el ID del repuesto: ");
            int idRepuesto = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce la cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce la fecha (yyyy-MM-dd): ");
            String fechaTexto = sc.nextLine();
            LocalDate fecha = LocalDate.parse(fechaTexto);

            boolean ok = ventaCtrl.anadirVenta(idOrden, idRepuesto, cantidad, fecha);
            System.out.println(ok ? "Venta registrada correctamente." : "No se pudo registrar la venta (verifica el stock disponible o los IDs ingresados).");
        } catch (DateTimeParseException e) {
            System.out.println("Error: La fecha debe tener el formato yyyy-MM-dd.");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir valores válidos.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    public void obtenerVentasMenu() {
        List<Venta> listaVentas = ventaCtrl.listarVentas();
        if (listaVentas.isEmpty()) {
            System.out.println("No hay ventas registradas");
            return;
        }
        for (Venta v : listaVentas) {
            System.out.println(v.getInfo());
        }
    }

    public void buscarVentaPorIdMenu() {
        System.out.println("Introduce el ID de la venta a buscar: ");
        try {
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer
            Venta ventaEncontrada = ventaCtrl.buscarVentaPorId(id);

            if (ventaEncontrada != null) {
                System.out.println("Venta encontrada.");
                System.out.println(ventaEncontrada.getInfo());
            } else {
                System.out.println("La venta no existe.");
            }
        } catch (Exception e) {
            System.out.println("Tienes que introducir un número.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    public void actualizarVentaMenu() {
        try {
            System.out.println("Introduce el ID de la venta a actualizar: ");
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce el nuevo ID de la orden: ");
            int idOrden = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce el nuevo ID del repuesto: ");
            int idRepuesto = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce la nueva cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            System.out.print("Introduce la nueva fecha (yyyy-MM-dd): ");
            String fechaTexto = sc.nextLine();
            LocalDate fecha = LocalDate.parse(fechaTexto);

            boolean ok = ventaCtrl.actualizarVenta(id, idOrden, idRepuesto, cantidad, fecha);
            System.out.println(ok ? "Venta actualizada correctamente." : "No se pudo actualizar la venta (verifica el stock disponible o los IDs ingresados).");
        } catch (DateTimeParseException e) {
            System.out.println("Error: La fecha debe tener el formato yyyy-MM-dd.");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir valores válidos.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

    public void eliminarVentaMenu() {
        System.out.print("Introduce el ID de la venta que deseas eliminar: ");
        try {
            int id = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            boolean ok = ventaCtrl.eliminarVenta(id);
            System.out.println(ok ? "Venta eliminada satisfactoriamente." : "No se encontró la venta");
        } catch (Exception e) {
            System.out.println("Error: Debes introducir un número.");
            sc.nextLine(); // Limpieza de buffer
        }
    }

}


