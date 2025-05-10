package umu.pds.dominio;

import java.util.List;

import edu.umd.cs.findbugs.annotations.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;


@Entity
public class Usuario {
	
	private String nombre;
	@Id
	private String email;
	@Nullable
	private String password;
	@Nullable
	private String provider_id;
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
	
	public Usuario(String nombre, String email, String provider_id) {
		this(nombre, email, null, 18);
		this.provider_id = provider_id;
	}

	public Usuario() {
		
	}
	
	public void updateEstadisticas(Estadistica estadisticas) {
		this.estadisticas = estadisticas;
	}
	
	public void updateEstadisticas(Progreso progreso) {
		this.estadisticas.addProgreso(progreso);
	}
	
	// Si el provider_id es null, entonces es un usuario normal y miramos la password sino miramos el provider_id
	public boolean comprobarLogin(String token) {
		if (password != null) {
			return this.password.equals(token);
		}else {
			return this.provider_id.equals(token);
		}
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
