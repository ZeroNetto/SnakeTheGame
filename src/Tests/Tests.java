package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import fieldObjects.AcceleratorBonus;
import fieldObjects.Apple;
import fieldObjects.FieldObject;
import fieldObjects.ResetAcceleratorBonus;
import fieldObjects.SnakeHead;
import fieldObjects.SnakePart;
import fieldObjects.Wall;
import game.Field;
import game.Game;
import utils.Point;

public class Tests {

	/*@Test
	public void testCorrectSnakeHeadMove() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		field.initilizeField();
		Game game = new Game(field);
		
		game.tick();
		
		assertEquals(field.getField()[2][1].getClass(), SnakeHead.class);
	}
	
	@Test
	public void testCorrectEatsApple() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		field.getObjects().add(new Apple(2, 1));
		field.initilizeField();
		Game game = new Game(field);
		
		game.tick();
		
		assertTrue(!field.getObjects().contains(new Apple(2, 1)));
	}
	
	@Test
	public void testIncreaseAfterEatsApple() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		field.getObjects().add(new Apple(2, 1));
		field.getObjects().add(new Apple(2, 0));
		field.initilizeField();
		Game game = new Game(field);
		
		game.tick();
		game.tick();
		int actualLength = 2;
		int expected = 3;
		SnakeHead snakeHead = game.findSnakeHead();
		SnakePart currentPart = snakeHead.getPreviousPart();
		while(currentPart.getPreviousPart() != null) {
			currentPart = currentPart.getPreviousPart();
			actualLength++;
		}
		
		assertEquals(expected, actualLength);
	}
	
	@Test
	public void testCorrectMoveSnakeWithBody() {
		Field field = new Field(3, 3);
		SnakeHead snakeHead = new SnakeHead(2, 1, new Point(0, -1));
		SnakePart snakePart = new SnakePart(2, 2);
		snakeHead.setPreviousPart(snakePart);
		field.getObjects().add(snakeHead);
		field.getObjects().add(snakePart);
		field.initilizeField();
		Game game = new Game(field);
		game.tick();
		
		assertTrue(field.getField()[2][0].getClass() == SnakeHead.class);
		assertTrue(field.getField()[2][1].getClass() == SnakePart.class);
	}

	@Test
	public void testCorrectDeadInWall() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		field.getObjects().add(new Wall(2, 1));
		field.initilizeField();
		Game game = new Game(field);
		
		game.tick();
		
		assertTrue(game.gameOver);
	}
	
	@Test
	public void testCorrectDeadInSnakePart() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		SnakePart firstPart = new SnakePart(2, 1);
		SnakePart secondPart = new SnakePart(2, 0);
		firstPart.setPreviousPart(secondPart);
		field.getObjects().add(firstPart);
		field.getObjects().add(secondPart);
		field.initilizeField();
		Game game = new Game(field);
		
		game.tick();
		
		assertTrue(game.gameOver);
	}
	
	@Test
	public void testCorrectDeadInSnakeHead() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		field.getObjects().add(new SnakeHead(2, 1, new Point(0, 0)));
		field.initilizeField();
		Game game = new Game(field);
		
		game.tick();
		
		assertTrue(game.gameOver);
	}
	
	@Test
	public void testCorrectMoveToTail() {
		Field field = new Field(3, 3);
		SnakeHead snakeHead = new SnakeHead(2, 2, new Point(0, -1));
		SnakePart firstPart = new SnakePart(1, 2);
		SnakePart secondPart = new SnakePart(1, 1);
		SnakePart thirdPart = new SnakePart(2, 1);
		snakeHead.setPreviousPart(firstPart);
		firstPart.setPreviousPart(secondPart);
		secondPart.setPreviousPart(thirdPart);
		field.getObjects().add(snakeHead);
		field.getObjects().add(firstPart);
		field.getObjects().add(secondPart);
		field.getObjects().add(thirdPart);
		field.initilizeField();
		Game game = new Game(field);
		
		game.tick();
		
		assertTrue(!game.gameOver);
	}
	
	@Test
	public void testCorrectIncreaseSpeedAfterBonus() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		FieldObject acceleratorBonus = (FieldObject)new AcceleratorBonus(2, 1);
		field.getObjects().add(acceleratorBonus);
		Game game = new Game(field);
		int basicSpeed = 500;
		field.initilizeField();
		game.tick();
		
		assertEquals(basicSpeed - 50, game.getSpeed());
		assertTrue(!field.getObjects().contains(acceleratorBonus));
	}
	
	@Test
	public void testCorrectSetDefaultSpeedAfterBonus() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		FieldObject resetBonus = (FieldObject) new ResetAcceleratorBonus(2, 1);
		field.getObjects().add(resetBonus);
		Game game = new Game(field);
		game.setSpeed(450);
		int basicSpeed = 500;
		field.initilizeField();
		game.tick();
		
		assertEquals(basicSpeed, game.getSpeed());
		assertTrue(!field.getObjects().contains(resetBonus));
	}
	
	@Test
	public void testBonusesWillClearAfterLifeTime() {
		Field field = new Field(15, 15);
		field.getObjects().add(new SnakeHead(1, 14, new Point(0, -1)));
		FieldObject resetBonus = (FieldObject)new ResetAcceleratorBonus(2, 1);
		FieldObject acceleratorBonus = (FieldObject)new AcceleratorBonus(2, 2);
		field.getObjects().add(resetBonus);
		field.getObjects().add(acceleratorBonus);
		Game game = new Game(field);
		field.initilizeField();
		for (int i = 0; i < 11; i++) {
			game.tick();
		}
		
		assertTrue(!field.getObjects().contains(resetBonus));
		assertTrue(!field.getObjects().contains(acceleratorBonus));
	}*/
}
