/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Random;



/**
 *
 * @author kroko
 */
public class Vector {
    public int x,y,range;
    

    public Vector() {
        range = 2;
    Random random = new Random(); //optionally, you can specify a seed, e.g. timestamp.
    
        x =  random.nextInt(range) * (random .nextBoolean() ? -1 : 1);
        y =  random.nextInt(range) * (random .nextBoolean() ? -1 : 1);
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector vector){
        x += vector.getX();
        y += vector.getY();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
