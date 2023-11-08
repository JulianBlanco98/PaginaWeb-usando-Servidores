package es.unex.cum.tw.ef2.service;

import java.util.List;

import es.unex.cum.tw.ef2.model.Valoracion;
import es.unex.cum.tw.ef2.model.ValoracionMedia;

/**
 * Interfaz para añadir valoraciones
 * @author julian
 *
 */
public interface ValoracionService {

	public boolean createValoracion(Valoracion v);
	public boolean deleteValoracion(Valoracion v);
	public Valoracion getValoracion(int idRuta, int idUsuario);
	public List <Valoracion> getValoracionLista(int id);
	public List <ValoracionMedia> getValoracionMedia();
	
}
