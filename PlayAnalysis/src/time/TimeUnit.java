package time;

import configuration.*;

/*
 * ====> Unit
 * 			Define an extensible Unit that forms to user-defined scene element points.	
 * 			This will have to involve implemented a custom configuration object that
 * 			defines the behavior of a custom traversal. 
*/
public class TimeUnit {

	private static UnitConfig uconf;

	public TimeUnit(UnitConfig uconf) {
		this.uconf = uconf;
	}

	public TimeUnit() {
		// Defaults to play execution (e.g. action or dialogue point)
		this( new UnitConfig(UnitType.DEFAULT));
	}
	
	public int setState(int cstate) {
		return cstate;
	}
}
