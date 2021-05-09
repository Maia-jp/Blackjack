package blackjack;

import blackjack.model.ModelAPI;
import blackjack.view.GUIService;

public class Main {

	public static void main(String[] args) {
		GUIService GUI =  GUIService.iniciar();
		try {
			GUI.exibir();
			System.out.print("123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
