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
    public int x,y;
    Random random = new Random(); //optionally, you can specify a seed, e.g. timestamp.

    public Vector() {
        int max = 2;
    
    
        x =  random.nextInt(max) * (random .nextBoolean() ? -1 : 1);
        y =  random.nextInt(max) * (random .nextBoolean() ? -1 : 1);
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector vector){
        x += vector.getX();
        y += vector.getY();
    }
    public void clear(){
        x = 0;
        y=0;
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
    public void display(){
        System.out.println("("+ x + ";"+ y +")");
    }
}
