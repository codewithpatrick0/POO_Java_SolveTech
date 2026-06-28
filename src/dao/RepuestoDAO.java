package dao;

import base_de_datos.Conexion;
import model.Repuesto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepuestoDAO implements IOperaciones<Repuesto>{
    @Override
    public boolean insertar(Repuesto repuesto) {
        String query = """
                        INSERT INTO repuestos(nombre, descripcion, precio, stock)
                        VALUES (?, ?, ?, ?)
                        """;
        try (Connection conn = Conexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setString(1, repuesto.getNombre());
            ps.setString(2, repuesto.getDescripcion());
            ps.setBigDecimal(3, repuesto.getPrecio());
            ps.setInt(4, repuesto.getStock());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            System.err.println("Error al insertar el repuesto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Repuesto> listar() {
        List<Repuesto> listaRepuestos = new ArrayList<>();
        String query = "SELECT id_repuesto, nombre, descripcion, precio, stock FROM repuestos";
        try(
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet resultado = ps.executeQuery();
                ){
            while (resultado.next()){
                Repuesto r = new Repuesto(
                        resultado.getInt("id_repuesto"),
                        resultado.getString("nombre"),
                        resultado.getString("descripcion"),
                        resultado.getBigDecimal("precio"),
                        resultado.getInt("stock")
                );
                listaRepuestos.add(r);
            }
        } catch (SQLException e){
            System.err.println("Error al obtener la lista de repuestos: " + e.getMessage());
        }
        return listaRepuestos;
    }

    @Override
    public Optional<Repuesto> buscarPorId(int id) {
        String query = "SELECT id_repuesto, nombre, descripcion, precio, stock FROM repuestos WHERE id_repuesto = ?";
        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ){
            ps.setInt(1, id);
            try (ResultSet resultado = ps.executeQuery()){
                if (resultado.next()){
                    Repuesto r = new Repuesto(
                            resultado.getInt("id_repuesto"),
                            resultado.getString("nombre"),
                            resultado.getString("descripcion"),
                            resultado.getBigDecimal("precio"),
                            resultado.getInt("stock")
                    );
                    return Optional.of(r);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el repuesto seleccionado " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean actualizar(Repuesto repuesto) {
        String query = "UPDATE repuestos SET nombre = ?, descripcion = ?, precio = ?, stock = ? WHERE id_repuesto = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setString(1, repuesto.getNombre());
            ps.setString(2, repuesto.getDescripcion());
            ps.setBigDecimal(3, repuesto.getPrecio());
            ps.setInt(4, repuesto.getStock());
            ps.setInt(5, repuesto.getIdRepuesto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el repuesto:  " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String query = "DELETE FROM repuestos WHERE id_repuesto = ?";
        try(Connection conn = Conexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el repuesto " + e.getMessage());
            return false;
        }
    }
}
