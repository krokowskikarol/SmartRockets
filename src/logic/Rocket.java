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
    public Vector  position,acceleration, velocity;

    public Rocket(int lifeLength, Vector spawnPoint) {
        dna = new DNA(lifeLength);
        position = spawnPoint;
        acceleration= new Vector(0,0); 
        velocity= new Vector(0,0);

        shape = new Rectangle(position.getX(), position.getY(), 6, 15);
    }
public Rocket(Rocket parent, DNA parentsGenes) {
        dna = parentsGenes;
        position = parent.position;
        shape = parent.shape;
    }
public void applyForce(Vector gene){
    acceleration.add(gene);
}
public void update(int i){
    applyForce(this.dna.getGene(i));
    velocity.add(acceleration);
    position.add(velocity);
    move();
    
    acceleration.clear();
}
private void move(){
    shape.setLocation(position.getX(), position.getY());
}

}
