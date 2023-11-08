package es.unex.cum.tw.ef2.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.tw.ef2.model.Reserva;
import es.unex.cum.tw.ef2.model.Reservas;
import util.ConexionUtil;

public class ReservaServiceBD implements ReservaService {

	@Override
	public boolean createReserva(Reserva r) {

		ResultSet resultados = null;
		Reserva aux = getReservaFecha(r.getIdUsuario(), r.getIdRuta(), r.getFechaReserva());

		if (aux != null) {
			return false;
		}

		try {
			String query = "INSERT INTO reservas (idUsuario,idRuta,fechaReserva,numeroPersonas) " + "VALUES (?,?,?,?)";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			synchronized (sentencia) {

				sentencia.setInt(1, r.getIdUsuario());
				sentencia.setInt(2, r.getIdRuta());
				sentencia.setString(3, r.getFechaReserva());
				sentencia.setInt(4, r.getNumeroPersonas());
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
	public boolean deleteReserva(Reserva r) {
		// TODO Auto-generated method stub

		Reserva aux = getReservaFecha(r.getIdUsuario(), r.getIdRuta(), r.getFechaReserva());
		ResultSet resultados = null;

		// Si es null, es que no existe el id
		if (aux == null) {
			return false;
		}
		try {
			String query = "DELETE FROM reservas where idUsuario=? and idRuta=? and fechaReserva=?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setInt(1, aux.getIdUsuario());
			sentencia.setInt(2, aux.getIdRuta());
			sentencia.setString(3, aux.getFechaReserva());
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

	/**
	 * Método privado para devolver una reserva según el idUsuario
	 * 
	 * @param id: entero del idUsuario para la ruta
	 * @return la reserva con el idUsuario
	 */
	public Reserva getReserva(int idUsuario, int idRuta) {

		Reserva aux = null;
		ResultSet resultados = null;
		try {
			String query = "SELECT * FROM reservas where idUsuario=? and idRuta=?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setInt(1, idUsuario);
			sentencia.setInt(2, idRuta);
			resultados = sentencia.executeQuery();
			if (resultados.next() == false) {
				return null;
			} else {
				aux = new Reserva(Integer.parseInt(resultados.getString(1)), Integer.parseInt(resultados.getString(2)),
						resultados.getString(3), Integer.parseInt(resultados.getString(4)));

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

	public Reserva getReservaFecha(int idUsuario, int idRuta, String fecha) {

		Reserva aux = null;
		ResultSet resultados = null;
		try {
			String query = "SELECT * FROM reservas WHERE idUsuario=? AND idRuta=? AND fechaReserva=?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setInt(1, idUsuario);
			sentencia.setInt(2, idRuta);
			sentencia.setString(3, fecha);
			resultados = sentencia.executeQuery();
			if (resultados.next() == false) {
				return null;
			} else {
				aux = new Reserva(Integer.parseInt(resultados.getString(1)), Integer.parseInt(resultados.getString(2)),
						resultados.getString(3), Integer.parseInt(resultados.getString(4)));

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
	public List<Reservas> getReservas(int idUsuario) {

		ResultSet resultados = null;
		List<Reservas> listaReserva = new ArrayList<>();
		try {
			String query = "SELECT  ruta.idRuta, ruta.pathImagen, ruta.nombreRuta, reservas.fechaReserva, reservas.numeroPersonas "
					+ "FROM reservas " + "INNER JOIN ruta ON reservas.idRuta = ruta.idRuta " + "WHERE idUsuario = ?";
			PreparedStatement sentencia = ConexionUtil.openConnection().prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia.setInt(1, idUsuario);
			resultados = sentencia.executeQuery();

			if (!resultados.next()) {
				return null;
			} else {

				listaReserva = new ArrayList<>();
				resultados.beforeFirst();
				while (resultados.next()) {
					listaReserva.add(new Reservas(Integer.parseInt(resultados.getString(1)), resultados.getString(2),
							resultados.getString(3), resultados.getString(4),
							Integer.parseInt(resultados.getString(5))));
				}
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
		return listaReserva;

	}

	@Override
	public boolean updateReserva(Reserva r) {

		Reserva aux = getReservaFecha(r.getIdUsuario(), r.getIdRuta(), r.getFechaReserva());

		ResultSet resultados = null;
		if (aux == null) {
			return false;
		}

		try {
			String query = "UPDATE reservas SET numeroPersonas = ? WHERE idUsuario = ? AND idRuta = ? and fechaReserva=?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			synchronized (sentencia) {

				sentencia.setInt(1, r.getNumeroPersonas());
				sentencia.setInt(2, r.getIdUsuario());
				sentencia.setInt(3, r.getIdRuta());
				sentencia.setString(4, r.getFechaReserva());

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
	public int getNumeroPersonas(int idRuta, String fecha) {

		int numeroPersonas = 0;

		ResultSet resultados = null;

		try {
			String query = "SELECT SUM(numeroPersonas) AS total_personas " +
                    "FROM reservas r " +
                    "INNER JOIN ruta u ON u.idRuta = r.idRuta " +
                    "WHERE r.fechaReserva = ? AND u.idRuta = ?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			
			sentencia.setString(1, fecha);
			sentencia.setInt(2, idRuta);
			resultados = sentencia.executeQuery();
			
			if(resultados.next()) {
				
				numeroPersonas=resultados.getInt("total_personas");
				
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

		return numeroPersonas;
	}

}
