/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * <h1>Klasa reprezentujaca DNA</h1>
 * Zawiera w sobie tablice określonej w konstruktorze dlugosci zawierajaca
 * kolejne wektory sluzace jako material genetyczny dla rakiet - na podstawie
 * tej tablicy bedzie obliczany ruch rakiet.
 *
 * @author kroko
 */
public class DNA {

    private final int length;
    private final Vector[] genes;

    /**
     * Podstawowy konstruktor klasy DNA Tworzy losowa tablice wektorow o podanej
     * w parametrze dlugosci.
     *
     * @param size Dlugosc kodu DNA {tablicy wektorow).
     */
    public DNA(int size) {
        length = size;
        genes = new Vector[length];

        for (int i = 0; i < length; i++) {
            genes[i] = new Vector();
        }

    }

    /**
     * Funkcja zwracajaca konkretny Gen z tablicy DNA.
     *
     * @param i Indeks pozadanego genu.
     * @return Wektor(gen) o podanym indeksie.
     */
    public Vector getGene(int i) {
        return genes[i];
    }

    /**
     * Funkcja pozwalajaca ustawic(podmienic) dowolny gen na inny.
     *
     * @param i Indeks genu ktory chcemy zmienic.
     * @param vector Wektor ktory chcemy ustawic jako gen o podanym indeksie.
     */
    public void setGene(int i, Vector vector) {
        this.genes[i] = vector;
    }

    /**
     * Funkcja zwracajaca dlugosc DNA na rzecz ktorego zostala wywolana.
     *
     * @return Liczba calkowita stanowiaca dlugosc tablicy genow(wektorow)
     */
    public int getLength() {
        return length;
    }

    /**
     * Funkcja diagnostyczna wypisujaca w konsoli cala tablice genow linia po
     * linii.
     */
    public void showDNA() {
        for (int i = 0; i < length; i++) {
            System.out.println(genes[i].getX() + ";" + genes[i].getY());
        }
    }
}
