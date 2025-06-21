package umu.pds.dominio;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Estadistica {
	
	// Constantes
	@Transient
	public static final int PUNTOS_CORRECTA = 10;
	@Transient
	public static final int PUNTOS_INCORRECTA = 5;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private long tiempoDeUso;
	private int rachaDeDias;
	private int puntos;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Progreso> progresos;
	@Transient
	private Instant startTime;
	
	public Estadistica() {
		this.tiempoDeUso = 0;
		this.rachaDeDias = 0;
		this.puntos = 0;
		this.progresos = new java.util.ArrayList<Progreso>();
		this.startTime = Instant.now();
	}
	
	
	public long getTiempoDeUso() {
		return (long) Math.floor(tiempoDeUso/60);
	}
	public int getRachaDeDias() {
		return rachaDeDias;
	}
	public List<Progreso> getProgresos() {
		return Collections.unmodifiableList(progresos);
	}

	public void addProgreso(Progreso progreso) {
		// Si el progreso ya existe, actualiza el existente
		for (int i = 0; i < progresos.size(); i++) {
			if(progresos.get(i).isSameCurso(progreso)) {
				progresos.set(i, progreso);
				return;
			}
		}
		this.progresos.add(progreso);
	}
	
	public void calcularTiempoDeUso() {
		this.tiempoDeUso = this.tiempoDeUso + Duration.between(startTime, Instant.now()).toSeconds();
		this.startTime = Instant.now();
	}


	public void updatePuntos(boolean correcta) {
		if(correcta) {
			puntos += PUNTOS_CORRECTA;
		} else {
			puntos += PUNTOS_INCORRECTA;
		}
	}
	
	public int getPuntos() {
		return puntos;
	}


	public int calcularPuntos(int correctas, int incorrectas) {
		return (correctas * PUNTOS_CORRECTA) + (incorrectas * PUNTOS_INCORRECTA);
	}
	
}
