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
    public Vector  position,acceleration= new Vector(0, 0), velocity= new Vector(0, 0);

    public Rocket(int lifeLength, Vector spawnPoint) {
        dna = new DNA(lifeLength);
        position = spawnPoint;
        
        shape = new Rectangle(position.getX(), position.getY(), 10, 25);
    }
public Rocket(Rocket parent, DNA parentsGenes) {
        dna = parentsGenes;
        position = parent.position;
        shape = parent.shape;
    }
   
}
