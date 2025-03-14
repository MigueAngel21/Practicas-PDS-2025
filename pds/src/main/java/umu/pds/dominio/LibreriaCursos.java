package umu.pds.dominio;

import java.util.ArrayList;
import java.util.List;

public enum LibreriaCursos {
	
	INSTANCE;

	List<EspecificacionCurso> cursos = new ArrayList<EspecificacionCurso>();
	
	
	public void addCurso(EspecificacionCurso curso) {
        cursos.add(curso);
    }
	
	public EspecificacionCurso getCurso(String nombre) {
		for (EspecificacionCurso curso : cursos) {
			if (curso.getNombre().equals(nombre)) {
				return curso;
			}
		}
		return null;
	}
		
}
