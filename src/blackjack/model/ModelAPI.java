package blackjack.model;

public class ModelAPI {
	//Implementa modelo singleton
	private static ModelAPI instanciaUnica;
	
	private ModelAPI() {
		
	}
	
	public static synchronized ModelAPI iniciar() {
		if(instanciaUnica == null)
			instanciaUnica = new ModelAPI();
		return instanciaUnica;
	}
	
	
}
