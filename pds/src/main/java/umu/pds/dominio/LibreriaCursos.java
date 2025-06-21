package umu.pds.dominio;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import umu.pds.interfaz.UIutils;

public class LibreriaCursos {

	private static LibreriaCursos instance = null;
	Map<String, EspecificacionCurso> cursosMap = new java.util.HashMap<String, EspecificacionCurso>();

	
	private LibreriaCursos() {

	}
	
	public static synchronized LibreriaCursos getInstance() {
		if (instance == null) {
			instance = new LibreriaCursos();
		}
		return instance;
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
		Path file = null;
		try {
			for (Path f : java.nio.file.Files.newDirectoryStream(java.nio.file.Paths.get(path))) {
				file = f;
				EspecificacionCurso curso = objectMapper.readValue(f.toFile(), EspecificacionCurso.class);
				cursosMap.put(curso.getNombre(), curso);
			}
		} catch (Exception e) {
			System.err.println("Error al cargar el curso, revisa el formateo");
			UIutils.showErrorDialog("El curso:  " + file + "   no se ha podido cargar");
		}
	}

	public List<EspecificacionCurso> getCursos() {
		List<EspecificacionCurso> cursos = new ArrayList<EspecificacionCurso>(cursosMap.values());
		Collections.sort(cursos, (a, b) -> a.getNombre().compareTo(b.getNombre()));
		return Collections.unmodifiableList(cursos);
	}


}
