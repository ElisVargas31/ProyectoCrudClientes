package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Dao.UsuarioDAO;
import clientes.Usuario;

@WebServlet("/simpleServlet") // Asegúrate de que la URL del servlet esté correctamente mapeada
public class SimpleServlet extends HttpServlet {
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO(); // Asegúrate de tener una instancia de UsuarioDAO

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios(); // Implementa este método en UsuarioDAO

        // Convertir la lista de usuarios a JSON
        Gson gson = new Gson();
        String json = gson.toJson(usuarios);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Acción recibida: " + action); // Para depurar

        if (action != null) {
            switch (action) {
                case "guardar":
                    guardarUsuario(request, response);
                    break;
                case "modificar":
                    modificarUsuario(request, response);
                    break;
                case "eliminar":
                    eliminarUsuario(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se ha especificado ninguna acción");
        }
    }

    private void guardarUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String identificacion = request.getParameter("identificacion");

        usuarioDAO.guardarUsuario(nombre, apellido, identificacion);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Usuario registrado exitosamente</h1>");
        out.println("</body></html>");
    }

    private void modificarUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String identificacion = request.getParameter("identificacion");
        Usuario usu = new Usuario(id, nombre, apellido, identificacion);
        usuarioDAO.modificarUsuario(usu);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("Inserción exitosa");

    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        usuarioDAO.eliminarUsuario(id);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Usuario eliminado exitosamente</h1>");
        out.println("</body></html>");
    }
}
