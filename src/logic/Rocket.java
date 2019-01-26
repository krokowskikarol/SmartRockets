package logic;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Klasa reprezentująca pojedyńczą rakietę
 *
 * @author kroko
 */
public class Rocket {

    public DNA dna;
    public Rectangle shape;
    public Vector position, acceleration, velocity;
    public int fitness;
    private boolean isAlive;

    public Rocket(int lifeLength, Vector spawnPoint) {
        this.fitness = 0;
        this.isAlive = true;
        dna = new DNA(lifeLength);
        position = spawnPoint;
        acceleration = new Vector(0, 0);
        velocity = new Vector(0,-2);
        
        shape = new Rectangle(position.getX(), position.getY(), 6, 15);
    }

    public Rocket(Rocket parent, DNA parentsGenes) {
        this.fitness = 0;
        this.isAlive = true;
        dna = parentsGenes;
        position = parent.position;
        shape = parent.shape;
    }

    public void applyForce(Vector gene) {
        acceleration.add(gene);
    }

    public void update(int i, Target target) {
        isAlive = !checkIfHitTarget(target);
        if(this.isAlive){
        acceleration.clear();
        acceleration.add(dna.getGene(i));

        velocity.add(acceleration);
        move();
        }
    }
    private Point getWarhead(){
        Point p = new Point();
        p.setLocation(this.shape.x+this.shape.width/2, this.shape.y);
        return p;
    }
    public void evaluate(Target target){
        if(checkIfHitTarget(target)){
            fitness = 100;
        }else{
            fitness =  (int) normalize(target);
        }
    }
    private double normalize(Target target){
        double i = (1.0/checkDistance(target)) * 1000;
        return i;
    }
    private void move() {
        shape.x += velocity.getX();
        shape.y += velocity.getY();
    }
    private boolean checkIfHitTarget(Target target){
      return this.checkDistance(target)< target.diagonal/2;
    }
    public int checkDistance(Target target){
        int a,b,c;
        a= (int) (target.getCenter().getX() - this.getWarhead().getX());
        b = (int) (target.getCenter().getY() - this.getWarhead().getY());
        
        c = (int) Math.sqrt((a*a)+(b*b));
        return c ;
    }
    
    public void showVelocity() {
        velocity.display();
    }
}
