package nl.chees.scorchedmmo.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {

	private Field field;
	
	@Before
	public void setUp() {
		field = new Field();
	}
	
	@Test
	public void testInitialized() throws Exception {
		assertEquals(Field.DEFAULT_WIDTH, field.getWidth());
		for(int i : field.getHeights()) {
			assertTrue(i >= 0);
			assertTrue(i <= Field.DEFAULT_HEIGHT);
		}
	}
}
