package es.unex.cum.tw.ef2.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unex.cum.tw.ef2.model.Usuario;
import util.ConexionUtil;

public class UsuarioServiceBD implements UsuarioService{

	@Override
	public boolean registroUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
		ResultSet resultados = null;
		if(getUsuarioNick(usuario.getNick()) != null) {
			return false;
		}
		
		try {
			String query ="INSERT INTO usuarios (nombre,apellidos,email,username,password)"
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			synchronized (sentencia) {
				sentencia.setString(1, usuario.getNombre());
				sentencia.setString(2, usuario.getApellidos());
				sentencia.setString(3, usuario.getEmail());
				sentencia.setString(4, usuario.getNick());
				sentencia.setString(5, usuario.getContra());
				sentencia.executeUpdate();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					/*Logger.getLogger(ProductosController.class.getName()).log(
							Level.SEVERE, "No se pudo cerrar el Resulset", ex);*/
				}
			}
		}
		
		
		return true;
	}

	@Override
	public boolean autenticado(String nick, String contra) {
		// TODO Auto-generated method stub
		
		Usuario aux = getUsuarioNick(nick);
		if(aux != null && aux.getNick().equals(nick) && aux.getContra().equals(contra)) {
			return true;
		}
		else {
			return false;
		}
		
		
	}

	@Override
	public Usuario getUsuarioNick(String nick) {
		// TODO Auto-generated method stub
		
		Usuario aux = null;
		ResultSet resultados = null;
		try {
			String query="SELECT * FROM usuarios where username=?";
			PreparedStatement sentencia=(PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setString(1,nick);
			resultados = sentencia.executeQuery();
			if(resultados.next() == false) {
				return null;
			}
			else {
				aux = new Usuario(Integer.parseInt(resultados.getString("id")), 
						resultados.getString("Nombre"),
						resultados.getString("Apellidos"),
						resultados.getString("email"),
						resultados.getString("username"),
						resultados.getString("password"),
						resultados.getString("tipo"));
				
				return aux;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Se cierra resultSet
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					/*Logger.getLogger(ProductosController.class.getName()).log(
							Level.SEVERE, "No se pudo cerrar el Resulset", ex);*/
				}
			}
		}
		return aux;
		
		
	}

	@Override
	public Usuario getUsuarioId(String idUsuario) {
		// TODO Auto-generated method stub
		
		Usuario aux = null;
		ResultSet resultados = null;
		try {
			String query="SELECT * FROM usuarios where id=?";
			PreparedStatement sentencia=(PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setString(1,idUsuario);
			resultados = sentencia.executeQuery();
			if (resultados.next() == false) {
				return null;
			} else {
				aux = new Usuario(
						Integer.parseInt(resultados.getString("id")),
						resultados.getString("Nombre"),
						resultados.getString("Apellidos"),
						resultados.getString("email"),
						resultados.getString("username"),
						resultados.getString("password"),
						resultados.getString("tipo"));

				return aux;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Se cierra resultSet
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					/*Logger.getLogger(ProductosController.class.getName()).log(
							Level.SEVERE, "No se pudo cerrar el Resulset", ex);*/
				}
			}
		}
		return aux;
	}

	@Override
	public List<Usuario> getListOfUsers() {
		// TODO Auto-generated method stub
		
		Usuario aux = null;
		ResultSet resultados = null;
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			String query="SELECT * FROM usuarios WHERE tipo = 'user'";
			PreparedStatement sentencia=(PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			resultados = sentencia.executeQuery();

			while (resultados.next()) {
				aux = new Usuario(
						Integer.parseInt(resultados.getString("id")),
						resultados.getString("Nombre"),
						resultados.getString("Apellidos"),
						resultados.getString("email"),
						resultados.getString("username"),
						resultados.getString("password"),
						resultados.getString("tipo"));
				listaUsuarios.add(aux);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Se cierra resultSet
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					/*Logger.getLogger(ProductosController.class.getName()).log(
							Level.SEVERE, "No se pudo cerrar el Resulset", ex);*/
				}
			}
		}
		return listaUsuarios;
		
	}

	@Override
	public boolean updateUsuario(Usuario usuario) {
		
		Usuario aux = getUsuarioId(String.valueOf(usuario.getId()));
		ResultSet resultados = null;
		
		if(aux == null) {
			return false;
		}
		try {
			String query ="UPDATE usuarios "
					+ "SET nombre = ? , apellidos = ? , email = ? , username = ? , password = ? "
					+ "WHERE id = ?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			synchronized (sentencia) {
				sentencia.setString(1, usuario.getNombre());
				sentencia.setString(2, usuario.getApellidos());
				sentencia.setString(3, usuario.getEmail());
				sentencia.setString(4, usuario.getNick());
				sentencia.setString(5, usuario.getContra());
				sentencia.setInt(6, usuario.getId());
				sentencia.executeUpdate();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					/*
					 * Logger.getLogger(ProductosController.class.getName()).log( Level.SEVERE,
					 * "No se pudo cerrar el Resulset", ex);
					 */
				}
			}
		}

		return true;
		
	}

	@Override
	public boolean deleteUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
		Usuario aux = getUsuarioId(String.valueOf(usuario.getId()));
		ResultSet resultados = null;
		
		if(aux == null) {
			return false;
		}
		try {
			String query = "DELETE FROM usuarios where id=?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setInt(1, aux.getId());
			resultados = sentencia.executeQuery();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if (resultados != null) {
				try {
					resultados.close();
				} catch (SQLException ex) {
					/*
					 * Logger.getLogger(ProductosController.class.getName()).log( Level.SEVERE,
					 * "No se pudo cerrar el Resulset", ex);
					 */
				}
			}
		}

		return true;
		
	}

}
