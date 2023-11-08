package es.unex.cum.tw.ef2.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import es.unex.cum.tw.ef2.model.Usuario;
import es.unex.cum.tw.ef2.service.UsuarioService;
import es.unex.cum.tw.ef2.service.UsuarioServiceBD;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {

	private UsuarioService usuarioServicio = new UsuarioServiceBD();// Lo construyo como BD

	public void init(ServletConfig config) throws ServletException {
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accion = req.getParameter("action");
		// aquí, hay que ver el nombre del action del botón
		if (accion.equals("UsuarioLogin")) {
			doLogin(req, res);
		} else if (accion.equals("UsuarioAlta")) {
			darAlta(req, res);
		} else if (accion.equals("verUsuarios")) {
			verUsuarios(req, res);
		} else if (accion.equals("ModificarUsuario")) {
			modUsuario(req, res);
		} else if (accion.equals("UpdateUsuario")) {
			updateUsuario(req, res);
		} else if (accion.equals("BorrarUsuario")) {
			deleteUsuario(req, res);
		} else if (accion.equals("UsuarioCrear")) {
			crearUsuario(req, res);
		} else {
			req.getRequestDispatcher("Error.jsp?error=No hay acción").forward(req, res);
		}

	}

	public void crearUsuario(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String n = req.getParameter("nombre");
		String apel = req.getParameter("apellidos");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String pass = req.getParameter("password");
		Usuario usuario1 = new Usuario(-1, n, apel, email, username, pass, "user");

		try {
			boolean result = usuarioServicio.registroUsuario(usuario1);
			if (result) {
				req.setAttribute("mensaje", "Dado de alta correctamente");
				req.getRequestDispatcher("./WEB-INF/Administrador.jsp").forward(req, res);
				return;
			} else {
				req.getRequestDispatcher("Error.jsp?error=El usuario ya existe").forward(req, res);
				;
				return;
			}
		} catch (Exception e2) {
			// Error SQL: login/passwd mal
			req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
		}
	}

	public void deleteUsuario(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idUser = (String) req.getParameter("idUser");

		try {
			Usuario aux = usuarioServicio.getUsuarioId(idUser);
			boolean result = usuarioServicio.deleteUsuario(aux);
			if (result) {
				req.setAttribute("mensaje", "Usuario borrado correctamente");
				req.getRequestDispatcher("./WEB-INF/Administrador.jsp").forward(req, res);
			} else {
				req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en borrado").forward(req, res);
			}
		} catch (Exception e) {
			req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
		}
	}

	public void updateUsuario(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = (String) req.getParameter("idUser");
		String n = req.getParameter("nombre");
		String apel = req.getParameter("apellidos");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String pass = req.getParameter("password");

		try {

			Usuario aux = new Usuario(Integer.parseInt(id), n, apel, email, username, pass, "user");

			boolean result = usuarioServicio.updateUsuario(aux);
			if (result) {
				req.setAttribute("mensaje", "Usuario actualizado correctamente");
				req.getRequestDispatcher("./WEB-INF/Administrador.jsp").forward(req, res);
			} else {
				req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en actualizar usuario").forward(req, res);
			}

		} catch (Exception e) {
			req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
		}

	}

	public void modUsuario(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String idUser = (String) req.getParameter("idUser");
		try {

			Usuario aux = usuarioServicio.getUsuarioId(idUser);
			if (aux == null) {
				req.getRequestDispatcher("Error.jsp?error=ERROR:Usuario null").forward(req, res);
			} else {
				req.setAttribute("modisuario", aux);
				req.getRequestDispatcher("./WEB-INF/ModificarUsuario.jsp").forward(req, res);
			}
		} catch (Exception e) {
			req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
		}

	}

	public void verUsuarios(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<Usuario> usuario = usuarioServicio.getListOfUsers();

		try {
			if (usuario == null) {

				req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
			} else {
				
				
				req.setAttribute("listaUsuario", usuario);
				req.getRequestDispatcher("./WEB-INF/Usuarios.jsp").forward(req, res);
			}
		} catch (Exception e) {
			req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
		}
	}

	public void doLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String username = req.getParameter("user");
		String passRecibido = req.getParameter("password");
		try {

			boolean result = usuarioServicio.autenticado(username, passRecibido);
			Usuario user = usuarioServicio.getUsuarioNick(username);
			if (result == true) {

				HttpSession session = req.getSession(true);
				session.setAttribute("id", String.valueOf(user.getId()));
				session.setAttribute("nombre", user.getNombre());
				session.setAttribute("username", user.getNick());
				session.setAttribute("tipo", user.getTipo());
				req.setAttribute("mensaje", "Autenticacion correcta");
				if (user.getTipo().equals("user")) {

					// Aquí es donde se reidirige a la "pantalla principal" de la página web para un
					// usuario
					req.getRequestDispatcher("./WEB-INF/Principal.jsp").forward(req, res);
				} else {

					req.getRequestDispatcher("./WEB-INF/Administrador.jsp").forward(req, res);
				}
			} else {
				res.sendRedirect("Error.jsp?error=Error en Password");
				return;
			}
		} catch (Exception e2) {
			// Error SQL: login/passwd mal
			req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
		}
	}

	public void darAlta(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String n = req.getParameter("nombre");
		String apel = req.getParameter("apellidos");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String pass = req.getParameter("password");
		Usuario usuario1 = new Usuario(-1, n, apel, email, username, pass, "user");

		try {
			boolean result = usuarioServicio.registroUsuario(usuario1);
			if (result) {
				req.setAttribute("mensaje", "Dado de alta correctamente");
				req.getRequestDispatcher("./Login.jsp").forward(req, res);
				return;
			} else {
				req.getRequestDispatcher("Error.jsp?error=El usuario ya existe").forward(req, res);
				;
				return;
			}
		} catch (Exception e2) {
			// Error SQL: login/passwd mal
			req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}

	@Override
	public void destroy() {
	}
}
