package controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import entities.Position;
import utils.Direction;


@ExtendWith(MockitoExtension.class)
class GameManagerTest {

	@Mock Position initialPosition;
	GameManager gm = GameManager.getInstance();
	
	
	
	@Test
	void testReadInputs() {
		try {
			
			List<String> inputLines = Files.readAllLines(Paths.get("src/main/resources/testFile.txt"));

			String positionInput = inputLines.get(0);
			String directionsInput = inputLines.get(1);

			String[] splittedPositionInput = positionInput.split(",");

			assertEquals(3, Integer.valueOf(splittedPositionInput[0]));
			assertEquals(0, Integer.valueOf(splittedPositionInput[1]));
			assertEquals("SSSSEEEEEENN",directionsInput);
			
		} catch (IOException ex) {

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	@Test
	void testMove() {
		Direction direction = Direction.E;
		Position position = Mockito.mock(Position.class);
		when(position.getX()).thenReturn(3);
		when(position.getY()).thenReturn(0);
		
		Position newPosition = Mockito.mock(Position.class);
		when(newPosition.getX()).thenReturn(4);
		when(newPosition.getY()).thenReturn(0);
		
		position = gm.move(position, direction);
		
		assertEquals(newPosition.getX(),position.getX());
		assertEquals(newPosition.getY(),position.getY());
	}

	@Test
	void testHandleMovement() {
		when(initialPosition.getX()).thenReturn(3);
		when(initialPosition.getY()).thenReturn(0);
	
		Position finalPosition = Mockito.mock(Position.class);

		List<Direction> directions = Arrays
									.asList(Direction.S,Direction.S,Direction.S,Direction.S,
									 Direction.E,Direction.E,Direction.E,Direction.E,Direction.E,Direction.E,
									 Direction.N,Direction.N);
		
		gm.setDirections(directions);
		gm.setInitialPosition(initialPosition);
		
		when(finalPosition.getX()).thenReturn(9);
		when(finalPosition.getY()).thenReturn(2);
		
		gm.handleMovement();
		
		assertNotNull(gm.getPosition());
		assertEquals(finalPosition.getX(), gm.getPosition().getX());
		assertEquals(finalPosition.getY(), gm.getPosition().getY());
	}

	@Test
	void testCheckIfCanMove() {
		
		Direction direction = Direction.E;
		Position position = Mockito.mock(Position.class);
		when(position.getX()).thenReturn(9);
		when(position.getY()).thenReturn(2);
		
		assertTrue(gm.checkIfCanMove(position, direction));
	}
	
	@Test
	void testCheckIfCanMoveBorders() {
		
		Direction direction = Direction.E;
		Position position = Mockito.mock(Position.class);
		when(position.getX()).thenReturn(19);
		when(position.getY()).thenReturn(9);
		
		assertThrows(IndexOutOfBoundsException.class, () -> gm.checkIfCanMove(position, direction));
	}

}
