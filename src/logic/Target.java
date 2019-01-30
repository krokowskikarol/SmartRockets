/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;
import javax.swing.JComponent;

/**
 * <h1>Klasa reprezentujaca cel(tarcze strzelnicza)</h1>
 * Target zawiera w sobie 3 zmienne cakowiete odpowiadajace kolejno:
 * wspolrzednej x , y i srednicy tarczy, a takrze punkt center reprezentujacy
 * srodek tarczy.
 *
 * @author kroko
 */
public final class Target extends JComponent {

    private final int x, y, diagonal;
    private final Point center;

    /**
     * Konstruktor losowego Celu tworzy cel w okreslonych arbitralnie granicach
     * x 0-700, y 0-500, oraz srednicy 30 px ustawiajac jednoczesnie srodek
     */
    public Target() {
        Random random = new Random();
        x = random.nextInt(700);
        y = random.nextInt(500);
        diagonal = 30;
        center = new Point(this.x + this.getRadius(), this.y + this.getRadius());
    }

    /**
     * Konstruktor losowego Celu tworzy cel w okreslonych arbitralnie granicach
     * x 0-700, y 0-500, oraz srednicy podanej w parzametrze, ustawiajac
     * jednoczesnie srodek.
     *
     * @param radius Liczba calkowita okreslajaca promien tarczy strzeleckiej.
     */
    public Target(int radius) {
        Random random = new Random();
        x = random.nextInt(700);
        y = random.nextInt(500);
        diagonal = radius * 2;
        center = new Point(this.x + radius, this.y + radius);
    }

    /**
     * Konstruktor celu pozwala na dowolne ustalenie zarowno pozycji jak i
     * srednicy celu
     *
     * @param x Liczba calkowita reprezentujaca wspolrzedna x celu
     * @param y Liczba calkowita reprezentujaca wspolrzedna y celu
     * @param diagonal Liczba calkowita reprezentujaca srednice celu
     */
    public Target(int x, int y, int diagonal) {
        this.x = x;
        this.y = y;
        this.diagonal = diagonal;
        center = new Point(this.x + this.getRadius(), this.y + this.getRadius());
    }

    /**
     * Nadpisana funkcja paint klasy JComponent rysuje tarcze celownicza
     *
     * @param g Obiekt klasy Graphics pozwalajacy rysowac grafike
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.

        Graphics2D g2d = (Graphics2D) g;
        int firstStep = diagonal / 3;
        int secondStep = firstStep * 2;

        g2d.setColor(Color.BLUE);
        g2d.fillOval(x, y, diagonal, diagonal);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x + firstStep / 2, y + firstStep / 2, diagonal - firstStep, diagonal - firstStep);
        g2d.setColor(Color.RED);
        g2d.fillOval(x + secondStep / 2, y + secondStep / 2, diagonal - secondStep, diagonal - secondStep);
    }

    /**
     * Funkcja zwarajaca pozycje środka tarczy celowniczej
     *
     * @return Obiekt Point o wspolrzednych srodka tarzczy.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Funkcja zwracajaca promień tarczy strzelniczej
     *
     * @return Liczba calkowita stanowiaca promien rysowanej tarczy.
     */
    public int getRadius() {
        return diagonal / 2;
    }
}
