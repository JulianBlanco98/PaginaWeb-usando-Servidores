package es.unex.cum.tw.ef2.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import es.unex.cum.tw.ef2.model.Ruta;
import es.unex.cum.tw.ef2.model.ValoracionMedia;
import es.unex.cum.tw.ef2.service.RutaService;
import es.unex.cum.tw.ef2.service.RutaServiceBD;
import es.unex.cum.tw.ef2.service.ValoracionService;
import es.unex.cum.tw.ef2.service.ValoracionServiceBD;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = "/RutaController")
@MultipartConfig
public class RutaController extends HttpServlet {

	private RutaService ruta = new RutaServiceBD();
	private ValoracionService valorarcion = new ValoracionServiceBD();

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

	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, SQLException {

		
		
		
		String accion = req.getParameter("action");
		if (accion.equals("VerRutas")) {
			listarRutas(req, res);
		} else if (accion.equals("VerDif")) {
			listarDificultad(req, res);
		}
		else if(accion.contains("VerRutasNoLogin")) {
			listarRutasNoLogin(req,res);
		}
		else if(accion.equals("Novedades")) {
			listarNovedades(req, res);
		}
		else if (accion.equals("VerDis")) {
			listarDistancia(req, res);
		} 
		else if (accion.equals("CrearRuta")) {
			addRuta(req, res);
		} 			
		else {
			req.getRequestDispatcher("./WEB-INF/Principal.jsp?mensaje=No hay acción").forward(req, res);
		}

	}

	public void addRuta(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String nombreRuta = req.getParameter("nombreRuta");
        String fecha = req.getParameter("fecha");
        int maxUsuarios = Integer.parseInt(req.getParameter("maxUsuarios"));
        int dificultad = Integer.parseInt(req.getParameter("dificultad"));
        int distancia = Integer.parseInt(req.getParameter("distancia"));
        
        Part filepart = req.getPart("imagen");
        String fileName = filepart.getSubmittedFileName();
        
        String filePath = "../img/"+fileName;
     
        
        Ruta aux = new Ruta(-1, filePath, fecha, maxUsuarios, dificultad, distancia, nombreRuta);
        
        try {
        	boolean result = ruta.createRuta(aux);
        	if(result) {
        		req.setAttribute("mensaje", "Nueva ruta creada correctamente");
				req.getRequestDispatcher("./WEB-INF/Administrador.jsp").forward(req, res);

        	}
        	else {
				req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en crearRuta").forward(req, res);

        	}
        }
        catch(Exception e) {
        	req.getRequestDispatcher("Error.jsp?error=ERROR:Fallo en SQL").forward(req, res);
        }
		
	}

	private void listarRutasNoLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<Ruta> rutas = ruta.getRutas();
			List<ValoracionMedia> media = valorarcion.getValoracionMedia();
			if(rutas == null) {
				req.getRequestDispatcher("Inicio.jsp?mensaje=ERROR: No hay rutas").forward(req, res);
				return;
			}
			else {
				req.setAttribute("listaRutaNoLogin", rutas);
				req.setAttribute("medias", media);
				req.getRequestDispatcher("Rutas.jsp").forward(req, res);
			}
		}
		catch(SQLException ex) {
			req.getRequestDispatcher("Inicio.jsp?mensaje=Error en la consulta. Hable con administrador").forward(req, res);
		}
	}

	public void listarRutas(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			List<Ruta> rutas = ruta.getRutas();
			List<ValoracionMedia> media = valorarcion.getValoracionMedia();
			if(rutas == null) {
				req.getRequestDispatcher("./WEB-INF/Principal.jsp?mensaje=ERRORddddddddddddddddddddddddddddddddddddddddddddddddd: No hay productos").forward(req, res);
				return;
			}
			else {
				req.setAttribute("listaRuta", rutas);
				req.setAttribute("medias", media);
				req.getRequestDispatcher("./WEB-INF/VerRutas.jsp").forward(req, res);
			}
		}
		catch(SQLException ex) {
			req.getRequestDispatcher("./WEB-INF/Principal.jsp?mensaje=Error en la consulta. Hable con administrador").forward(req, res);
		}
		
	}

	private void listarDistancia(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Ruta> rutas = ruta.getRutaDistancia();
		if(rutas == null) {
			req.getRequestDispatcher("./WEB-INF/Principal.jsp?mensaje=ERRORddddddddddddddddddddddddddddddddddddddddddddddddd: No hay productos").forward(req, res);
			return;
		}
		else {
			req.setAttribute("listaDis", rutas);
			req.getRequestDispatcher("./WEB-INF/VerDistancia.jsp").forward(req, res);
		}
	}

	private void listarDificultad(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, SQLException {
		List<Ruta> rutas = ruta.getRutaDificultad();
		if(rutas == null) {
			req.getRequestDispatcher("./WEB-INF/Principal.jsp?mensaje=ERRORddddddddddddddddddddddddddddddddddddddddddddddddd: No hay productos").forward(req, res);
			return;
		}
		else {
			req.setAttribute("listaDif", rutas);
			req.getRequestDispatcher("./WEB-INF/VerDificultad.jsp").forward(req, res);
		}
	}

	private void listarNovedades(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Ruta> rutas = ruta.getRutaNovedades();
		if(rutas == null) {
			req.getRequestDispatcher("./WEB-INF/Principal.jsp?mensaje=ERRORddddddddddddddddddddddddddddddddddddddddddddddddd: No hay productos").forward(req, res);
			return;
		}
		else {
			req.setAttribute("listaNovedades", rutas);
			req.getRequestDispatcher("./WEB-INF/VerNovedades.jsp").forward(req, res);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (IOException | ServletException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
