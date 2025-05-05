package umu.pds.dominio;

import java.util.List;

public class Usuario {
	
	private String nombre;
	private String email;
	private String password;
	private int edad;
	private Estadistica estadisticas;
	private List<Curso> cursos;
	
	public Usuario(String nombre, String email, String password, int edad) {
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.edad = edad;
		this.estadisticas = new Estadistica();
		this.cursos = new java.util.ArrayList<Curso>();
	}

	public void updateEstadisticas(Estadistica estadisticas) {
		this.estadisticas = estadisticas;
	}
	
	public boolean comprobarContrasena(String contrasena) {
		// TODO Auto-generated method stub
		return false;
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
