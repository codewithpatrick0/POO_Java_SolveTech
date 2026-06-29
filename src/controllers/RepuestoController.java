package controllers;

import dao.RepuestoDAO;
import model.Repuesto;
import utils.Validador;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class RepuestoController {
    private RepuestoDAO dao = new RepuestoDAO();

    public boolean anadirRepuesto(String nombre, String desc, BigDecimal precio, int stock){
        if (!Validador.validarNombre(nombre)){
            System.out.println("Error: nombre inválido");
            return false;
        }

        if (!Validador.validarDescripcion(desc)){
            System.out.println("Error: Descripción inválida");
            return false;
        }

        if (!Validador.validarPrecio(precio) || !Validador.validarStock(stock)){
            System.out.println("Error: Precio o stock inválido");
            return false;
        }
        Repuesto r = new Repuesto(0, nombre, desc, precio, stock);
        dao.insertar(r);
        return true;
    }

    public List<Repuesto> listarRepuestos(){
        return dao.listar();
    }

    public Repuesto buscarRepuestoPorId(int id){
        Optional<Repuesto> r = dao.buscarPorId(id);
        return r.orElse(null);
    }

    public boolean actualizarRepuesto(int id, String nombre, String desc, BigDecimal precio, int stock){
        if (!Validador.validarNombre(nombre)){
            System.out.println("Error: nombre inválido");
            return false;
        }

        if (!Validador.validarDescripcion(desc)){
            System.out.println("Error: Descripción inválida");
            return false;
        }

        if (!Validador.validarPrecio(precio) || !Validador.validarStock(stock)){
            System.out.println("Error: Precio o stock inválido");
            return false;
        }
        Repuesto r = new Repuesto(id, nombre, desc, precio, stock);
        dao.actualizar(r);
        return true;
    }

    public boolean eliminarRepuesto(int id){
        return dao.eliminar(id);
    }
}
