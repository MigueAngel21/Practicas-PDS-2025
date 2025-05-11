package umu.pds.dominio;

public class FactoriaEstrategias {

	private static FactoriaEstrategias instance = new FactoriaEstrategias();
	
	private FactoriaEstrategias() {
		// Constructor privado para evitar instanciación
	}
	
	
	public static FactoriaEstrategias getUnicaInstancia() {
		return instance;
	}

	public EstrategiaAprendizaje crearEstrategia(String tipo) {
		try {
			return (EstrategiaAprendizaje) Class.forName(tipo).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			return null;
		}
	}
	
}
