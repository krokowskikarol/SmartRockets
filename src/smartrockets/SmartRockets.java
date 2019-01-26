/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartrockets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import logic.Population;
import logic.Target;

/**
 *
 * @author kroko
 */
public class SmartRockets extends JComponent implements ActionListener {

    public Population pop = new Population(25,200);
    public static Target tr = new Target();

    String str;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SmartRockets symulation = new SmartRockets();

        JFrame window = new JFrame("Smart rockets");
        window.add(symulation);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setBackground(Color.LIGHT_GRAY);
        window.add(tr);

        Timer t = new Timer(150, symulation);
        t.start();

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.drawString("" + pop.count, 50, 50);
        g2d.drawString("" + pop.generation, 50, 65);
g2d.drawString("" + pop.rockets[0].position.x + "" + pop.rockets[0].position.y + ")", 50, 80);
        g2d.drawString("(" + pop.rockets[5].position.x + ";" + pop.rockets[5].position.y+ ")", 50, 95);

        for (int j = 0; j < pop.popSize; j++) {
            g2d.setColor(Color.BLACK);
            g2d.fill(pop.rockets[j].shape);
            g2d.setColor(Color.YELLOW);
            g2d.draw(pop.rockets[j].shape);

        }

    }

    /*
public void stateActualization(){
        
        
            for (int j = 0; j < pop.popSize; j++) {
                pop.rockets[j].conditionCheck(tr);
                if(pop.rockets[j].isAlive){
                pop.rockets[j].shape.x += pop.rockets[j].dna.genes[pop.rockets[j].count].getX();
                pop.rockets[j].shape.y += pop.rockets[j].dna.genes[pop.rockets[j].count].getY();
                pop.rockets[j].count +=1;
            }
        }
    }
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (pop.isAlive()) {
            pop.update();
            repaint();

        } else {
            //pop.createGenePool(pop.rockets);
            // System.out.println(pop.matingPool.size());
            // pop = new Population(pop, pop.matingPool);
            // System.out.println("population size : " + pop.popSize);   

            pop = new Population(pop);
        }
        /*     
if (i < 200) {

            for (int j = 0; j < pop.popSize; j++) {
        //        pop.rockets[j].conditionCheck(tr);
        //    pop.rockets[j].shape.x += pop.rockets[j].dna.genes[i].getX();
        //      pop.rockets[j].shape.y += pop.rockets[j].dna.genes[i].getY();
            }
        
        
            i++;
            repaint();
        }else{
            i=1;
            for (int j = 0; j < pop.popSize; j++) {
                pop.rockets[j].validate(tr);
            }
            pop.createGenePool(pop.rockets);
            System.out.println(pop.matingPool.size());
            pop = new Population(pop, pop.matingPool);
           System.out.println("population size : " + pop.popSize);
        }
         */
    }

}
