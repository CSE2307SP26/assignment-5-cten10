import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;
public class Game {
	private static double velocityLowerBound = 0.005;
	private static double velocityUpperBound = 0.01;
	private static int numberOfBalls = 3;
	private static final double ballRadius = 0.025;
	private static int score = 0;
	private static int highScore = 0;
	private static final double playerSpeed = 0.01;
	
	public static void main(String[] args) {
		PlayerBall player = new PlayerBall(0.5, 0.5);
		player.setPositionBounds(new Vector2D(0, 0), new Vector2D(1, 1));
		
		EnemyBall[] balls = new EnemyBall[numberOfBalls];
		EnemyBall.setVelocityBounds(velocityLowerBound, velocityUpperBound);
		EnemyBall.setRadius(ballRadius);

		for(int i = 0; i < numberOfBalls; i++) {
			balls[i] = new EnemyBall();
			balls[i].randomizePosition();
			balls[i].randomizeVelocity();
		}
		
		StdDraw.enableDoubleBuffering();
		
		long startTime = System.currentTimeMillis();
		long deltaTime = System.currentTimeMillis();
		
		while (true) {
			
			StdDraw.clear();
			boolean c = false;
			for(int i = 0; i < numberOfBalls; i++) {
				EnemyBall ball = balls[i];
				ball.tick();
				for(int j = 0; j < numberOfBalls; j++) {
					Vector2D otherBallPos = balls[j].getPosition();
					if(i != j) {
						double d = Math.sqrt(Math.pow(ball.getPosition().xPos - otherBallPos.xPos, 2) + Math.pow(ball.getPosition().yPos - otherBallPos.yPos, 2));
						if(d < 2 * ballRadius) {
							ball.getVelocity().xPos = -ball.getVelocity().xPos;
							ball.getVelocity().yPos = -ball.getVelocity().yPos;
						}
					}
				}
				double d = Math.sqrt(Math.pow(ball.getPosition().xPos - player.getPosition().xPos, 2) + Math.pow(ball.getPosition().yPos - player.getPosition().yPos, 2));
				if(d < 2 * ballRadius) {
					c = true;
				}
			}
			
			if(c) {
				numberOfBalls = 3;
				for(int i = 0; i < numberOfBalls; i++) {
					balls[i].randomizePosition();
					balls[i].randomizeVelocity();
					score = 0;
					startTime = System.currentTimeMillis();
					deltaTime = System.currentTimeMillis();
					player.setPosition(0.5, 0.5);
				}
			}

			player.setSpeed(playerSpeed);
			player.processKBInput();
			player.enforcePositionBounds();
			
			long currentTime = System.currentTimeMillis();
			if(currentTime > startTime + 1000) {
				score++;
				if(score > highScore) {
					highScore = score;
				}
				startTime = currentTime;
			}
			
			if(currentTime > deltaTime + 10000) {
				
				numberOfBalls++;
				EnemyBall[] newBalls = new EnemyBall[numberOfBalls];

				for(int i = 0; i < numberOfBalls - 1; i++) {
					newBalls[i] = new EnemyBall();
					newBalls[i].setPosition(balls[i].getPosition());
					newBalls[i].setVelocity(balls[i].getVelocity());
				}
				newBalls[numberOfBalls - 1] = new EnemyBall();
				newBalls[numberOfBalls - 1].randomizePosition();
				newBalls[numberOfBalls - 1].randomizeVelocity();
			
				balls = newBalls;
				deltaTime = currentTime;
			}
			StdDraw.setPenColor(Color.red);
			for(int i = 0; i < numberOfBalls; i++) {
				StdDraw.filledCircle(balls[i].getPosition().xPos, balls[i].getPosition().yPos, ballRadius);
			}
			
			StdDraw.setPenColor(Color.black);
			StdDraw.filledCircle(player.getPosition().xPos, player.getPosition().yPos, ballRadius);
			StdDraw.text(0.5, 0.1, "Score: " + score + " High Score: " + highScore);
			
			StdDraw.show();
			StdDraw.pause(10);
			
		}
	}
}
