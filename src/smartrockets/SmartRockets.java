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
import logic.DNA;
import logic.Population;
import logic.Rocket;
import logic.Target;

/**
 *
 * @author kroko
 */
public class SmartRockets extends JComponent implements ActionListener {

  
    public static Target tr = new Target(50);
  public Population pop = new Population(100, 120,tr);
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

        g2d.setColor(Color.RED);
        g2d.drawString("" + pop.count, 50, 50);
        g2d.drawString("" + pop.generation, 50, 65);
        g2d.drawString("" + pop.rockets[0].position.x + "" + pop.rockets[0].position.y + ")", 50, 80);
//        g2d.drawString("(" + pop.rockets[5].position.x + ";" + pop.rockets[5].position.y + ")", 50, 95);

        for (int j = 0; j < pop.popSize; j++) {
            g2d.setColor(Color.BLACK);
            g2d.fill(pop.rockets[j].shape);
            g2d.setColor(Color.YELLOW);
            g2d.draw(pop.rockets[j].shape);

        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (pop.isAlive()) {
            pop.update();
        repaint();
        }
        else{
            
           
            pop = new Population(pop);
            /*
            for (Rocket rocket : pop.rockets) {
                rocket.evaluate();
                System.out.println("****************");
                System.out.println("dystans");
                System.out.println(rocket.checkDistance());
                System.out.println("fitness");
                
                System.out.println(rocket.fitness);
            }
            */
        }

    }
}
