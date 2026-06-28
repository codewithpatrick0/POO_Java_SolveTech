package dao;

import base_de_datos.Conexion;
import model.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class VentaDAO implements IOperaciones<Venta> {

    @Override
    public boolean insertar(Venta venta) {
        String query = """
                INSERT INTO ventas (id_orden, id_repuesto, cantidad, total, fecha)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, venta.getIdOrden());
            ps.setInt(2, venta.getIdRepuesto());
            ps.setInt(3, venta.getCantidad());
            ps.setBigDecimal(4, venta.getTotal());
            ps.setDate(5, Date.valueOf(venta.getFecha()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar venta: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Venta> listar() {
        List<Venta> lista = new ArrayList<>();
        String query = "SELECT id_venta, id_orden, id_repuesto, cantidad, total, fecha FROM ventas";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Venta(
                        rs.getInt("id_venta"),
                        rs.getInt("id_orden"),
                        rs.getInt("id_repuesto"),
                        rs.getInt("cantidad"),
                        rs.getBigDecimal("total"),
                        rs.getDate("fecha").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar ventas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Optional<Venta> buscarPorId(int id) {
        String query = "SELECT id_venta, id_orden, id_repuesto, cantidad, total, fecha FROM ventas WHERE id_venta = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Venta v = new Venta(
                            rs.getInt("id_venta"),
                            rs.getInt("id_orden"),
                            rs.getInt("id_repuesto"),
                            rs.getInt("cantidad"),
                            rs.getBigDecimal("total"),
                            rs.getDate("fecha").toLocalDate()
                    );
                    return Optional.of(v);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar venta: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean actualizar(Venta venta) {
        String query = "UPDATE ventas SET id_orden = ?, id_repuesto = ?, cantidad = ?, total = ?, fecha = ? WHERE id_venta = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, venta.getIdOrden());
            ps.setInt(2, venta.getIdRepuesto());
            ps.setInt(3, venta.getCantidad());
            ps.setBigDecimal(4, venta.getTotal());
            ps.setDate(5, Date.valueOf(venta.getFecha()));
            ps.setInt(6, venta.getIdVenta());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar venta: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String query = "DELETE FROM ventas WHERE id_venta = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar venta: " + e.getMessage());
            return false;
        }
    }
}