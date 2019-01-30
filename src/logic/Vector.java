/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Random;

/**
 * <h1>Reprezentacja Wektoru w 2D</h1>
 * Klasa reprezenytujaca wspolrzedne w przestrzeni dwu wymiarowej(x,y). Mozemy
 * stworzyc losowy wektor lub tez sprecyzowac dokladne wspolrzedne.
 *
 * @author kroko
 *
 */
public class Vector {

    private int x, y;
    Random random = new Random(); //optionally, you can specify a seed, e.g. timestamp.

    /**
     * Konstruktor tworzacy losowy wektor. na potrzeby tego programu ma ustaloną
     * odgornie maksumalną wartosc z ktorej losuje wspolrzedne na 2.
     */
    public Vector() {
        int max = 2;

        x = random.nextInt(max) * (random.nextBoolean() ? -1 : 1);
        y = random.nextInt(max) * (random.nextBoolean() ? -1 : 1);
    }

    /**
     * Konstruktor tworzacy wektor o sprecyzowanych wartosciach.
     *
     * @param x Liczba calkowita reprezentujaca wspolrzedna x w przestrzeni 2D.
     * @param y Liczba calkowita reprezentujaca wspolrzedna y w przestrzeni 2D.
     */
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Funcja dodajaca do danego wektora drugi wektor w praktyce dodaje
     * wspolrzedna x danego jako parametr wektora do wspolrzednej x wektora na
     * ktorego rzecz zostala wywolana i analogicznie postepuje z wspolredna y.
     *
     * @param vector Wektor ktory chcemy dodac.
     */
    public void add(Vector vector) {
        x += vector.getX();
        y += vector.getY();
    }

    /**
     * Funkcja ustawiajaca wartosci x i y wektora na ktorego rzecz zostala
     * wywolana na 0.
     */
    public void clear() {
        x = 0;
        y = 0;
    }

    /**
     * Funkcja zwracajaca wspolrzedna x wektora
     *
     * @return Liczba calkowita stanowiaca wspolrzedna x wektora
     */
    public int getX() {
        return x;
    }

    /**
     * Funkcja zwracajaca wspolrzedna y wektora
     *
     * @return Liczba calkowita stanowiaca wspolrzedna y wektora
     */
    public int getY() {
        return y;
    }

    /**
     * Funkcja pozwalajaca ustawic wspolrzedna x wektora na podstawie podanego
     * parametru
     *
     * @param x Liczba calkowita ktora chcemy ustawic jako wspolrzedna x wektora
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Funkcja pozwalajaca ustawic wspolrzedna y wektora na podstawie podanego
     * parametru
     *
     * @param y Liczba calkowita ktora chcemy ustawic jako wspolrzedna y wektora
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Funcja wyswietlajaca w konsoli wspolrzedne wektora
     */
    public void display() {
        System.out.println("(" + x + ";" + y + ")");
    }
}
