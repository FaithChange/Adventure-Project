package utils;

import entities.Map;
import entities.Position;

public class PositionValidator {
	
	public static boolean evaluatePosition(Map map, Position position) {
		try {
			boolean isValid = false;
			char cell = map.getGrid()[position.getX()][position.getY()];
			switch(cell) {
			
			case ' ': {
				isValid = true;break;
			}
			case '#': {
				isValid = false;break;
			}
			default: 
				   break;
			
			}
			
			return isValid;
			
		}catch(IndexOutOfBoundsException ex) {
			throw ex;
		}
		
		
	}

}
