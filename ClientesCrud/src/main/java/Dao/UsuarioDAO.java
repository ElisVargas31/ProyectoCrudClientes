package Dao;

import javax.naming.NamingException;
import clientes.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    private ConexionBD conexionBD;

    public UsuarioDAO() {
        try {
            this.conexionBD = new ConexionBD(); // Inicializa la conexión aquí
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void guardarUsuario(String nombre, String apellido, String identificacion) {
        Connection connection = null;
        try {
            connection = ConexionBD.conectar();
            String sql = "INSERT INTO usuarios (nombre, apellido, identificacion) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, identificacion);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                conexionBD.cerrarConexion(connection);
            }
        }
    }

    public List<Usuario> listarUsuarios() { // Cambiado de obtenerUsuarios a listarUsuarios
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConexionBD.conectar();
            String sql = "SELECT * FROM usuarios";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
            	int id=resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String identificacion = resultSet.getString("identificacion");
                usuarios.add(new Usuario(id,nombre, apellido, identificacion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                conexionBD.cerrarConexion(connection);
            }
        }
        return usuarios;
    }
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        Connection connection = null;
        try {
            connection = ConexionBD.conectar();
        	PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0; // Retorna true si se eliminó al menos un usuario
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
            return false; // Retorna false si ocurrió un error
        }
    }

    public boolean modificarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, identificacion = ? WHERE id = ?";
        Connection connection = null;
        try {
            connection = ConexionBD.conectar();
        	PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getIdentificacion());
            statement.setInt(4, usuario.getId()); // Asumiendo que tienes un método getId() en la clase Usuario
            
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0; // Retorna true si se modificó al menos un usuario
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
            return false; // Retorna false si ocurrió un error
        }
    }

}
