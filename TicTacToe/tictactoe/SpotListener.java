package tictactoe;


/*
 * SpotListener
 * 
 * Listener interface supported by Spot to report click, enter, and exit events.
 * 
 */

import connectfour.Spot;

public interface SpotListener {

	void spotClicked(connectfour.Spot spot);
	void spotEntered(connectfour.Spot spot);
	void spotExited(Spot spot);
}
