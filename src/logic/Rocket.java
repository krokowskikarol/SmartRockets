package logic;

import java.awt.Point;
import java.awt.Rectangle;
/**
 * <h1>Klasa reprezentujaca pojedyncza rakiete </h1>
 * Zawiera w sobie Dna, Shape na podstawie ktorego zostanie narysowana, 
 *  3 wektory odpowiadajaca za pozycje startowa, przyspieszenie i przedkosc,
 * zdatnosc (jak blisko celu przeleciala rakieta), maksymalna zdatnosc 
 * jaka moze rakieta osiagnac, zmienna okreslajaca czy rakieta jest 
 * zywa(czy moze sie poruszac) oraz cel ktory powinna osiagnac.
 * @author kroko
 */
public class Rocket {

    private final DNA dna;
    private final Rectangle shape;
    private final Vector position, acceleration, velocity;
    private int fitness;
    private final int maxFitness;
    private boolean isAlive;
    private final Target target;

    /**
     * Konstruktor pierwszego pokolenia przekazujemy do niego dane takie jak 
     * pozycj startowa, dlugosc zycia oraz cel ktory ma osiagnac 
     * na podstawie tych danych oraz stalych ustalonych odgornie tworzona jest
     * rakieta pierwszego pokolenia - czyli taka o losowo wygenerowanym DNA 
     * @param lifeLength Liczba calkowita okreslajaca dlugosc zycia rakiety
     * @param spawnPoint Vector kreslajacy poczatkowa pozycje rakiety
     * @param tar Target cel do ktorego powinny dazyc rakiety
     */
    public Rocket(int lifeLength, Vector spawnPoint, Target tar) {
        this.maxFitness = 100;
        target = tar;
        this.fitness = 0;
        this.isAlive = true;
        dna = new DNA(lifeLength);
        position = spawnPoint;
        acceleration = new Vector(0, 0);
        velocity = new Vector(0, 0);

        shape = new Rectangle(position.getX(), position.getY(), 4, 15);
    }
/**
 * Konstruktor kolenych pokoleń 
 * przekazujemy do niego 2 parametry odpowiadajace kolejno przykladowej rakiecie
 * z ktorej ta pobierze sobie pewne (w tym momecie juz) stale jak np pozycje 
 * startowa, oraz wygenerowanemu w poprzedniej populacji DNA.    
 * @param parent obiekt Rocket z ktorego pobrane zostana niezbedne dane(pozycja
 * startowa oraz maksymalna zdatnosc)
 * @param parentsGenes obiekt typu DNA reprezentujacy wygenerowane w 
 * poprzedniej populacji DNA z 2 rodzicow; 
 */
    public Rocket(Rocket parent, DNA parentsGenes) {
        this.maxFitness = parent.maxFitness;
        this.fitness = 0;
        this.isAlive = true;
        this.target = parent.target;
        dna = parentsGenes;
        // mutate();
        position = parent.position;
        shape = new Rectangle(position.getX(), position.getY(), 4, 15);
        acceleration = new Vector(0, 0);
        velocity = new Vector(0, 0);
    }

    //wymaga glebszego przemyslenia min. kiedy ma dzialac, wprowadzanie jej w
    //kazdym kolejnym pokoleniu losowo wprowadza niezwykly haos
    /**
     * Funkcja mutujaca DNA
     * jej celem jest wprowadzenie roznorodnosci w zmniejszajacej sie
     * z czasem puli genow pop przez losowa podmiane konkretnych genow(wektorow)
     * na nowe losowe.
     */
    private void mutate() {
        for (int i = 0; i < this.dna.getLength(); i++) {
            if (Math.random() < 0.01) {
                this.dna.setGene(i, new Vector());
                System.out.println("mutacja");
            }
        }
    }

    //mozna nieco poprawic wydajnsc
    /**
 * Funkcja aktualizujaca stan rakiety
 * w pierwszym kroku sprawdza czy rakieta jest ciagle zywa(nie osiagnela celu)
 * jezeli tak, w nastepnym kroku zeruje przyspieszenie a nastepnie zwieksza je 
 * o wektor(gen) o podanym w parametrze indeksie, dalej dodaje przyspieszenie 
 * do predkosci(potencjalnie zmienajac kierunek lotu) i wywoluje funcje move()
 * konczy dzialanie na poddaniu rakiety ewaluacji.
 * 
 * @param i Liczba calkowita stanowiaca indeks genu na podstawie ktorego
 * zostanie zaktualizowany stan rakiety 
 */
    public void update(int i) {
        isAlive = !checkIfHitTarget();
        if (this.isAlive) {
            acceleration.clear();
            acceleration.add(dna.getGene(i));

            velocity.add(acceleration);
            move();
            evaluate();
        }
    }
/**
 * Funkcja zwraca pozycje glowicy rakiety
 * @return obiekt Point stanowiacy srodek szczytu ksztaltu rakiety
 * (na podstawie tego mijsca liczymy wszelkie kolizje)
 */
    private Point getWarhead() {
        Point p = new Point();
        p.setLocation(this.shape.x + this.shape.width / 2, this.shape.y);
        return p;
    }

