package umu.pds.dominio;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LibreriaCursos {

	private static LibreriaCursos instance = null;
	Map<String, EspecificacionCurso> cursosMap = new java.util.HashMap<String, EspecificacionCurso>();

	
	private LibreriaCursos() {

	}
	
	public static LibreriaCursos getInstance() {
		if (instance == null) {
			instance = new LibreriaCursos();
		}
		return instance;
	}
	
	public void addCurso(EspecificacionCurso curso) {
		if (cursosMap.containsKey(curso.getNombre())) {
			throw new IllegalArgumentException("El curso ya existe");
		}
		cursosMap.put(curso.getNombre(), curso);
		//cursos.add(curso);
	}

	public EspecificacionCurso getEspecificacionCurso(String nombre) {
		if (cursosMap.containsKey(nombre)) {
			return cursosMap.get(nombre);
		}
		return null;
	}

	public void cargarCursos(String path) {
		ObjectMapper objectMapper = new ObjectMapper();
		// For every JSON file in path
		try {
			for (Path file : java.nio.file.Files.newDirectoryStream(java.nio.file.Paths.get(path))) {
				EspecificacionCurso curso = objectMapper.readValue(file.toFile(), EspecificacionCurso.class);
				cursosMap.put(curso.getNombre(), curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<EspecificacionCurso> getCursos() {
		List<EspecificacionCurso> cursos = new ArrayList<EspecificacionCurso>(cursosMap.values());
		Collections.sort(cursos, (a, b) -> a.getNombre().compareTo(b.getNombre()));
		return Collections.unmodifiableList(cursos);
	}


}
