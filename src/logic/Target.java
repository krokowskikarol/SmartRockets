/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JComponent;

/**
 *
 * @author kroko
 */
public class Target extends JComponent{
    public int x,y,diagonal;

    public Target() {
        Random random = new Random();
        
        x =  random.nextInt(700);
        y =  random.nextInt(500);
        diagonal = 30;
    }

    public Target(int x, int y, int diagonal) {
        this.x = x;
        this.y = y;
        this.diagonal = diagonal;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        Graphics2D g2d = (Graphics2D) g;
        int firstStep = diagonal/3;
        int secondStep= firstStep*2;
        
        g2d.setColor(Color.BLUE);
        g2d.fillOval(x, y, diagonal, diagonal);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x+firstStep/2, y+firstStep/2, diagonal-firstStep, diagonal-firstStep);
        g2d.setColor(Color.RED);
        g2d.fillOval(x+secondStep/2, y+secondStep/2, diagonal-secondStep, diagonal-secondStep);
        
    }
public int getCenterX(){
    return this.x+diagonal/2;
} 
public int getCenterY(){
    return this.y+diagonal/2;
} 
    
    
    
    
}
