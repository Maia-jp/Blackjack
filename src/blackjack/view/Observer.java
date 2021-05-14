package blackjack.view;

import java.util.List;

import blackjack.model.ModelAPI;

public class Observer {
	ModelAPI api;
	public Observer(ModelAPI api) {
		this.api = api; 
	}
	
	public List<String> getDealermao(){
		return api.dealerMao();
	}
	
	public int getValorMaoDealer() {
		return api.valorDealerMao();
	}
}
