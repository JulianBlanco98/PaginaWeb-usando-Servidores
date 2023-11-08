package es.unex.cum.tw.ef2.model;

public class Reservas {

	private int idRuta;
	private String pathImagen;
	private String nombreRuta;
	private String fechaReserva;
	private int numPersonas;
	
	public Reservas() {
		idRuta=0;
		pathImagen="";
		nombreRuta="";
		fechaReserva="";
		numPersonas=0;
	}

	public Reservas(int idRuta, String pathImagen, String nombreRuta, String fechaReserva, int numPersonas) {
		super();
		this.idRuta=idRuta;
		this.pathImagen = pathImagen;
		this.nombreRuta = nombreRuta;
		this.fechaReserva = fechaReserva;
		this.numPersonas = numPersonas;
	}
	

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public String getPathImagen() {
		return pathImagen;
	}

	public void setPathImagen(String pathImagen) {
		this.pathImagen = pathImagen;
	}

	public String getNombreRuta() {
		return nombreRuta;
	}

	public void setNombreRuta(String nombreRuta) {
		this.nombreRuta = nombreRuta;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}
	
}
