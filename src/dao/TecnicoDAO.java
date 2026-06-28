package dao;

import base_de_datos.Conexion;
import model.Tecnico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TecnicoDAO implements IOperaciones<Tecnico>{
    @Override
    public boolean insertar(Tecnico tecnico) {
        String query = """
                        INSERT INTO tecnicos (nombre, telefono, especialidad)
                        VALUES (?, ?, ?)
                        """;
        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ){
            ps.setString(1, tecnico.getNombre());
            ps.setString(2, tecnico.getTelefono());
            ps.setString(3, tecnico.getEspecialidad());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al agregar al técnico: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Tecnico> listar() {
        List<Tecnico> listaTecnicos = new ArrayList<>();
        String query = "SELECT id_tecnico, nombre, telefono, especialidad FROM tecnicos";
        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet resultado = ps.executeQuery();
        ){
            while(resultado.next()){
                Tecnico t= new Tecnico(
                        resultado.getInt("id_tecnico"),
                        resultado.getString("nombre"),
                        resultado.getString("telefono"),
                        resultado.getString("especialidad")
                );
                listaTecnicos.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener lista de técnicos: " + e.getMessage());
        }
        return listaTecnicos;
    }

    @Override
    public Optional<Tecnico> buscarPorId(int id) {
        String query = "SELECT id_tecnico, nombre, telefono, especialidad FROM tecnicos WHERE id_tecnico = ?";
        try(
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, id);
            try (ResultSet resultado = ps.executeQuery()){
                if(resultado.next()){
                    Tecnico t = new Tecnico(
                            resultado.getInt("id_tecnico"),
                            resultado.getString("nombre"),
                            resultado.getString("telefono"),
                            resultado.getString("especialidad")
                    );
                    return Optional.of(t);
                }
            }
        } catch (SQLException e){
            System.err.println("Error al obtener el técnico seleccionado:  " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean actualizar(Tecnico tecnico) {
        String query = "UPDATE tecnicos SET nombre = ?, telefono = ?, especialidad = ? WHERE id_tecnico = ?";
        try(
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ){
            ps.setString(1, tecnico.getNombre());
            ps.setString(2, tecnico.getTelefono());
            ps.setString(3, tecnico.getEspecialidad());
            ps.setInt(4, tecnico.getId());
            ps.executeUpdate();
            return true;
            } catch (SQLException e) {
            System.err.println("Error al actualizar al técnico seleccionado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String query = "DELETE FROM tecnicos WHERE id_tecnico = ?";
        try(
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ){
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el técnico seleccionado: " + e.getMessage());
            return false;
        }
    }
}
