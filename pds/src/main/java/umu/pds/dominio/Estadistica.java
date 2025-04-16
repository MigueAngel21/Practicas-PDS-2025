package umu.pds.dominio;

import java.util.Collections;
import java.util.List;

public class Estadistica {

	private int tiempoDeUso;
	private int rachaDeDias;
	private List<Progreso> progresos;
	
	public Estadistica() {
		this.tiempoDeUso = 0;
		this.rachaDeDias = 0;
		this.progresos = new java.util.ArrayList<Progreso>();
	}
	
	public Estadistica(int tiempoDeUso, int rachaDeDias) {
		this.tiempoDeUso = tiempoDeUso;
		this.rachaDeDias = rachaDeDias;
		this.progresos = new java.util.ArrayList<Progreso>();
	}
	
	public int getTiempoDeUso() {
		return tiempoDeUso;
	}
	public int getRachaDeDias() {
		return rachaDeDias;
	}
	public List<Progreso> getProgresos() {
		return Collections.unmodifiableList(progresos);
	}
	
	public void addProgreso(Progreso progreso) {
		this.progresos.add(progreso);
	}
	
}
