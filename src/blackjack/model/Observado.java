package blackjack.model;
import java.util.List;

import blackjack.view.*;

public interface Observado {
	public static final List<Observador> observadores = null;
	
	public void adicionarObservador(Observador o);
	
	public void notificar(Object obj,int id);
	
}
