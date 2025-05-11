package umu.pds.dominio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonExport {

	public static void export(EspecificacionCurso espec) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = "";
		try {
			json = objectMapper.writeValueAsString(espec);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		// save JSON to file
		try {
			java.nio.file.Files.write(java.nio.file.Paths.get("especificacionCurso.json"), json.getBytes());
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		
		// load json to class
		try {
			EspecificacionCurso espec2 = objectMapper.readValue(json, EspecificacionCurso.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
