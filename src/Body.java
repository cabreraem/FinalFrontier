/**
 * Created by emiliacabrera on 9/15/16.
 */
public class Body {

    private final static double G = 6.67e11;

    private double mass;
    private double radius;
    private double posX;
    private double posY;
    private double velocityX;
    private double velocityY;
    private double accelX;
    private double accelY;

    public Body(double m, double r, double x, double y, double vX, double vY) {
        mass = m;
        radius = r;
        posX = x;
        posY = y;
        velocityX = vX;
        velocityY = vY;
    }

    //Getters
    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    //Methods
    public double getGravity(double d){

        double g = G*mass / Math.pow(d,2);

        return g;
    }

    public void force(Body b){
        double dX = b.getPosX() - posX;
        double dY = b.getPosY() - posY;

        accelX+= b.getGravity(dX);
        accelY+= b.getGravity(dY);
    }

    public void move(int t){
        velocityX += accelX * t;
        velocityY += accelY *t;

        posX += velocityX * t;
        posY += velocityY * t;

        accelX = 0;
        accelY = 0;
    }

}
