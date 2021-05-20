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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
