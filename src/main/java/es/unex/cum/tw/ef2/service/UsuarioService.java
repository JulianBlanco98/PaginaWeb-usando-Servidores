package es.unex.cum.tw.ef2.service;

import java.util.List;

import es.unex.cum.tw.ef2.model.Usuario;

/**
 * Interfaz de Usuario. Modelo CRG
 * 
 * @author julian
 *
 */
public interface UsuarioService {

	public boolean registroUsuario(Usuario usuario);// Create

	public boolean autenticado(String nick, String contra); // Read

	public Usuario getUsuarioNick(String nick); // Read

	public Usuario getUsuarioId(String idUsuario); // Read

	public List<Usuario> getListOfUsers(); // Gettter
	
	public boolean updateUsuario(Usuario usuario);
	
	public boolean deleteUsuario(Usuario usuario);

}
