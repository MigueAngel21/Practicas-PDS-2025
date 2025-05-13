package umu.pds.dominio;

import java.util.List;

import com.google.common.annotations.VisibleForTesting;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;


public class RepositorioUsuarios {

	// Singleton
	private static RepositorioUsuarios instance = null;
		
    private final EntityManagerFactory emf;
    private final EntityManager entityManager;

	private RepositorioUsuarios() {
		this.emf = Persistence.createEntityManagerFactory("persistencia");
		this.entityManager = emf.createEntityManager();
	}
	
	
	public static synchronized RepositorioUsuarios getUnicaInstancia() {
		if (instance == null) {
			instance = new RepositorioUsuarios();
		}
		return instance;
	}
	
	@VisibleForTesting // Soles una anotación (no hace nada)
	@SuppressFBWarnings("SING_SINGLETON_HAS_NONPRIVATE_CONSTRUCTOR")
	// Necesitamos que sea publico ya que lo llamamos desde los test del controlador que estan en otro paquete
	// Si movemos el controlador de paquete entonces perderiamos los constructores protected del controlador
	public RepositorioUsuarios(EntityManagerFactory emf, EntityManager entityManager) {
		this.emf = emf;
		this.entityManager = entityManager;
	}
	
	
	public void add(Usuario usuario) {
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
	}
	
	
	public void remove(Usuario usuario) {
		entityManager.getTransaction().begin();
		Usuario managed = entityManager.contains(usuario) ? usuario : entityManager.merge(usuario);
		entityManager.remove(managed);
		entityManager.getTransaction().commit();
	}
	
	
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
	
	public List<Usuario> getAllUsuarios() {
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT u FROM Usuario u");
		List<Usuario> usuarios = query.getResultList();
		entityManager.getTransaction().commit();
		return usuarios;
	}
	
	
}
	

	