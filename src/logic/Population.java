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

    public int popSize, lifespan, count = 0, generation = 1;
    public Vector spawnPoint = new Vector(400, 500);
    public ArrayList<DNA> matingPool;
    public Rocket[] rockets;

    public Population(int size, int lifeLength) {
        lifespan = lifeLength;
        popSize = size;
        rockets = new Rocket[popSize];
        for (int i = 0; i < popSize; i++) {
            rockets[i] = new Rocket(lifespan, spawnPoint);
        }
    }

    public Population(Population pop) {
        lifespan = pop.lifespan;
        generation = pop.generation + 1;
        popSize = pop.popSize;
        rockets = new Rocket[popSize];
        for (int i = 0; i < popSize; i++) {
            rockets[i] = new Rocket(pop.rockets[i], createChildrenGenes());
        }
    }

    public DNA createChildrenGenes() {
        Random random = new Random();
        DNA parentA = matingPool.get(random.nextInt(matingPool.size()));
        DNA parentB = matingPool.get(random.nextInt(matingPool.size()));
        while (parentA == parentB) {
            parentB = matingPool.get(random.nextInt(matingPool.size()));
        }
        for (int i = 0; i < parentA.getLength(); i++) {

            if (Math.random() < 0.5) {
                parentA.genes[i] = parentB.genes[i];
            }
        }
        return parentA;
    }

    public void update(Target target) {
        for (int i = 0; i < popSize; i++) {
            rockets[i].update(count, target);
        }
        count++;
    }

    public boolean isAlive() {
        return count < lifespan;
    }
}
