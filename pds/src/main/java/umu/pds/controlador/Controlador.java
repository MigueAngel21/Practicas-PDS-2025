package umu.pds.controlador;

import umu.pds.dominio.RepositorioUsuarios;
import umu.pds.dominio.Usuario;

public enum Controlador {
	
	// Singleton con ENUM
	INSTANCE;
	
	private Usuario usuarioActual;
	private RepositorioUsuarios repositorioUsuarios;
	
	
	public boolean InciarSesion(String usuario, String contrasena) {
		System.out.println("Usuario: " + usuario + " Contraseña: " + contrasena);
		if (usuario.equals("paco") && contrasena.equals("1234") ) {
			return true;
		} else {
			return false;
		}
		
		/*usuarioActual = repositorioUsuarios.getUsuario(usuario);
		
		if (usuarioActual != null && usuarioActual.comprobarContrasena(contrasena)) {
			return true;
		}
		
		return false;*/
	}
	
}
