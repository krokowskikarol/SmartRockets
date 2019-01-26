package logic;

import java.awt.Rectangle;
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

    public Rocket(int lifeLength, Vector spawnPoint) {
        dna = new DNA(lifeLength);
        position = spawnPoint;
        acceleration = new Vector(0, 0);
        velocity = new Vector();

        shape = new Rectangle(position.getX(), position.getY(), 6, 15);
    }

    public Rocket(Rocket parent, DNA parentsGenes) {
        dna = parentsGenes;
        position = parent.position;
        shape = parent.shape;
    }

    public void applyForce(Vector gene) {
        acceleration.add(gene);
    }

    public void update(int i) {
        acceleration.clear();
        acceleration.add(dna.getGene(i));

        velocity.add(acceleration);

        move();

    }

    private void move() {
        shape.x += velocity.getX();
        shape.y += velocity.getY();
    }

    public void showVelocity() {
        velocity.display();
    }
}
