public class Vector2D {

    public Vector2D(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public double xPos = 0.0;
    public double yPos = 0.0;

    public void set(Vector2D newVec) {
        this.xPos = newVec.xPos;
        this.yPos = newVec.yPos;
    }

    public void set(double newX, double newY) {
        this.xPos = newX;
        this.yPos = newY;
    }

    public void add(Vector2D otherVec) {
        this.xPos += otherVec.xPos;
        this.yPos += otherVec.yPos;
    }

    public void add(double newX, double newY) {
        this.yPos += newX;
        this.yPos += newY;
    }

    public void subtract(Vector2D otherVec) {
        this.xPos -= otherVec.xPos;
        this.yPos -= otherVec.yPos;
    }

    public void subtract(double newX, double newY) {
        this.yPos -= newX;
        this.yPos -= newY;
    }

    public void clamp(Vector2D minPosition, Vector2D maxPosition) {
        if(xPos > maxPosition.xPos) {
            xPos = maxPosition.xPos;
        }
        if(xPos < minPosition.xPos) {
            xPos = minPosition.xPos;
        }
        if(yPos > maxPosition.yPos) {
            yPos = maxPosition.yPos;
        }
        if(yPos < minPosition.yPos) {
            yPos = minPosition.yPos;
        }
    }
}
