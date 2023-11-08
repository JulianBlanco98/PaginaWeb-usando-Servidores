package es.unex.cum.tw.ef2.model;

import java.util.Objects;

public class Usuario {

	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String nick;
	private String contra;
	private String tipo;
	
	public Usuario() {
		id=0;
		nombre="";
		apellidos="";
		email="";
		nick="";
		contra="";
		tipo="";
	}

	public Usuario(int id, String nombre, String apellidos, String email, String nick, String contra, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nick = nick;
		this.contra = contra;
		this.tipo=tipo;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}
	
	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, contra, email, id, nick, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(contra, other.contra)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(nick, other.nick)
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", nick="
				+ nick + ", contra=" + contra + "]";
	}
	
	
}
