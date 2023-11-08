package es.unex.cum.tw.ef2.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.tw.ef2.model.Valoracion;
import es.unex.cum.tw.ef2.model.ValoracionMedia;
import util.ConexionUtil;

public class ValoracionServiceBD implements ValoracionService {

	@Override
	public boolean createValoracion(Valoracion v) {
		ResultSet resultados = null;
		Valoracion aux = getValoracion(v.getIdRuta(),v.getIdUsuario());

		if (aux != null) {

			return false;

		}
		try {
			String query = "INSERT INTO valoraciones (idUsuario,idRuta,comentario,valoracion) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			synchronized (sentencia) {
				
				sentencia.setInt(1, v.getIdUsuario());
				sentencia.setInt(2, v.getIdRuta());
				sentencia.setString(3, v.getComentario());
				sentencia.setInt(4, v.getValoracion());
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
	public boolean deleteValoracion(Valoracion v) {
		// TODO Auto-generated method stub
		
		Valoracion aux=getValoracion(v.getIdUsuario());
		ResultSet resultados = null;
		
		if(aux == null) {
			return false;
		}
		try {
			String query = "DELETE FROM valoraciones where idUsuario=? and idRuta=?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setInt(1, aux.getIdUsuario());
			sentencia.setInt(2, aux.getIdRuta());
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

	@Override
	public List<Valoracion> getValoracionLista(int id) {
		
		
		Valoracion aux=null;
		ResultSet resultados=null;
		List<Valoracion> listaValoracion= new ArrayList<>();
		
		try {
			String query="SELECT * from valoraciones where idUsuario =?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setInt(1, id);
			resultados = sentencia.executeQuery();
			
			while(resultados.next()) {
				aux = new Valoracion(Integer.parseInt(resultados.getString(1)),
						Integer.parseInt(resultados.getString(2)),
						Integer.parseInt(resultados.getString(3)),
						resultados.getString(4),
						Integer.parseInt(resultados.getString(5)));
				listaValoracion.add(aux);
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
					/*
					 * Logger.getLogger(ProductosController.class.getName()).log( Level.SEVERE,
					 * "No se pudo cerrar el Resulset", ex);
					 */
				}
			}
		}
		return listaValoracion;
		
		
	}

	private Valoracion getValoracion(int id) {

		Valoracion aux = null;
		ResultSet resultados = null;
		try {

			String query="select * from valoraciones where idUsuario =?";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setInt(1, id);
			resultados=sentencia.executeQuery();
			if(resultados.next() == false) {
				return null;
			}
			else {
				aux = new Valoracion(Integer.parseInt(resultados.getString(1)), 
						Integer.parseInt(resultados.getString(2)), 
						Integer.parseInt(resultados.getString(3)), 
						resultados.getString(4), Integer.parseInt(resultados.getString(5)));
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
	public Valoracion getValoracion(int idRuta, int idUsuario) {

		Valoracion aux=null;
		ResultSet resultados=null;
		try {
			String query="select * from valoraciones where idUsuario =? and idRuta =?";
			PreparedStatement sentencia=(PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			sentencia.setInt(1,idUsuario);
			sentencia.setInt(2,idRuta);
			resultados = sentencia.executeQuery();
			if (resultados.next() == false) {
				return null;
			} else {
				aux = new Valoracion(Integer.parseInt(resultados.getString(1)),
						Integer.parseInt(resultados.getString(2)),
						Integer.parseInt(resultados.getString(3)),
						resultados.getString(4),
						Integer.parseInt(resultados.getString(5)));
						
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
	public List<ValoracionMedia> getValoracionMedia() {
		// TODO Auto-generated method stub
		
		ResultSet resultados=null;
		List<ValoracionMedia> listaValoracion= new ArrayList<>();
		
		try {
			String query="SELECT idRuta, AVG(valoracion) AS media_valoracion FROM valoraciones GROUP BY idRuta";
			PreparedStatement sentencia = (PreparedStatement) ConexionUtil.openConnection().prepareStatement(query);
			
			resultados = sentencia.executeQuery();
			
			while(resultados.next()) {
				listaValoracion.add(new ValoracionMedia(Integer.parseInt(resultados.getString(1)), Float.valueOf(resultados.getString(2))));
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
					/*
					 * Logger.getLogger(ProductosController.class.getName()).log( Level.SEVERE,
					 * "No se pudo cerrar el Resulset", ex);
					 */
				}
			}
		}
		return listaValoracion;
	}

}
