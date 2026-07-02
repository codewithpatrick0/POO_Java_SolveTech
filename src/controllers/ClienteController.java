package controllers;

import formatters.Formatter;
import dao.ClienteDAO;
import model.Cliente;
import utils.Validador;

import java.util.List;
import java.util.Optional;

public class ClienteController {
    private ClienteDAO dao = new ClienteDAO();

    //Métodos
    public boolean registrarCliente(String nombre, String telefono, String correo, String direccion){
        if (!Validador.validarNombreYTelefono(nombre, telefono)) {
            System.out.println("Error: nombre o teléfono inválido");
            return false;
        }

        if (!Validador.validarCorreo(correo)) {
            System.out.println("Error: Correo inválido");
            return false;
        }
        Cliente c = new Cliente(0, Formatter.capitalizar(nombre), telefono, correo, direccion);
        dao.insertar(c);
        return true;
    }

    public List<Cliente> obtenerClientes(){
        return dao.listar();
    }

    public Cliente obtenerClientePorId(int id){
        Optional<Cliente> c = dao.buscarPorId(id);
        return c.orElse(null);
    }

    public boolean actualizarCliente(int id, String nombre, String telefono, String correo, String direccion) {
        if (!Validador.validarNombreYTelefono(nombre, telefono)) {
            System.out.println("Error: nombre o teléfono inválido");
            return false;
        }

        if (!Validador.validarCorreo(correo)) {
            System.out.println("Error: Correo inválido");
            return false;
        }
        Cliente c = new Cliente(id, Formatter.capitalizar(nombre), telefono, correo, direccion);
        dao.actualizar(c);
        return true;
    }

    public boolean eliminarCliente(int id) {
        return dao.eliminar(id);
    }
}
