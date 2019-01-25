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
public class DNA {

    public int length;
    public Vector[] genes;

    public DNA(int size) {
        length = size;
        genes = new Vector[length];

        for (int i = 0; i < length; i++) {
            genes[i] = new Vector();
        }
        
    }

    
    public int getLength() {
        return length;
    }

}
