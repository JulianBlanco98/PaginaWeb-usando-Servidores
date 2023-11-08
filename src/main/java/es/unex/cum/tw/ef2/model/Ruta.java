package es.unex.cum.tw.ef2.model;

import java.util.Date;
import java.util.Objects;

public class Ruta {

	
	private int idRuta;
	private String nombreRuta;
	private String pathImagen;
	private String fechaIncorporacion;
	private int maximoUsuario;
	private int dificultad;
	private int metros;
	
	public Ruta() {
		idRuta=0;
		nombreRuta="";
		pathImagen="";
		fechaIncorporacion="";
		maximoUsuario=0;
		dificultad=0;
		metros=0;
	}

	public Ruta(int idRuta, String pathImagen, String fechaIncorporacion, int maximoUsuario, int dificultad, int metros, String nombreRuta) {
		super();
		this.idRuta = idRuta;
		this.pathImagen = pathImagen;
		this.fechaIncorporacion = fechaIncorporacion;
		this.maximoUsuario = maximoUsuario;
		this.dificultad = dificultad;
		this.metros = metros;
		this.nombreRuta=nombreRuta;
	}

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public String getNombreRuta() {
		return nombreRuta;
	}

	public void setNombreRuta(String nombreRuta) {
		this.nombreRuta = nombreRuta;
	}

	public String getPathImagen() {
		return pathImagen;
	}

	public void setPathImagen(String pathImagen) {
		this.pathImagen = pathImagen;
	}

	public String getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	public void setFechaIncorporacion(String fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}

	public int getMaximoUsuario() {
		return maximoUsuario;
	}

	public void setMaximoUsuario(int maximoUsuario) {
		this.maximoUsuario = maximoUsuario;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public int getMetros() {
		return metros;
	}

	public void setMetros(int metros) {
		this.metros = metros;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dificultad, fechaIncorporacion, idRuta, maximoUsuario, metros, nombreRuta, pathImagen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ruta other = (Ruta) obj;
		return dificultad == other.dificultad && Objects.equals(fechaIncorporacion, other.fechaIncorporacion)
				&& idRuta == other.idRuta && maximoUsuario == other.maximoUsuario && metros == other.metros
				&& Objects.equals(nombreRuta, other.nombreRuta) && Objects.equals(pathImagen, other.pathImagen);
	}

	@Override
	public String toString() {
		return "Ruta [idRuta=" + idRuta + ", nombreRuta=" + nombreRuta + ", pathImagen=" + pathImagen
				+ ", fechaIncorporacion=" + fechaIncorporacion + ", maximoUsuario=" + maximoUsuario + ", dificultad="
				+ dificultad + ", metros=" + metros + "]";
	}

	
	
	
}
