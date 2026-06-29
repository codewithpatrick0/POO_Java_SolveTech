package utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Validador {

    public static boolean validarNombreYTelefono(String nombre, String telefono) {
        if (nombre == null || telefono == null) return false;

        boolean nombreValido = nombre.trim().length() >= 2
                && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");

        boolean telefonoValido = telefono.matches("\\d{9}");

        return nombreValido && telefonoValido;
    }

    public static boolean validarNombre(String nombre){
        return nombre.trim().length() >= 2 && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
    }

    public static boolean validarCorreo(String correo) {
        if (correo == null) return false;

        return correo.matches("^[\\w.+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean validarCantidad(int cantidad){
        return cantidad > 0;
    }

    public static boolean validarStock(int stock){
        return stock > 0;
    }

    public static boolean validarPrecio (BigDecimal precio){
        return precio != null && precio.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean validarDescripcion(String descripcion) {
        return descripcion != null && descripcion.trim().length() >= 20 && descripcion.trim().length() <= 120;
    }

    public static boolean validarId(int id){
        return id > 0;
    }

    public static boolean validarEstado(String estado){
        List<String> estados = List.of("pendiente", "en proceso", "completado", "cancelado");
        return estado != null && estados.contains(estado.toLowerCase());
    }

    public static boolean esFechaFutura(LocalDate fecha){
        return fecha != null && fecha.isAfter(LocalDate.now());
    }
}