    /**
     * Funkcja aktualizujaca zdatnosc Rakiety
     * jej zadaniem jest sprawdzenie w danym kroku jak daleko od celu jest rakieta
     * oraz aktualizowanie jej zdatnosci jezeli w tym kroku jest ona wieksza
     * niz dotychczas, jezeli rakieta juz trafila w cel funkcja przypisuje jej 
     * maksymalna zdatnosc oraz w przyszlosci pominie sprawdzanie.
     * Idea stojaca za ta funkcja jest aktualizacja krok po kroku jak blisko
     * celu przelatuje rakieta w celu zwiekszenia szansy na przekazanie genow 
     * dalej rakietom ktore w ktoryms momecie lotu znajda sie blisko celu 
     * jednoczesnie w niego nie trafiajac.
     */
    public void evaluate() {
        if (fitness < maxFitness) {
            if (checkIfHitTarget()) {
                this.fitness = maxFitness;
            } else {
                int check = (int) normalize();
                if (fitness < check) {
                    fitness = check;
                }
            }
        }
    }
/**
 * Funkcja zwracajaca znormalizowana odleglosc do celu
 * zadaniem tej funkcji jest ustalenie jak daleko jest rakieta od targetu ktory ma 
 * osiagnac.
 * W tym celu dzielimy 1 \ odleglosc-do-celu a nastepnie mnozymy ta
 * liczbe przez arbitralnie ustalone 1000
 * *np  1/302 = 0,0033112583    0,0033112583*1000 = 3,3....
 *  w tym wypadku funkcja zwroci nam 3,3.. itd
* @return Liczbe zmiennoprzecinkowa stanowiaca znormalizowana odleglosc od celu 
 */
    private double normalize() {
        double i = (1.0 / checkDistance()) * 1000;
        return i;
    }
/**
 * Funkcja aktualizujaca pozycje Rakiety
 * zadanime funkcji jest aktualizacja wspolrzednych obiektu 
 * Shape(reprezentujacego graficznie pojedyncza rakiete) o jej predkosc(Vector)
 */
    private void move() {
        shape.x += velocity.getX();
        shape.y += velocity.getY();
    }

    /**
     * Funkcja sprawdzajaca czy rakieta trafila w cel
     * jej zadaniem jest sprawdzic czy odleglosc do srodka celu jest mniejsza 
     * niz promien celu(tarczy) 
     * @return true w wypadku gdy odleglosc rakiety do centrum celu jest
     * mniejsza niz jego promien
     */
    private boolean checkIfHitTarget() {
        return this.checkDistance() < target.getRadius();
    }

    /**
     * Funkcja sprawdzajaca odleglosc miedzy glowica rakiety a centrum celu
     * w oparciu o twierdzenie Pitagorasa mierzy odleglosc miedzy dwoma punktami
     * gdzie:
     * a oznacza roznice odleglosci w osi x
     * b oznacza roznice odleglosci w osi y
     * @return c stanowiace pierwiastek z sumy podniesionych do kwadratu a i b (Pitagoras) 
     */
    public int checkDistance() {
        int a, b, c;
        a = (int) (this.target.getCenter().getX() - this.getWarhead().getX());
        b = (int) (this.target.getCenter().getY() - this.getWarhead().getY());
        c = (int) Math.sqrt((a * a) + (b * b));
        return c;
    }
/**
 * Funkcja zracajaca DNA rakiety
 * @return DNA rakiety na rzecz ktorej zostala wywolana
 */
    public DNA getDna() {
        return dna;
    }
/**
 * Funkcja zracajaca zmienna fitness(zdatnosc) rakiety
 * @return Liczba calkowita okresclajaca zdatnosc rakiety na rzecz ktorej zostala wywolana
 */
    public int getFitness() {
        return fitness;
    }
/**
 * Funkcja zwracajaca obiekt Shape stanowiacy ksztalt rakiety
 * @return Shape stanowiacy ksztalt rakiety na rzecz ktorej zostala wywolana
 */
    public Rectangle getShape() {
        return shape;
    }

    /**
     * Funkcja diagnostyczna wywolujaca funkcje display() na rzecz wektora 
     * okreslajacego predkosc danej rakiety
     */
    public void showVelocity() {
        velocity.display();
    }
}
