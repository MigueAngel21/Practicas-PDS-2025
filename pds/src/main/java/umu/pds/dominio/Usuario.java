package umu.pds.dominio;

import java.util.List;

public class Usuario {
	
	private String nombre;
	private String email;
	private String password;
	private int edad;
	private Estadistica estadisticas;
	private List<Curso> cursos;

	public boolean comprobarContrasena(String contrasena) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
