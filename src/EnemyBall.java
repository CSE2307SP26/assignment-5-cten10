public class EnemyBall {
    
    private Vector2D position;
    private Vector2D velocity;
    private static Vector2D velocityBounds; // x = lower bound, y = upper bound
    private static double radius;

    public EnemyBall() {
        position = new Vector2D(0, 0);
        velocity = new Vector2D(0,0);
    }

    public EnemyBall(double startXPos, double startYPos) {
        position = new Vector2D(startXPos, startYPos);
        velocity = new Vector2D(0,0);
    }

    public Vector2D getPosition() {
        return this.position;
    }
    
    public void setPosition(Vector2D newPosition) {
        this.position.set(newPosition);
    }

    public Vector2D getVelocity() {
        return this.velocity;
    }

    public void setVelocity(double newXVel, double newYVel) {
        this.velocity.set(newXVel, newYVel);
    }

    public void setVelocity(Vector2D newVelocity) {
        this.velocity.set(newVelocity);
    }

    public static void setVelocityBounds(double lowerBound, double upperBound) {
        velocityBounds = new Vector2D(lowerBound, upperBound);
    }

    public static void setRadius(double ballRadius) {
        radius = ballRadius;
    }

    public void randomizePosition() {
        this.position.set(Math.random(), Math.random());
    }

    public void randomizeVelocity() {
        this.velocity.xPos = Math.random() * (velocityBounds.yPos - velocityBounds.xPos) * velocityBounds.xPos;
        this.velocity.yPos = Math.random() * (velocityBounds.yPos - velocityBounds.xPos) * velocityBounds.xPos;
    }

    public void tick() {
        this.position.add(this.velocity);

        if(position.xPos + radius > 1 || position.xPos - radius < 0) { 
					velocity.xPos = -velocity.xPos;
		}

        if(position.yPos + radius > 1 || position.yPos - radius < 0) { 
            velocity.yPos = -velocity.yPos;
        }
    }
}
