package controllers;

import dao.OrdenServicioDAO;
import model.OrdenServicio;
import utils.Validador;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrdenController {
    private OrdenServicioDAO dao = new OrdenServicioDAO();

    public boolean generarNuevaOrden(int id_cliente, int id_tecnico, String desc, String estado, LocalDate fecha){
        if (!Validador.validarId(id_cliente) || !Validador.validarId(id_tecnico)){
            System.out.println("Error: ID del cliente o técnico inválido");
            return false;
        }

        if (!Validador.validarDescripcion(desc)){
            System.out.println("Error: Descripción inválida");
            return false;
        }

        if (!Validador.validarEstado(estado)){
            System.out.println("Error: Estado inexistente");
            return false;
        }

        if (Validador.esFechaFutura(fecha)){
            System.out.println("Error: Las fecha ingresada aún no existe");
            return false;
        }
        OrdenServicio orden = new OrdenServicio(0, id_cliente, id_tecnico, desc, estado, fecha);
        dao.insertar(orden);
        return true;
    }

    public List<OrdenServicio> listarOrdenes() {
        return dao.listar();
    }

    public OrdenServicio buscarOrdenPorId(int id) {
        Optional<OrdenServicio> orden = dao.buscarPorId(id);
        return orden.orElse(null);
    }

    public boolean actualizarOrden(int id, int id_cliente, int id_tecnico, String desc, String estado, LocalDate fecha) {
        if (!Validador.validarId(id_cliente) || !Validador.validarId(id_tecnico)){
            System.out.println("Error: ID del cliente o técnico inválido");
            return false;
        }

        if (!Validador.validarDescripcion(desc)){
            System.out.println("Error: Descripción inválida");
            return false;
        }

        if (!Validador.validarEstado(estado)){
            System.out.println("Error: Estado inexistente");
            return false;
        }

        if (Validador.esFechaFutura(fecha)){
            System.out.println("Error: Las fecha ingresada aún no existe");
            return false;
        }
        OrdenServicio orden = new OrdenServicio(id, id_cliente, id_tecnico, desc, estado, fecha);
        dao.actualizar(orden);
        return true;
    }

    public boolean eliminarOrden(int id) {
        return dao.eliminar(id);
    }
}
