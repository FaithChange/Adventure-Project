package controllers;

import entities.Position;
import utils.Direction;

public interface MovementController {
	
	public boolean checkIfCanMove(Position position, Direction direction);
	public void handleMovement();
	public Position move(Position position, Direction direction);
	
	
}
