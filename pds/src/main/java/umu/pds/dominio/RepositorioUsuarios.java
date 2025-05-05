package umu.pds.dominio;

import jakarta.persistence.*;
import umu.pds.controlador.Controlador;


public class RepositorioUsuarios {

	// Singleton
	private static RepositorioUsuarios instance = null;
		
    private final EntityManagerFactory emf;
    private final EntityManager entityManager;

	private RepositorioUsuarios() {
		this.emf = Persistence.createEntityManagerFactory("persistencia");
		this.entityManager = emf.createEntityManager();
	}
	
	
	public static RepositorioUsuarios getUnicaInstancia() {
		if (instance == null) {
			instance = new RepositorioUsuarios();
		}
		return instance;
	}
	
	
	public void add(Usuario usuario) {
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
	}
	
	/*
	public void remove(Usuario usuario) {
		entityManager.getTransaction().begin();
		Usuario managed = entityManager.contains(usuario) ? usuario : entityManager.merge(usuario);
		entityManager.remove(managed);
		entityManager.getTransaction().commit();
	}
	*/
	
	public void update(Usuario usuario) {
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
	}
	
	public void close() {
		entityManager.close();
		emf.close();
	}
	
	
	public Usuario getUsuario(String usuario) {
		entityManager.getTransaction().begin();
        Usuario usuarioEncontrado = entityManager.find(Usuario.class, usuario);
        entityManager.getTransaction().commit();
        return usuarioEncontrado;
    }
	
	
}
	

	