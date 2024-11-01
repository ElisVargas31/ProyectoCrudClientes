package clientes;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import Dao.ConexionBD;
import Dao.UsuarioDAO;


public class Main {
    public static void main(String[] args) throws NamingException {
    	   // Crear una instancia de ConexionBD
        ConexionBD conex = new ConexionBD();

        // Establecer la conexión
        Connection conexion = null;
		try {
			conexion = conex.conectar();
		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
  
        UsuarioDAO usuariodao= new UsuarioDAO();
        usuariodao.guardarUsuario("karen", "Vargas", "999");
        //usuariodao.actualizarUsuario("Elis", "Muñoz", "1234568");
        //usuariodao.eliminarUsuario( "1234568");
        // Hacer algo con la conexión (por ejemplo, realizar consultas)
        // Aquí podrías agregar código para realizar operaciones en la base de datos.

        // Cerrar la conexión
        conex.cerrarConexion(conexion);
    }
}
