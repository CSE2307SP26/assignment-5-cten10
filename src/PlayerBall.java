import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class PlayerBall {
    
    public PlayerBall(double startingXPos, double startingYPos) {
        this.position = new Vector2D(startingXPos, startingYPos);
        this.minPosition = new Vector2D(0, 0);
        this.maxPosition = new Vector2D(0, 0);
    }

    private Vector2D position;
    private double playerSpeed;
    private Vector2D minPosition;
    private Vector2D maxPosition;

    public Vector2D getPosition() {
        return this.position;
    }

    public void setPosition(Vector2D newPosition) {
        this.position = newPosition;
    }

    public void setPosition(double newXPosition, double newYPosition) {
        this.position.set(newXPosition, newYPosition);
    }

    public void setXPosition(double newXPosition) {
        this.position.xPos = newXPosition;
    }

    public void setYPosition(double newYPosition) {
        this.position.yPos = newYPosition;
    }

    public void setPositionBounds(Vector2D minPosition, Vector2D maxPosition) {
        this.minPosition.set(minPosition);
        this.maxPosition.set(maxPosition);
    }

    public void enforcePositionBounds() {
        position.clamp(minPosition, maxPosition);
    }

    public void setSpeed(double playerSpeed) {
        this.playerSpeed = playerSpeed;
    }

    public void processKBInput() {	
			if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
				position.add(0, playerSpeed);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
				position.subtract(0, playerSpeed);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
				position.subtract(playerSpeed, 0);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
				position.add(playerSpeed, 0);
			}
    }
}
