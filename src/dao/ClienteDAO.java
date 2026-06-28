package dao;
import model.Cliente;
import base_de_datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Optional;

public class ClienteDAO implements IOperaciones<Cliente> {
    //Métodos para gestionar en la base de datos.
    @Override
    public boolean insertar(Cliente cliente) {
        String query = """
                INSERT INTO clientes (nombre, telefono, correo, direccion)
                VALUES (?, ? , ?, ?)
                """;
        try (
                //Abrimos conexión a la base de datos
                Connection conn = Conexion.getConnection();
                //Preparamos la sentencia sql
                PreparedStatement ps = conn.prepareStatement(query);
                ){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getDireccion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Cliente> listar() {
        String query = "SELECT id_cliente, nombre, telefono, correo, direccion FROM clientes";
        List<Cliente> listaClientes = new ArrayList<>();
        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet resultado = ps.executeQuery();
                ){
            while (resultado.next()){
                Cliente c = new Cliente(
                        resultado.getInt("id_cliente"),
                        resultado.getString("nombre"),
                        resultado.getString("telefono"),
                        resultado.getString("correo"),
                        resultado.getString("direccion")
                );
                listaClientes.add(c);
            }
        } catch (SQLException e){
            System.out.println("Error al obtener la lista de clientes: " + e.getMessage());
        }
        return listaClientes;
    }

    @Override
    public Optional<Cliente> buscarPorId(int id) {
        String query = "SELECT id_cliente, nombre, telefono, correo, direccion FROM clientes WHERE id_cliente = ?";

        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
                ){
            ps.setInt(1, id);
            try (ResultSet resultado = ps.executeQuery()){
                if (resultado.next()){
                    Cliente c = new Cliente(
                            resultado.getInt("id_cliente"),
                            resultado.getString("nombre"),
                            resultado.getString("telefono"),
                            resultado.getString("correo"),
                            resultado.getString("direccion")
                    );
                    return Optional.of(c);
                }
            }
        } catch (SQLException e){
            System.err.println("Error al buscar al cliente por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        String query = """
                    UPDATE clientes SET nombre = ?, telefono = ?, correo = ?, direccion = ?
                    WHERE id_cliente = ?
                    """;
        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getDireccion());
            ps.setInt(5, cliente.getId());
            //Guardar cambios
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean eliminar(int id) {
        String query = "DELETE FROM clientes WHERE id_cliente = ?";

        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}
