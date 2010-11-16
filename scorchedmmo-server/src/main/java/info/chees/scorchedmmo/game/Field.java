package info.chees.scorchedmmo.game;

import java.util.ArrayList;
import java.util.List;

public class Field {

	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_HEIGHT = 100;
	
	/**
	 * A list of heights which defines the landscape.
	 */
	private List<Integer> heights;
	
	public Field() {
		heights = new ArrayList<Integer>();
		for(int i=0; i<DEFAULT_WIDTH; i++) {
			heights.add((int)(Math.random() * DEFAULT_HEIGHT));
		}
	}
	
	public int getWidth() {
		return heights.size();
	}

	public List<Integer> getHeights() {
		return heights;
	}

}
