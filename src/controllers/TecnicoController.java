package controllers;

import dao.TecnicoDAO;
import model.Tecnico;
import utils.Validador;

import java.util.List;
import java.util.Optional;


public class TecnicoController {
    private TecnicoDAO dao = new TecnicoDAO();

    //Métodos
    public boolean registrarTecnico(String nombre, String telefono, String especialidad){
        if (!Validador.validarNombreYTelefono(nombre, telefono)){
            System.out.println("Error: nombre o teléfono inválidos");
            return false;
        }
        Tecnico t = new Tecnico(0, nombre, telefono, especialidad);
        dao.insertar(t);
        return true;
    }

    public List<Tecnico> obtenerTecnicos(){return dao.listar();}

    public Tecnico obtenerTecnicoPorId(int id){
        Optional<Tecnico> t = dao.buscarPorId(id);
        return t.orElse(null);
    }

    public boolean actualizarTecnico(int id, String nombre, String telefono, String especialidad){
        if (!Validador.validarNombreYTelefono(nombre, telefono)) {
            System.out.println("Error: nombre o teléfono inválido");
            return false;
        }
        Tecnico t = new Tecnico(id, nombre, telefono, especialidad);
        dao.actualizar(t);
        return true;
    }

    public boolean eliminarTecnico(int id) {
        return dao.eliminar(id);
    }
}
