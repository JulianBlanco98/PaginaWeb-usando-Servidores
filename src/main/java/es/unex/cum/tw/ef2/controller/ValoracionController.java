package es.unex.cum.tw.ef2.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import es.unex.cum.tw.ef2.model.Ruta;
import es.unex.cum.tw.ef2.model.Valoracion;
import es.unex.cum.tw.ef2.model.ValoracionMedia;
import es.unex.cum.tw.ef2.service.RutaService;
import es.unex.cum.tw.ef2.service.RutaServiceBD;
import es.unex.cum.tw.ef2.service.ValoracionService;
import es.unex.cum.tw.ef2.service.ValoracionServiceBD;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/ValoracionController")
public class ValoracionController extends HttpServlet{

	private ValoracionService valoracion = new ValoracionServiceBD();
	private RutaService ruta = new RutaServiceBD();
	@Override
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
	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, SQLException {

		String accion = req.getParameter("action");
		if (accion.equals("VerValoracion")) {
			verValor(req,res);
		}
		else if(accion.equals("addValor")){
			
			addValor(req, res);
		}
		/*else if (accion.equals("VerDif")) {
			listarDificultad(req, res);
		}*/
		
		 else {
			req.getRequestDispatcher("./WEB-INF/Principal.jsp?mensaje=No hay acción").forward(req, res);
		}

	}
	public void verValor(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession sesion = req.getSession();
		String id=(String) sesion.getAttribute("id");
		String idRuta=(String)req.getParameter("idRuta");
		
		
		try {
			
		Valoracion aux = valoracion.getValoracion(Integer.parseInt(idRuta),Integer.parseInt(id));
		Ruta aux2=ruta.getIdRuta(Integer.parseInt(idRuta));
		List<ValoracionMedia>media=valoracion.getValoracionMedia();
		
		//Si no existe valoración, va a la página de añadir valoración
		if(aux == null) {
			
			req.setAttribute("idRutaV", idRuta);
			req.getRequestDispatcher("./WEB-INF/InsertarValoracion.jsp").forward(req, res);
		}
		else {
			
			req.setAttribute("v_usuario", aux);
			req.setAttribute("rutaaux", aux2);
			req.setAttribute("mediaLista", media);
			req.getRequestDispatcher("./WEB-INF/Valoracion.jsp").forward(req, res);
			
		}
		}
		catch(Exception e) {
			req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
		}
		
		
		
	}
	public void addValor(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = req.getSession();
		String id=(String) sesion.getAttribute("id");
		String idRuta=(String)req.getParameter("idRutaV");
		String coment=(String)req.getParameter("comentario");
		String num_valoracion= req.getParameter("valoracion");
		
		Valoracion aux= new Valoracion(-1, Integer.parseInt(idRuta), Integer.parseInt(id), coment, Integer.parseInt(num_valoracion));
		
		try {
			boolean result=valoracion.createValoracion(aux);
			if(result) {
				req.setAttribute("mensaje", "Introducido correctamente la valoración");
				req.getRequestDispatcher("./WEB-INF/Principal.jsp").forward(req, res);
			}
			else {
				req.getRequestDispatcher("Error.jsp?error=El usuario ya existe").forward(req, res);;
				return;
			}
		}
		catch(Exception e) {
			req.getRequestDispatcher("Error.jsp?ERROR:Fallo en SQL").forward(req, res);
		}
		
		
	}
	
}
