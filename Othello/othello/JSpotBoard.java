package othello;


import org.w3c.dom.css.RGBColor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

/*
 * JSpotBoard is a user interface component that implements SpotBoard.
 * 
 * Spot width and spot height are specified to the constructor. 
 * 
 * By default, the spots on the spot board are set up with a checker board pattern
 * for background colors and yellow highlighting.
 * 
 * Uses SpotBoardIterator to implement Iterable<Spot>
 * 
 */

public class JSpotBoard extends JPanel implements SpotBoard {

	private static final int DEFAULT_SCREEN_WIDTH = 500;
	private static final int DEFAULT_SCREEN_HEIGHT = 500;
	private static final Color DEFAULT_BACKGROUND_LIGHT = new Color(0.8f, 0.8f, 0.8f);
	private static final Color DEFAULT_BACKGROUND_DARK = new Color(0.5f, 0.5f, 0.5f);
	private static final Color DEFAULT_OTHELLO_DARK = new Color(0f, 0.4f, 0f);
	private static final Color DEFAULT_OTHELLO_LIGHT = new Color(0f, 0.6f, 0f);
	private static final Color DEFAULT_SPOT_COLOR = Color.BLACK;
	private static final Color DEFAULT_HIGHLIGHT_COLOR = Color.YELLOW;

	private Spot[][] _spots;

	public JSpotBoard(int width, int height) {
		if (width < 1 || height < 1 || width > 50 || height > 50) {
			throw new IllegalArgumentException("Illegal spot board geometry");
		}
		setLayout(new GridLayout(height, width));
		_spots = new Spot[width][height];

		Dimension preferred_size = new Dimension(DEFAULT_SCREEN_WIDTH/width, DEFAULT_SCREEN_HEIGHT/height);

		// Board specific to game
		if (width == 3) {
			for (int y=0; y<height; y++) {
				for (int x=0; x<width; x++) {
					Color bg = DEFAULT_BACKGROUND_LIGHT;
					_spots[x][y] = new JSpot(bg, DEFAULT_SPOT_COLOR, DEFAULT_HIGHLIGHT_COLOR, this, x, y);
					((JSpot)_spots[x][y]).setPreferredSize(preferred_size);
					add(((JSpot) _spots[x][y]));
				}
			}
		} else if (width == 7) {
			for (int y=0; y<height; y++) {
				for (int x=0; x<width; x++) {
					Color bg = ((x) % 2 == 0) ? DEFAULT_BACKGROUND_LIGHT : DEFAULT_BACKGROUND_DARK;
					_spots[x][y] = new JSpot(bg, DEFAULT_SPOT_COLOR, DEFAULT_HIGHLIGHT_COLOR, this, x, y);
					((JSpot)_spots[x][y]).setPreferredSize(preferred_size);
					add(((JSpot) _spots[x][y]));
				}
			}
		} else if (width == 8){
			for (int y=0; y<height; y++) {
				for (int x=0; x<width; x++) {
					Color bg = ((x+y)%2 == 0) ? DEFAULT_OTHELLO_LIGHT : DEFAULT_OTHELLO_DARK;
					_spots[x][y] = new JSpot(bg, DEFAULT_SPOT_COLOR, DEFAULT_HIGHLIGHT_COLOR, this, x, y);
					((JSpot)_spots[x][y]).setPreferredSize(preferred_size);
					add(((JSpot) _spots[x][y]));
				}
			}
		} else {
			for (int y=0; y<height; y++) {
				for (int x=0; x<width; x++) {
					Color bg = ((x+y)%2 == 0) ? DEFAULT_BACKGROUND_LIGHT : DEFAULT_BACKGROUND_DARK;
					_spots[x][y] = new JSpot(bg, DEFAULT_SPOT_COLOR, DEFAULT_HIGHLIGHT_COLOR, this, x, y);
					((JSpot)_spots[x][y]).setPreferredSize(preferred_size);
					add(((JSpot) _spots[x][y]));
				}
			}
		}
	}

	// Getters for SpotWidth and SpotHeight properties
	
	@Override
	public int getSpotWidth() {
		return _spots.length;
	}
	
	@Override
	public int getSpotHeight() {
		return _spots[0].length;
	}

	// Lookup method for Spot at position (x,y)
	
	@Override
	public Spot getSpotAt(int x, int y) {
		if (x < 0 || x >= getSpotWidth() || y < 0 || y >= getSpotHeight()) {
			throw new IllegalArgumentException("Illegal spot coordinates");
		}
		
		return _spots[x][y];
	}
	
	// Convenience methods for (de)registering spot listeners.
	
	@Override
	public void addSpotListener(SpotListener spot_listener) {
		for (int x=0; x<getSpotWidth(); x++) {
			for (int y=0; y<getSpotHeight(); y++) {
				_spots[x][y].addSpotListener(spot_listener);
			}
		}
	}
	
	@Override
	public void removeSpotListener(SpotListener spot_listener) {
//		for (int x=0; x<getSpotWidth(); x++) {
//			for (int y=0; y<getSpotHeight(); y++) {
//				_spots[x][y].removeSpotListener(spot_listener);
//			}
//		}
		for (Spot s : this) {
			s.addSpotListener(spot_listener);
		}
	}

	@Override
	public Iterator<Spot> iterator() {
		return new SpotBoardIterator(this);
	}
}
