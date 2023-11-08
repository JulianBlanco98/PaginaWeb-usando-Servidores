package es.unex.cum.tw.ef2.service;

import java.util.List;

import es.unex.cum.tw.ef2.model.Reserva;
import es.unex.cum.tw.ef2.model.Reservas;

public interface ReservaService {

	
	/**
	 * Interfaz de Reserva. Realizar y anular una reserva
	 */
	
	public boolean createReserva(Reserva r);
	public boolean deleteReserva(Reserva r);
	public List<Reservas> getReservas(int idUsuario);
	public Reserva getReserva(int idUsuario, int idRuta);
	public Reserva getReservaFecha(int idUsuario, int idRuta, String fecha);
	public boolean updateReserva(Reserva r);
	public int getNumeroPersonas(int idRuta, String fecha);
	
}
