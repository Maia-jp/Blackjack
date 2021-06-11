/* Blackjack
 * Alexandre Bomfim Junior - 1921241
 * Jose Lucas Teixeira Xavier - 1921254
 * Joao Pedro Maia - 1920354
 */
package blackjack;

import blackjack.controller.ControllerAPI;
import blackjack.model.ModelAPI;
import blackjack.view.GUIService;

public class Main {

	public static void main(String[] args) {
		ControllerAPI game =  ControllerAPI.iniciar();
		try {
			game.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}