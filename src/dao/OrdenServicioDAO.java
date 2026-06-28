package dao;

import base_de_datos.Conexion;
import model.OrdenServicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdenServicioDAO implements IOperaciones<OrdenServicio>{
    @Override
    public boolean insertar(OrdenServicio orden) {
        String query = """
                INSERT INTO ordenes_servicio (id_cliente, id_tecnico, descripcion, estado, fecha)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orden.getIdCliente());
            ps.setInt(2, orden.getIdTecnico());
            ps.setString(3, orden.getDescripcion());
            ps.setString(4, orden.getEstado());
            ps.setDate(5, Date.valueOf(orden.getFecha()));  // ⚠️ ver nota abajo
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar orden de servicio: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<OrdenServicio> listar() {
        List<OrdenServicio> listaOrdenes = new ArrayList<>();
        String query = "SELECT id_orden, id_cliente, id_tecnico, descripcion, estado, fecha FROM ordenes_servicio";
        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ){
            while (rs.next()) {
                listaOrdenes.add(new OrdenServicio(
                        rs.getInt("id_orden"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_tecnico"),
                        rs.getString("descripcion"),
                        rs.getString("estado"),
                        rs.getDate("fecha").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar órdenes: " + e.getMessage());
        }
        return listaOrdenes;
    }

    @Override
    public Optional<OrdenServicio> buscarPorId(int id) {
        String query = "SELECT id_orden, id_cliente, id_tecnico, descripcion, estado, fecha FROM ordenes_servicio WHERE id_orden = ?";
        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    OrdenServicio orden = new OrdenServicio(
                            rs.getInt("id_orden"),
                            rs.getInt("id_cliente"),
                            rs.getInt("id_tecnico"),
                            rs.getString("descripcion"),
                            rs.getString("estado"),
                            rs.getDate("fecha").toLocalDate()
                    );
                    return Optional.of(orden);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar orden: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean actualizar(OrdenServicio orden) {
        String query = "UPDATE ordenes_servicio SET id_cliente = ?, id_tecnico = ?, descripcion = ?, estado = ?, fecha = ? WHERE id_orden = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orden.getIdCliente());
            ps.setInt(2, orden.getIdTecnico());
            ps.setString(3, orden.getDescripcion());
            ps.setString(4, orden.getEstado());
            ps.setDate(5, Date.valueOf(orden.getFecha()));
            ps.setInt(6, orden.getIdOrden());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar orden: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String query = "DELETE FROM ordenes_servicio WHERE id_orden = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar orden: " + e.getMessage());
            return false;
        }
    }
}
