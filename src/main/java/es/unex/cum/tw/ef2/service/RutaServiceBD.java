package es.unex.cum.tw.ef2.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.tw.ef2.model.Ruta;
import util.ConexionUtil;

public class RutaServiceBD implements RutaService {

	@Override
	public boolean createRuta(Ruta r) {
		// TODO Auto-generated method stub

		ResultSet resultados = null;
		Ruta aux = getIdRuta(r.getIdRuta());

		if (aux != null) {

			return false;

		}
		try {
			String query = "INSERT INTO ruta (pathImagen,fechaIncorporacion,maximoUsuario,dificultad,metros,nombreRuta) "
					+ "VALUES (?,?,?,?,?,?)";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			synchronized (sentencia) {
				sentencia.setString(1, r.getPathImagen());
				sentencia.setString(2, r.getFechaIncorporacion());
				sentencia.setInt(3, r.getMaximoUsuario());
				sentencia.setInt(4, r.getDificultad());
				sentencia.setInt(5, r.getMetros());
				sentencia.setString(6, r.getNombreRuta());
				sentencia.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	public boolean deleteRuta(Ruta r) {
		// TODO Auto-generated method stub

		Ruta aux = getIdRuta(r.getIdRuta());
		ResultSet resultados = null;

		// Si es null, es que no existe el id
		if (aux == null) {
			return false;
		}
		try {
			String query = "DELETE FROM ruta where idRuta=?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setInt(1, aux.getIdRuta());
			resultados = sentencia.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	public List<Ruta> getRutaNovedades() {
		// TODO Auto-generated method stub

		Ruta aux = null;
		ResultSet resultados = null;
		List<Ruta> listaRuta = new ArrayList<>();

		try {
			String query = "SELECT * FROM ruta ORDER BY fechaIncorporacion DESC LIMIT 5";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			resultados = sentencia.executeQuery();

			while (resultados.next()) {
				aux = new Ruta(Integer.parseInt(resultados.getString("idRuta")), resultados.getString("pathImagen"),
						resultados.getString("fechaIncorporacion"),
						Integer.parseInt(resultados.getString("maximoUsuario")),
						Integer.parseInt(resultados.getString("dificultad")),
						Integer.parseInt(resultados.getString("metros")), resultados.getString("nombreRuta"));
				listaRuta.add(aux);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Se cierra resultSet
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
		return listaRuta;
	}

	@Override
	public List<Ruta> getRutaDificultad() {
		// TODO Auto-generated method stub
		Ruta aux = null;
		ResultSet resultados = null;
		List<Ruta> listaRuta = new ArrayList<>();

		try {
			String query = "SELECT * FROM ruta ORDER BY dificultad DESC";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			resultados = sentencia.executeQuery();

			while (resultados.next()) {
				aux = new Ruta(Integer.parseInt(resultados.getString("idRuta")), resultados.getString("pathImagen"),
						resultados.getString("fechaIncorporacion"),
						Integer.parseInt(resultados.getString("maximoUsuario")),
						Integer.parseInt(resultados.getString("dificultad")),
						Integer.parseInt(resultados.getString("metros")), resultados.getString("nombreRuta"));
				listaRuta.add(aux);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Se cierra resultSet
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
		return listaRuta;
	}

	@Override
	public List<Ruta> getRutaDistancia() {
		// TODO Auto-generated method stub

		Ruta aux = null;
		ResultSet resultados = null;
		List<Ruta> listaRuta = new ArrayList<>();

		try {
			String query = "SELECT * FROM ruta ORDER BY metros DESC";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			resultados = sentencia.executeQuery();

			while (resultados.next()) {
				aux = new Ruta(Integer.parseInt(resultados.getString("idRuta")), resultados.getString("pathImagen"),
						resultados.getString("fechaIncorporacion"),
						Integer.parseInt(resultados.getString("maximoUsuario")),
						Integer.parseInt(resultados.getString("dificultad")),
						Integer.parseInt(resultados.getString("metros")), resultados.getString("nombreRuta"));
				listaRuta.add(aux);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Se cierra resultSet
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
		return listaRuta;

	}

	/**
	 * Método privado para devolver una ruta por su id.
	 * 
	 * @param id: entero del número de la ruta
	 * @return ruta
	 */
	public Ruta getIdRuta(int id) {

		Ruta aux = null;
		ResultSet resultados = null;
		try {
			String query = "SELECT * FROM ruta where idRuta=?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setInt(1, id);
			resultados = sentencia.executeQuery();
			if (resultados.next() == false) {
				return null;
			} else {
				aux = new Ruta(Integer.parseInt(resultados.getString("idRuta")), resultados.getString("pathImagen"),
						resultados.getString("fechaIncorporacion"),
						Integer.parseInt(resultados.getString("maximoUsuario")),
						Integer.parseInt(resultados.getString("dificultad")),
						Integer.parseInt(resultados.getString("metros")), resultados.getString("nombreRuta"));

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
					/*
					 * Logger.getLogger(ProductosController.class.getName()).log( Level.SEVERE,
					 * "No se pudo cerrar el Resulset", ex);
					 */
				}
			}
		}
		return aux;

	}

	@Override
	public List<Ruta> getRutas() throws SQLException {
		// TODO Auto-generated method stub

		ResultSet resultados = null;
		List<Ruta> l = null;
		try {

			Statement sentencia = ConexionUtil.openConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			resultados = sentencia.executeQuery("SELECT * FROM ruta");
			if (!resultados.next()) {

				return null;
			} else {
				l = new ArrayList<Ruta>();
				resultados.beforeFirst();
				while (resultados.next()) {
					l.add(new Ruta(Integer.parseInt(resultados.getString("idRuta")), resultados.getString("pathImagen"),
						resultados.getString("fechaIncorporacion"),
						Integer.parseInt(resultados.getString("maximoUsuario")),
						Integer.parseInt(resultados.getString("dificultad")),
						Integer.parseInt(resultados.getString("metros")), resultados.getString("nombreRuta")));
				}
			}

		} catch (SQLException e2) {
			throw e2;
		} finally {

			// Se cierra resultSet
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
		return l;
	}

	@Override
	public int getNumeroMaximoPersonas(int idRuta) {
		
		int numeroPersonasRutas=0;
		ResultSet resultados = null;

		try {
			String query = "SELECT maximoUsuario FROM ruta WHERE idRuta = ?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			
			sentencia.setInt(1, idRuta);
			resultados = sentencia.executeQuery();
			
			if(resultados.next()) {
				
				numeroPersonasRutas=resultados.getInt("maximoUsuario");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		
		return numeroPersonasRutas;
	}

}
