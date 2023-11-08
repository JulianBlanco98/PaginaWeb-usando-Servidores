package es.unex.cum.tw.ef2.model;

import java.util.Objects;

public class Valoracion {
	
	private int idValoracion;
	private int idRuta;
	private int idUsuario;
	private String comentario;
	private int valoracion;
	
	public Valoracion() {
		
		idValoracion=0;
		idRuta=0;
		idUsuario=0;
		comentario="";
		valoracion=0;
	}

	public Valoracion(int idValoracion, int idRuta, int idUsuario, String comentario, int valoracion) {
		super();
		this.idValoracion = idValoracion;
		this.idRuta = idRuta;
		this.idUsuario = idUsuario;
		this.comentario = comentario;
		this.valoracion = valoracion;
	}

	public int getIdValoracion() {
		return idValoracion;
	}

	public void setIdValoracion(int idValoracion) {
		this.idValoracion = idValoracion;
	}

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comentario, idRuta, idUsuario, idValoracion, valoracion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Valoracion other = (Valoracion) obj;
		return Objects.equals(comentario, other.comentario) && idRuta == other.idRuta && idUsuario == other.idUsuario
				&& idValoracion == other.idValoracion && valoracion == other.valoracion;
	}

	@Override
	public String toString() {
		return "Valoracion [idValoracion=" + idValoracion + ", idRuta=" + idRuta + ", idUsuario=" + idUsuario
				+ ", comentario=" + comentario + ", valoracion=" + valoracion + "]";
	}
	

}
