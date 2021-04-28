package blackjack.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TesteModelAPI {

	public ModelAPI testClass;
	
	@Before
	public void setUp() throws Exception {
		testClass = ModelAPI.iniciar();
	
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
