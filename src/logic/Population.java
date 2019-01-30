/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 * <h1>Klasa reprezentujaca populacje rakiet</h1>
 * klasa zawiera w sobe licznik okreslajacy z ktorego genu w danum kroku
 * korzystamy, pola stale typu int oznaczajace wielkosc populacji, dlugosc jej
 * zycia oraz numer generacji ktora obecnie jest aktywna. Posiada ona takze pole
 * typu Vector reprezentujace punkt startowy dla rakiet znajdujacych sie w
 * populacji, pole typu ArrayList<> stanowiace pule wszystkich DNA z ktorych
 * rozmnozona zostanie kolejna generacja, oraz oczywiscie tablice zawierajaca
 * kwszystkie rakiety wchodzace w sklad populacji
 *
 * @author kroko
 */
public class Population {

    private int count = 0;
    private final int popSize, lifespan, generation;
    private final Vector spawnPoint = new Vector(400, 500);
    private final ArrayList<DNA> matingPool;
    private final Rocket[] rockets;

    /**
     * Konstruktor pierwszej populacji okreslamy w nim parametry takie jak
     * wielkosc populacji, dlugosc jej zycia oraz cel ktory rakiety maja
     * osiagnac. Generacja ostaje ustalona na 1 z razcji na to ze jest to
     * pierwsza generacja rakiet.
     *
     * @param size Liczba calkowita okreslajaca wielkosc populacji.
     * @param lifeLength Liczba calkowita okreslajaca dlugosc zycia polulacji.
     * @param target obiekt Target okreslajacy cel ktory powinny osiagnac
     * rakiety.
     */
    public Population(int size, int lifeLength, Target target) {
        matingPool = new ArrayList<>();
        generation = 1;
        lifespan = lifeLength;
        popSize = size;
        rockets = new Rocket[popSize];
        for (int i = 0; i < popSize; i++) {
            rockets[i] = new Rocket(lifespan, spawnPoint, target);
        }
    }

    /**
     * Konstruktor kolejnych generacji na podstawie przekazanej populacji tworzy
     * kolejna generacje rakiet pobierajac wszelkie niezbedne dane z
     * dostarczonego parametru
     *
     * @param pop obiekt Populacja na podstawie ktorej zostanie stworzona
     * kolejna generacja rakiet
     */
    public Population(Population pop) {
        matingPool = new ArrayList<>();
        lifespan = pop.lifespan;
        generation = pop.generation + 1;
        popSize = pop.popSize;
        rockets = new Rocket[popSize];
        for (int i = 0; i < popSize; i++) {
            rockets[i] = new Rocket(pop.rockets[0], pop.createChildrenGenes());
        }
    }

    /**
     * Funkcja uzupelniajaca liste potecjalnych rodzicow dla przyszlego
     * pokolenia w oparciu o zdatnosc(fitness) danej rakiety dodaje tyle razy
     * jej dna do puli rodzicow np jezeli fitness == 5 dna zostanie dodane 5
     * razy itp
     */
    private void createMatingPool() {
        matingPool.clear();
        for (Rocket rocket : rockets) {
            for (int i = 0; i < rocket.getFitness(); i++) {
                matingPool.add(rocket.getDna());
            }
        }
    }

    /**
     * Funkcja tworzaca nowe dna jej zadaniem jest losowe pobranie dwojki
     * rodzicow z listy matinPool nastepnie krzyzuje dna rodzicow w losowo
     * wybranym punkcie i zgodnie z zasada gdzie geny mniejsze od i naleza do
     * rodziac A a kolejne Do rodzica B
     *
     * @return DNA zbudowane w losowy sposob z 2 rodzicow pobranych z listy
     * matingPool
     */
    public DNA createChildrenGenes() {
        createMatingPool();

        Random random = new Random();
        DNA parentA = matingPool.get(random.nextInt(matingPool.size()));
        DNA parentB = matingPool.get(random.nextInt(matingPool.size()));
        for (int i = random.nextInt(parentA.getLength()); i < parentA.getLength(); i++) {
            parentA.setGene(i, parentB.getGene(i));
        }
        return parentA;
    }

    /**
     * Funkcja aktualizujaca status rakied dla wszystkich rakiet w populacji jej
     * zadaniem jest wywolanie funkcji update() dla kazdej rakiety z populacji
     * podajac jako parametr licznik count , a nastepnie zwiekszenie licznika o
     * 1 ustawiajac go w ten sposob na nastepny krok
     */
    public void update() {
        for (int i = 0; i < popSize; i++) {
            rockets[i].update(count);
        }
        count++;
    }

    /**
     * Funkcja sprawdzajaca czy populacja jest zywa jej zadaniem sjest controla
     * czy licznik nie przekroczyl dlugosci zycia populacji sluzy ona do
     * determinacji czy populacja powinna byc dalej aktualizowana
     *
     * @return true jezeli licznik(count) jest mniejszy od dlugosci
     * zycia(lifespan)
     */
    public boolean isAlive() {
        return count < lifespan;
    }

    /**
     * Funkcja sprawdzajaca ile rakiet w populacji trafilo w cel sprawdza ile
     * rakiet w populacji posiada maksymana zdatnosc(maxFitness)
     *
     * @return
     */
    public int checkHowManyHit() {
        int i = 0;
        for (Rocket rocket : rockets) {
            if (rocket.getFitness() == rocket.getMaxFitness()) {
                i++;
            }
        }
        return i;
    }

    /**
     * Funkcja zrwacajaca Shape danej rakiety jej zadaniem z=jest zwrocic
     * ksztalt rakiety w oparciu o podany indeks
     *
     * @param i Liczba calkowita okresclajaca indeks pozadanej rakiety
     * @return Shape stanowiacy graficzna reprezentacje rakiety
     */
    public Rectangle getRocketShape(int i) {
        return rockets[i].getShape();
    }

    /**
     * Funkcja zwracajaca wielkosc populacji
     *
     * @return Liczba calkowita stanowiaca wielkosc populacji
     */
    public int getPopSize() {
        return popSize;
    }

    /**
     * Funkcja zwracajaca zdatnosc okreslonej rakiety jej zadaniem jest zwrocic
     * wartosc fitness rakiety z tablicy o indeksie okreslonym przez prametr
     *
     * @param i Liczba calkowita okresclajaca indeks pozadanej rakiety
     * @return Liczba calkowita stanowiaca zdatnosc okreslonej indeksem rakiety
     */
    public int getRocketFitness(int i) {
        return rockets[i].getFitness();
    }

    /**
     * Funkcja zwracajaca wartosc licznika(count)
     *
     * @return Liczba calkowita stanowiaca wartosc licznika
     */
    public int getCount() {
        return count;
    }

    /**
     * Funkcja zwracajaca numer generacji
     *
     * @return Liczba calkowita stanowiaca numer generacji
     */
    public int getGeneration() {
        return generation;
    }

}
