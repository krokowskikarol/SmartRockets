/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kroko
 */
public class Population {

    private int count = 0;
    private final int popSize, lifespan, generation;
    private final Vector spawnPoint = new Vector(400, 500);
    private final ArrayList<DNA> matingPool;
    private final Rocket[] rockets;
    

    public Population(int size, int lifeLength, Target target) {
        matingPool = new ArrayList<>();
        generation = 1;
        lifespan = lifeLength;
        popSize = size;
        rockets = new Rocket[popSize];
        for (int i = 0; i < popSize; i++) {
            rockets[i] = new Rocket(lifespan, spawnPoint, target);
        }
    }

    public Population(Population pop) {
        matingPool = new ArrayList<>();
        lifespan = pop.lifespan;
        generation = pop.generation + 1;
        popSize = pop.popSize;
        rockets = new Rocket[popSize];
        for (int i = 0; i < popSize; i++) {
            rockets[i] = new Rocket(pop.rockets[0], pop.createChildrenGenes());
        }
    }

    private void createMatingPool(){
        matingPool.clear();
        for (Rocket rocket : rockets) {
            
            
            
            for (int i = 0; i < rocket.getFitness(); i++) {
                matingPool.add(rocket.getDna());
            }
        }
        
    }
    public DNA createChildrenGenes() {
        createMatingPool();
        
        Random random = new Random();
        DNA parentA = matingPool.get(random.nextInt(matingPool.size()));
        DNA parentB = matingPool.get(random.nextInt(matingPool.size()));
        for (int i =random.nextInt(parentA.getLength()); i < parentA.getLength(); i++) {
        
                 parentA.setGene(i, parentB.getGene(i));
        
    }
        
        return parentA;
    }

    public void update() {
        for (int i = 0; i < popSize; i++) {
            rockets[i].update(count);
        }
        count++;
    }

    public boolean isAlive() {
        return count < lifespan;
    }
    
    public int checkHowManyHit(){
        int i = 0;
        for (Rocket rocket : rockets) {
            if(rocket.getFitness() == 100) i++;
        }
        return i;
    }

    public Rectangle getRocketShape(int i) {
        return rockets[i].getShape();
    }

    public int getPopSize() {
        return popSize;
    }
    public int getRocketFitness(int i) {
        return rockets[i].getFitness();
    }

    public int getCount() {
        return count;
    }

    public int getGeneration() {
        return generation;
    }
    
}
