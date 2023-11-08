package es.unex.cum.tw.ef2.model;

import java.util.Objects;

public class ValoracionMedia {

	private int idRuta;
	private float mediaV;
	
	public ValoracionMedia() {
		idRuta=0;
		mediaV=0f;
	}

	public ValoracionMedia(int idRuta, float mediaV) {
		super();
		this.idRuta = idRuta;
		this.mediaV = mediaV;
	}

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public float getMediaV() {
		return mediaV;
	}

	public void setMediaV(float mediaV) {
		this.mediaV = mediaV;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRuta, mediaV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValoracionMedia other = (ValoracionMedia) obj;
		return idRuta == other.idRuta && Float.floatToIntBits(mediaV) == Float.floatToIntBits(other.mediaV);
	}

	@Override
	public String toString() {
		return "ValoracionMedia [idRuta=" + idRuta + ", mediaV=" + mediaV + "]";
	}
	
}
