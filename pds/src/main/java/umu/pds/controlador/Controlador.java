package umu.pds.controlador;

import umu.pds.dominio.RepositorioUsuarios;
import umu.pds.dominio.Usuario;

public enum Controlador {
	
	// Singleton con ENUM
	INSTANCE;
	
	private Usuario usuarioActual;
	private RepositorioUsuarios repositorioUsuarios;
	
	
	public boolean InciarSesion(String usuario, String contrasena) {
		usuarioActual = repositorioUsuarios.getUsuario(usuario);
		
		if (usuarioActual != null && usuarioActual.comprobarContrasena(contrasena)) {
			return true;
		}
		
		return false;
	}
	
}
