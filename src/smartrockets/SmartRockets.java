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
 * <h1>Glowna klasa programu</h1>
 * Tutaj odbywa sie cala magia(prezentacja)
 * 
 * @author kroko
 */
public class SmartRockets extends JComponent implements ActionListener {

    public static Target tr = new Target(30);
    public Population pop = new Population(100, 60, tr);
   
    /**
     * Funkcja main w ktorej teorzymy formatke, dodajemy cel dla rakiet 
     * do formatki oraz tworzymy timer 
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // tworzenie formatki na ktorej bedzie wyswietlana symulacja
        SmartRockets symulation = new SmartRockets();

        JFrame window = new JFrame("Smart rockets");
        window.add(symulation);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setBackground(Color.LIGHT_GRAY);
        
        //dodanie targetu do formatki
        window.add(tr);

        //zainicjowanie i uruchomienie timera
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
        //w tym miejscu nastepuje sprawdzenie czy populacja jest ciagle
        //zywa(count<lifespan) oraz aktualizacja jej stanu
        if (pop.isAlive()) {
            pop.update();

            repaint();
        } else {
            // w wypadku gdy poprzednia populacja zakonczyla zywot 
            // tworzymy na jej podstawie kolejna generacje rakiet
            pop = new Population(pop);
        }
}
    /**
     * Funkcja rysujaca na formatce niezbedne dane ukazujace jak radzi sobie
     * dana populacja rakiet. Danymi tymi sa:
     * Count - konkretny krok dna na ktorym w danej chwili jestesmy,
     * Generation - obecna generacja rakiet,
     * Ilosc rakiet ktore trafily w okreslony cel
     * Zdatnosc arbitralnie wybranej rakiety (w tym wypadku rakiety o indeksie 0)
     * @param g2d Obiekt Graphics2D niezbedny do rysowania grafiki 
     */
    public void drawStatisstics(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.drawString("Step : " + pop.getCount(), 50, 50);
        g2d.drawString("Generation : " + pop.getGeneration(), 50, 65);
        g2d.drawString("Rockets that hit target : " + pop.checkHowManyHit() + "/" + pop.getPopSize(), 50,80);
        g2d.drawString("rocket 0 fitness : " + pop.getRocketFitness(0), 50, 95);

    }
    /**
     * Funkcja odpowiadjaca za rysowanie przebiegu symulacji.
     * Rysuje wszystkie rakiety w formi 
     * polprzezroczystej(w celu lepszej widocznosci)
     * plus rakiete o indeksie 0 w osobnym nieprzezroczystym kolorze w celu
     * latwiejszego sledzenia jej lotu
     * @param g2d Obiekt Graphics2D niezbedny do rysowania grafiki 
     */
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
