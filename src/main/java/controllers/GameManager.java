package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import entities.Map;
import entities.Position;
import utils.Direction;
import utils.PositionValidator;

public class GameManager implements MovementController {
	
	private Position initialPosition;
	private Position position;
	private List<Direction> directions;
	private Map map = Map.getInstance();
	
	private static GameManager gameManager;
	
	
	private GameManager() {}
	
	public void readInputs() {
		try {
			List<String> inputLines = Files.readAllLines(Paths.get("src/main/resources/testFile.txt"));
			
			if(inputLines.isEmpty()) throw new Exception("Empty inputs file");
			
			//validation required todo
			
			String positionInput = inputLines.get(0);
			String directionsInput = inputLines.get(1);
	
			String[] splittedPositionInput = positionInput.split(",");
			
			List<Direction> splittedDirectionsInput = directionsInput
												 		.chars()
												 		.mapToObj(d -> Direction.valueOf(String.valueOf((char)d)))
												 		.collect(Collectors.toList());
			this.initialPosition = new Position(Integer.valueOf(splittedPositionInput[0]), Integer.valueOf(splittedPositionInput[1]));
			this.directions = splittedDirectionsInput;
			splittedDirectionsInput = null;
			
			
		}catch(IOException ex) {
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	public static GameManager getInstance() {
		if(gameManager!= null) return gameManager;
		
		gameManager = new GameManager();
		return gameManager;
	}

	@Override
	public Position move(Position position, Direction direction) {
	
		Position newPosition = null;
		try {
			switch(direction) {
			case S : {
				newPosition = new Position(position.getX(), position.getY() + 1);
				break;
			}
			case E : {
				newPosition = new Position(position.getX() + 1, position.getY());
				break;
			}
			case N : {
				newPosition = new Position(position.getX(), position.getY() - 1);
				break;
			}
			case O : {
				newPosition = new Position(position.getX() - 1, position.getY());
				break;
			}
			default:
				break;
	
			}
			return newPosition;
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}

		
	}

	@Override
	public void handleMovement() {
		Position currentPosition= null;
		try {
			boolean isInitialPositionValid = PositionValidator.evaluatePosition(map, initialPosition);			
			if(isInitialPositionValid) {
				currentPosition = initialPosition;
				position = currentPosition;
			}else {
				System.out.println("Can not initialize position in dangerous woods");
				position = null;
				return;
			}
		}catch(IndexOutOfBoundsException ex) {
			System.out.println("Can not initialize position out of the map");
			return;
		}
		
		try {
			
			for(Direction direction : directions) {
				boolean ifCanMove = checkIfCanMove(currentPosition, direction);
				if(ifCanMove) {
					currentPosition = move(currentPosition, direction);
					position = currentPosition;
				}else {
					System.out.println("You are in : " + currentPosition + " you can't move in this dangerous path");
					position =null;
					return;
				}
			}
			System.out.println("destination achieved successfully : " + currentPosition);
		}catch(IndexOutOfBoundsException ex) {
			System.out.println("You are in : " + currentPosition + " you can't move out of the map");
		}
		
	
	}

	@Override
	public boolean checkIfCanMove(Position position, Direction direction) {
		
		if(position == null || direction == null) return false;
		Position tempPosition = null;
		
		boolean isValid = false;
		
		try {
			switch(direction) {
			case S : {
				tempPosition = new Position(position.getX(), position.getY() + 1);
				isValid = PositionValidator.evaluatePosition(map, tempPosition);
				break;
			}
			case E : {
				tempPosition = new Position(position.getX() + 1, position.getY());
				isValid = PositionValidator.evaluatePosition(map, tempPosition);
				break;
			}
			case N : {
				tempPosition = new Position(position.getX(), position.getY() - 1);
				isValid = PositionValidator.evaluatePosition(map, tempPosition);
				break;
			}
			case O : {
				tempPosition = new Position(position.getX() - 1, position.getY());
				isValid = PositionValidator.evaluatePosition(map, tempPosition);
				break;
			}
			default:
				break;
	
			}
			return isValid;
		}catch(IndexOutOfBoundsException ex) {
			throw ex;
		}
		
	}
	public void setDirections(List<Direction> directions) {
		this.directions = directions;
	}
	public void setInitialPosition(Position initialPosition) {
		this.initialPosition = initialPosition;
	}
	public Position getPosition() {
		return position;
	}





}
