/* Blackjack
 * Alexandre Bomfim Junior - 1921241
 * Jose Lucas Teixeira Xavier - 1921254
 * Joao Pedro Maia - 1920354
 */
package blackjack.model;
import java.util.List;

import blackjack.view.*;

public interface Observado {
	public static final List<Observador> observadores = null;
	
	public void adicionarObservador(Observador o);
	
	public void notificar(Object obj,int id);
	
}
