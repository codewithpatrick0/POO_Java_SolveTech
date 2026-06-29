package controllers;

import dao.RepuestoDAO;
import dao.VentaDAO;
import model.Repuesto;
import model.Venta;
import utils.Validador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class VentaController {
    private VentaDAO dao = new VentaDAO();
    private RepuestoDAO repuestoDAO = new RepuestoDAO();

    public boolean anadirVenta(int id_orden, int id_repuesto, int cantidad, LocalDate fecha) {
        if (!Validador.validarId(id_orden) || !Validador.validarId(id_repuesto)) {
            System.out.println("Error: La venta tiene algún ID inválido.");
            return false;
        }

        if (!Validador.validarCantidad(cantidad)) {
            System.out.println("Error: Cantidad ingresada inválida");
            return false;
        }

        if (Validador.esFechaFutura(fecha)) {
            System.out.println("Error: La fecha ingresada aún no existe en el calendario.");
            return false;
        }
        //Validar stock y calcular total
        Optional<Repuesto> repuestoOptional = repuestoDAO.buscarPorId(id_repuesto);
        if (repuestoOptional.isPresent()) {
            Repuesto repuesto = repuestoOptional.get();
            if (repuesto.getStock() >= cantidad){
                BigDecimal total = repuesto.getPrecio().multiply(BigDecimal.valueOf(cantidad));
                repuesto.setStock(repuesto.getStock() - cantidad);
                repuestoDAO.actualizar(repuesto);

                    Venta venta = new Venta(0, id_orden, id_repuesto, cantidad, total, fecha);
                    dao.insertar(venta);
                    return true;
            }
        }
        return false;
    }

    public List<Venta> listarVentas() {
        return dao.listar();
    }

    public Venta buscarVentaPorId(int id) {
        Optional<Venta> venta = dao.buscarPorId(id);
        return venta.orElse(null);
    }

    public boolean actualizarVenta(int id, int id_orden, int id_repuesto, int cantidad, LocalDate fecha){
        if (!Validador.validarId(id_orden) || !Validador.validarId(id_repuesto)) {
            System.out.println("Error: La venta tiene algún ID inválido.");
            return false;
        }

        if (!Validador.validarCantidad(cantidad)) {
            System.out.println("Error: Cantidad ingresada inválida");
            return false;
        }

        if (Validador.esFechaFutura(fecha)) {
            System.out.println("Error: La fecha ingresada aún no existe en el calendario.");
            return false;
        }
        //Validar stock y calcular total
        Optional<Repuesto> repuestoOptional = repuestoDAO.buscarPorId(id_repuesto);
        if (repuestoOptional.isPresent()) {
            Repuesto repuesto = repuestoOptional.get();
            if (repuesto.getStock() >= cantidad){
                BigDecimal total = repuesto.getPrecio().multiply(BigDecimal.valueOf(cantidad));
                repuesto.setStock(repuesto.getStock() - cantidad);
                repuestoDAO.actualizar(repuesto);

                Venta venta = new Venta(id, id_orden, id_repuesto, cantidad, total, fecha);
                dao.actualizar(venta);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarVenta(int id) {
        return dao.eliminar(id);
    }
}
