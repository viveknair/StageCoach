package unit;

import configuration.*;

/*
 * ====> Unit
 * 			Define an extensible Unit that forms to user-defined scene element points.	
 * 			This will have to involve implemented a custom configuration object that
 * 			defines the behavior of a custom traversal. 
*/
public class Unit {

	private static UnitConfig uconf;

	public Unit(UnitConfig uconf) {
		this.uconf = uconf;
	}


	public Unit() {
		// Defaults to play execution (e.g. action or dialogue point)
		this( new UnitConfig(UnitType.DEFAULT));
	}
}
