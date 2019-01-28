/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kroko
 */
public class Population {

    public int popSize, lifespan,midFit = 0, count = 0, generation = 1;
    public Vector spawnPoint = new Vector(400, 500);
    public ArrayList<DNA> matingPool;
    public Rocket[] rockets;
    

    public Population(int size, int lifeLength, Target target) {
        matingPool = new ArrayList<>();
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
            
            
            
            for (int i = 0; i < rocket.fitness; i++) {
                matingPool.add(rocket.dna);
            }
        }
        
    }
    public DNA createChildrenGenes() {
        createMatingPool();
        
        Random random = new Random();
        DNA parentA = matingPool.get(random.nextInt(matingPool.size()));
        DNA parentB = matingPool.get(random.nextInt(matingPool.size()));
        for (int i =random.nextInt(parentA.getLength()); i < parentA.getLength(); i++) {
        
                 parentA.genes[i] = parentB.genes[i];
        
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
    public void calculatePopFit(){
        int mid = 0;
        for (Rocket rocket : rockets) {
            mid += rocket.fitness;
        }
        midFit = mid/popSize;
    }
    public int checkHowManyHit(){
        int i = 0;
        for (Rocket rocket : rockets) {
            if(rocket.fitness == 100) i++;
        }
        return i;
        
    }
}
