package es.unex.cum.tw.ef2.service;

import java.sql.SQLException;
import java.util.List;

import es.unex.cum.tw.ef2.model.Ruta;

/**
 * Interfaz de Ruta. Modelo CRD
 * @author julian
 *
 */
public interface RutaService {
	
	public boolean createRuta(Ruta r);
	public boolean deleteRuta(Ruta r);
	public Ruta getIdRuta(int id);
	public int getNumeroMaximoPersonas(int idRuta);
	public List<Ruta> getRutas() throws SQLException;
	public List<Ruta> getRutaNovedades();
	public List<Ruta> getRutaDificultad();
	public List<Ruta> getRutaDistancia();
	

}
