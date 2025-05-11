package umu.pds.dominio;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LibreriaCursos {

	private static LibreriaCursos instance = null;
	List<EspecificacionCurso> cursos = new ArrayList<EspecificacionCurso>();

	
	private LibreriaCursos() {

	}
	
	public static LibreriaCursos getInstance() {
		if (instance == null) {
			instance = new LibreriaCursos();
		}
		return instance;
	}
	
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

	public void cargarCursos(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		// For every JSON file in path
		try {
			for (Path file : java.nio.file.Files.newDirectoryStream(java.nio.file.Paths.get(path))) {
				EspecificacionCurso curso = objectMapper.readValue(file.toFile(), EspecificacionCurso.class);
				cursos.add(curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<EspecificacionCurso> getCursos() {
		return Collections.unmodifiableList(cursos);
	}

}
