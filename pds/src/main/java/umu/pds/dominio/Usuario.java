package umu.pds.dominio;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class Usuario {
	
	private String nombre;
	@Id
	private String email;
	private String password;
	private int edad;
	@OneToOne(cascade = CascadeType.ALL)//ESTO MIRARLO
	private Estadistica estadisticas;
	@Transient
	private List<Curso> cursos;
	
	public Usuario(String nombre, String email, String password, int edad) {
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.edad = edad;
		this.estadisticas = new Estadistica();
		this.cursos = new java.util.ArrayList<Curso>();
	}

	public Usuario() {
		
	}
	
	public void updateEstadisticas(Estadistica estadisticas) {
		this.estadisticas = estadisticas;
	}
	
	public void updateEstadisticas(Progreso progreso) {
		this.estadisticas.addProgreso(progreso);
	}
	
	public boolean comprobarContrasena(String contrasena) {
		return this.password.equals(contrasena);
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public int getEdad() {
		return edad;
	}

	public Estadistica getEstadisticas() {
		return estadisticas;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	


}
