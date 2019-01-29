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
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import logic.DNA;
import logic.Population;
import logic.Rocket;
import logic.Target;

/**
 *
 * @author kroko
 */
public class SmartRockets extends JComponent implements ActionListener {

    public static Target tr = new Target(30);
    public Population pop = new Population(100, 60, tr);
    String str;
    public int statFit;

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

        Timer t = new Timer(60, symulation);
        t.start();

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

    
        drawStatisstics(g2d);
        drawPopulation(g2d);
        
        // śledzenie jednej arbitralnie wybranej rakiety 
        //(rakiety zaczynają sie nakładać na siebie z czasem)
        g2d.setColor(Color.MAGENTA);
        g2d.fill(pop.getRocketShape(0));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (pop.isAlive()) {
            pop.update();

            repaint();
        } else {
            pop = new Population(pop);
        }
}
    public void drawStatisstics(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.drawString("Step : " + pop.getCount(), 50, 50);
        g2d.drawString("Generation : " + pop.getGeneration(), 50, 65);
        g2d.drawString("Rockets that hit target : " + pop.checkHowManyHit() + "/" + pop.getPopSize(), 50, 95);
        g2d.drawString("rocket 0 fitness : " + pop.getRocketFitness(0), 50, 110);

    }
    public void drawPopulation(Graphics2D g2d){
        int alpha = 127;
        Color back = new Color(0, 0, 0, alpha);
        Color border = new Color(225, 225, 0, alpha/2);
        for (int j = 1; j < pop.getPopSize(); j++) {
            
            g2d.setColor(back);
            g2d.fill(pop.getRocketShape(j));
            g2d.setColor(border);
            g2d.draw(pop.getRocketShape(j));

        }
        
    }
}
