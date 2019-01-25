/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author kroko
 */
public class Population {
    public int size,lifespan,count;
    Vector spawnPoint = new Vector(400, 500);
    public Rocket[] rockets;

    public Population() {
        size = 20;
        rockets = new Rocket[size];
        for (int i = 0; i < size; i++) {
            rockets[i] = new Rocket(lifespan, spawnPoint);
        }
    }
    
    
    
    
}
