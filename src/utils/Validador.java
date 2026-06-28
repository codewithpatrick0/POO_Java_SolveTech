package utils;

import java.math.BigDecimal;

public class Validador {

    public static boolean validarNombreYTelefono(String nombre, String telefono) {
        if (nombre == null || telefono == null) return false;

        boolean nombreValido = nombre.trim().length() >= 2
                && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");

        boolean telefonoValido = telefono.matches("\\d{9}");

        return nombreValido && telefonoValido;
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
}