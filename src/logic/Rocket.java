package logic;


import java.awt.Rectangle;


/**
 * Klasa reprezentująca pojedyńczą rakietę
 *
 * @author kroko
 */
public class Rocket {

    public DNA dna;
    public Rectangle shape;
    public Vector acceleration, velocity, position;

    public Rocket(int dnaLength, Vector spawnPoint) {
        dna = new DNA(dnaLength);
        acceleration = new Vector(0, 0);
        velocity = new Vector(0, 0);
        position = spawnPoint;
        
        shape = new Rectangle(position.getX(), position.getY(), 10, 25);
    }

   
}
