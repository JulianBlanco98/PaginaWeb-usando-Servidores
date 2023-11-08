package es.unex.cum.tw.ef2.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import es.unex.cum.tw.ef2.model.Reserva;
import es.unex.cum.tw.ef2.model.Reservas;
import es.unex.cum.tw.ef2.model.Ruta;
import es.unex.cum.tw.ef2.service.ReservaService;
import es.unex.cum.tw.ef2.service.ReservaServiceBD;
import es.unex.cum.tw.ef2.service.RutaService;
import es.unex.cum.tw.ef2.service.RutaServiceBD;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/ReservaController")
public class ReservaController extends HttpServlet {

	private ReservaService reserva = new ReservaServiceBD();
	private RutaService ruta = new RutaServiceBD();

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			processRequest(request, response);
		} catch (IOException | ServletException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (IOException | ServletException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, SQLException {

		String accion = req.getParameter("action");
		if (accion.equals("VerReserva")) {
			verReserva(req, res);
		} else if (accion.equals("addReserva")) {
			addReserva(req, res);
		} else if (accion.equals("VerTodas")) {
			listarTodasReservas(req, res);
		} else if (accion.equals("ModificarReserva")) {
			modReserva(req, res);
		} else if (accion.equals("updateReserva")) {
			updateReserva(req, res);
		}
		else if(accion.equals("BorrarReserva")) {
			borrarReserva(req,res);
		}

		else {
			req.getRequestDispatcher("./WEB-INF/Principal.jsp?mensaje=No hay acción").forward(req, res);
		}

	}

	public void borrarReserva(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = req.getSession();
		String id = (String) sesion.getAttribute("id");
		String idRuta = (String) req.getParameter("idRuta");
		
		try {
			Reserva aux = reserva.getReserva(Integer.parseInt(id), Integer.parseInt(idRuta));
			boolean result=reserva.deleteReserva(aux);
			if(!result) {
				req.getRequestDispatcher("Error.jsp?error=la reserva no existe").forward(req, res);
				;
				return;
			}
			else {
				req.setAttribute("mensaje ", "Borrado de Reserva Correcto");
				req.getRequestDispatcher("./WEB-INF/Principal.jsp").forward(req, res);
			}
		}
		catch(Exception e) {
			req.getRequestDispatcher("Error.jsp?ERROR:Fallo en SQL").forward(req, res);
		}
	}

	public void updateReserva(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = req.getSession();
		String id = (String) sesion.getAttribute("id");
		String idRuta = (String) req.getParameter("idRutaV");
		String fecha = (String) req.getParameter("fecha");
		String numeroPersonas = (String) req.getParameter("numPersonas");
		
		try {
			
			boolean comprobacion = comprobarNumeroPersonas(Integer.parseInt(idRuta), fecha, Integer.parseInt(numeroPersonas));
			
			if(comprobacion) {
				
				Reserva aux = new Reserva(Integer.parseInt(id), Integer.parseInt(idRuta), fecha,
						Integer.parseInt(numeroPersonas));
				boolean result = reserva.updateReserva(aux);
				if (result) {
					req.setAttribute("mensaje", "Modificado correctamente la reserva");
					req.getRequestDispatcher("./WEB-INF/Principal.jsp").forward(req, res);
				} else {
					req.getRequestDispatcher("Error.jsp?error=El usuario ya existe").forward(req, res);
					;
					return;
				}
			}
			else {
				req.getRequestDispatcher("Error.jsp?error=Se sobrepasa el número máximo de Usuarios. El límite es: "+ruta.getNumeroMaximoPersonas(Integer.parseInt(numeroPersonas))).forward(req, res);
			}
			

		} catch (Exception e) {
			req.getRequestDispatcher("Error.jsp?ERROR:Fallo en SQL").forward(req, res);
		}

	}

	public void modReserva(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = req.getSession();
		String id = (String) sesion.getAttribute("id");
		String idRuta = (String) req.getParameter("idRuta");

		try {
			Reserva aux = reserva.getReserva(Integer.parseInt(id), Integer.parseInt(idRuta));
			if(aux == null) {
				req.setAttribute("mensaje", "No hay reservas");
				req.getRequestDispatcher("./WEB-INF/Principal.jsp").forward(req, res);
				
			}
			else {
				//req.setAttribute("idRutaV", idRuta);
				req.setAttribute("reserva", aux);
				req.getRequestDispatcher("./WEB-INF/ModificarReserva.jsp").forward(req, res);
				
			}

		} catch (Exception e) {
			req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
		}

	}

	public void listarTodasReservas(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = req.getSession();
		String id = (String) sesion.getAttribute("id");

		List<Reservas> listaReservas = reserva.getReservas(Integer.parseInt(id));
		if (listaReservas == null) {
			req.getRequestDispatcher(
					"./WEB-INF/Principal.jsp?mensaje=ERRORddddddddddddddddddddddddddddddddddddddddddddddddd: No hay productos")
					.forward(req, res);
			
		} else {
			req.setAttribute("listaReservas", listaReservas);
			req.getRequestDispatcher("./WEB-INF/VerReservas.jsp").forward(req, res);
		}
	}

	public void addReserva(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession sesion = req.getSession();
		String id = (String) sesion.getAttribute("id");
		String idRuta = (String) req.getParameter("idRutaV");
		String fecha = (String) req.getParameter("fecha");
		String numeroPersonas = (String) req.getParameter("numPersonas");

		try {
			Reserva aux = new Reserva(Integer.parseInt(id), Integer.parseInt(idRuta), fecha,
					Integer.parseInt(numeroPersonas));
			
			
			//Ver si el número de personas excede el maxPersonas de la ruta
			
			boolean comprobacion = comprobarNumeroPersonas(Integer.parseInt(idRuta), fecha, Integer.parseInt(numeroPersonas));
			
			if(comprobacion) {
				
				boolean result = reserva.createReserva(aux);
				if (result) {
					req.setAttribute("mensaje", "Introducido correctamente la reserva");
					req.getRequestDispatcher("./WEB-INF/Principal.jsp").forward(req, res);
				} else {
					req.getRequestDispatcher("Error.jsp?error=Ese día ya está cogido").forward(req, res);
					;
					return;
				}
				
			}
			else {
				req.getRequestDispatcher("Error.jsp?error=Se supera el máximo de personas que tiene la ruta").forward(req, res);
			}
			

		} catch (Exception e) {
			req.getRequestDispatcher("Error.jsp?ERROR:Fallo en SQL").forward(req, res);
		}

	}

	public void verReserva(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession sesion = req.getSession();
		String idRuta = (String) req.getParameter("idRuta");

		
		try {
			Ruta aux2 = ruta.getIdRuta(Integer.parseInt(idRuta));
			//Reserva aux = reserva.getReserva(Integer.parseInt(id), Integer.parseInt(idRuta));

			// Si no existe la reserva, va a la página de hacer la reserva
			
				req.setAttribute("ruta", aux2);
				req.getRequestDispatcher("./WEB-INF/InsertarReserva.jsp").forward(req, res);
			 /*else {
				req.setAttribute("reserva_usuario", aux);
				req.setAttribute("rutaaux", aux2);
				req.getRequestDispatcher("./WEB-INF/Reserva.jsp").forward(req, res);
			}*/

		} catch (Exception e) {
			req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
		}
	}
	
	/**
	 * Método privado para comprobar si a la hora de hacer o modificar una reserva, el número de usuarios incluidos en la reserva
	 * no sobrepasa el número máximo de Usuarios de la ruta.
	 * @param idRuta: entero con la ruta
	 * @param fecha: string de la fecha
	 * @param personasReserva: entero con las personas introducidas en el formulario
	 * @return true si se puede hacer o modificar, false si no
	 */
	private boolean comprobarNumeroPersonas(int idRuta, String fecha, int personasReserva) {
		
		
		int numeroPersonasReserva=0;
		int maximoPersonasRuta=0;
		int total=0;
		//Conseguir el número de Personas de una fecha en una ruta
		
		numeroPersonasReserva=reserva.getNumeroPersonas(idRuta, fecha);
		
		//Conseguir el número de Personas totales que tiene esa ruta
		
		maximoPersonasRuta=ruta.getNumeroMaximoPersonas(idRuta);
		
		total = numeroPersonasReserva+personasReserva;
		
		//si el total (personas ya con la reserva + la nueva reserva) supera el total de la ruta, devuelve false, es decir, no se puede hacer
		
		if(total > maximoPersonasRuta) {
			
			return false;
		}
		else {
			
			return true;
		}
		
	}

}
