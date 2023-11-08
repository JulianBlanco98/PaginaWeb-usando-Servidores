package es.unex.cum.tw.ef2.model;

import java.util.Date;
import java.util.Objects;

public class Reserva {

	private int idUsuario;
	private int idRuta;
	private String fechaReserva;
	private int numeroPersonas;
	
	public Reserva() {
		idUsuario=0;
		idRuta=0;
		fechaReserva = "";
		numeroPersonas=0;
	}

	public Reserva(int idUsuario, int idRuta, String fechaReserva, int numeroPersonas) {
		super();
		this.idUsuario = idUsuario;
		this.idRuta = idRuta;
		this.fechaReserva = fechaReserva;
		this.numeroPersonas = numeroPersonas;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public int getNumeroPersonas() {
		return numeroPersonas;
	}

	public void setNumeroPersonas(int numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaReserva, idRuta, idUsuario, numeroPersonas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(fechaReserva, other.fechaReserva) && idRuta == other.idRuta
				&& idUsuario == other.idUsuario && numeroPersonas == other.numeroPersonas;
	}

	@Override
	public String toString() {
		return "Reserva [idUsuario=" + idUsuario + ", idRuta=" + idRuta + ", fechaReserva=" + fechaReserva
				+ ", numeroPersonas=" + numeroPersonas + "]";
	}
	
	
}
