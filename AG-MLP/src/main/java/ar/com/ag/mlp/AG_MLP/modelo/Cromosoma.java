package ar.com.ag.mlp.AG_MLP.modelo;

import java.util.ArrayList;

public class Cromosoma {
	
	private ArrayList<Gene> genes;
	// apSesgo= wb
	private float apSesgo;
	private int nMax;
	private float aptitud;
	private float relAptitud;
	private float acumAptitud;
	
	public Cromosoma() {
		super();		
	}

	public ArrayList<Gene> getGenes() {
		return genes;
	}

	public void setGenes(ArrayList<Gene> genes) {
		this.genes = genes;
	}

	public float getApSesgo() {
		return apSesgo;
	}

	public void setApSesgo(float apSesgo) {
		this.apSesgo = apSesgo;
	}

	public int getnMax() {
		return nMax;
	}

	public void setnMax(int nMax) {
		this.nMax = nMax;
	}

	public float getAptitud() {
		return aptitud;
	}

	public void setAptitud(float aptitud) {
		this.aptitud = aptitud;
	}

	public float getRelAptitud() {
		return relAptitud;
	}

	public void setRelAptitud(float relAptitud) {
		this.relAptitud = relAptitud;
	}

	public float getAcumAptitud() {
		return acumAptitud;
	}

	public void setAcumAptitud(float acumAptitud) {
		this.acumAptitud = acumAptitud;
	}

	@Override
	public String toString() {
		return "Cromosoma [genes=" + genes + ", apSesgo=" + apSesgo + ", nMax=" + nMax + ", aptitud=" + aptitud
				+ ", relAptitud=" + relAptitud + ", acumAptitud=" + acumAptitud + "]";
	}
	
}
