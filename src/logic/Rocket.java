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
    public Target target;

    public Rocket(int lifeLength, Vector spawnPoint, Target tar) {
        target = tar;
        this.fitness = 1;
        this.isAlive = true;
        dna = new DNA(lifeLength);
        position = spawnPoint;
        acceleration = new Vector(0, 0);
        velocity = new Vector(0, -2);

        shape = new Rectangle(position.getX(), position.getY(), 6, 15);
    }

    public Rocket(Rocket parent, DNA parentsGenes) {
        this.fitness = 1;
        this.isAlive = true;
        this.target = parent.target;
        dna = parentsGenes;
        position = parent.position;
        shape = new Rectangle(position.getX(), position.getY(), 6, 15);
        acceleration = new Vector(0, 0);
        velocity = new Vector(0, -2);
    }

    public void applyForce(Vector gene) {
        acceleration.add(gene);
    }

    public void update(int i) {
        isAlive = !checkIfHitTarget();
        if (this.isAlive) {
            acceleration.clear();
            acceleration.add(dna.getGene(i));

            velocity.add(acceleration);
            move();
        }
    }

    private Point getWarhead() {
        Point p = new Point();
        p.setLocation(this.shape.x + this.shape.width / 2, this.shape.y);
        return p;
    }

    public void evaluate() {
        if (checkIfHitTarget()) {
            this.fitness = 10;
        } else {
            this.fitness += (int) normalize();
        }
    }

    private double normalize() {
        double i = (1.0 / checkDistance()) * 1000;
        return i;
    }

    private void move() {
        shape.x += velocity.getX();
        shape.y += velocity.getY();
    }

    private boolean checkIfHitTarget() {
        return this.checkDistance() < target.diagonal / 2;
    }

    public int checkDistance() {
        int a, b, c;
        a = (int) (target.getCenter().getX() - this.getWarhead().getX());
        b = (int) (target.getCenter().getY() - this.getWarhead().getY());

        c = (int) Math.sqrt((a * a) + (b * b));
        return c;
    }

    public void showVelocity() {
        velocity.display();
    }
}
