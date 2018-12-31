/*
 * 24.12.2018  Algorytm Centroidow - algorytm k-srednich
 * 
 * Dzialanie programu:
 * 1.Program wczytuje plik iris.csv (nazwa i sciezka na sztywno) i zapisuje w tabeli string
 * 2.Przerobienie stringa za pomoca split (rozdzielenie danych do tablic) i zapisanie w tablicy obiektow (klasa iris_kontener).
 * 3.Wygenerowanie trzech srodkow (random, zakres od (3,1) do (max,max), maxy wyliczone z wczytanych obiektow.
 * 4.Utworzenie obiektu z klasy centroidy, przekazanie tablicy obiektow i centroidow startowych.
 * 5.Liczenie odleglosci - sprawdzenie, ktora najmniejsza do ktorego centroida i przypisanie numeru centroida do danego obiektu.
 * 6.Zliczenie wynikowych wspolrzednych dla danego klastra + zliczenie ilosci obiektow przy danym klastrze.
 * 7.Wyznaczenie nowych srodkow - ze wspolrzednych wynikowych i ilosci obiektow przy danym centroidzie.
 * 8.Sprawdzenie odleglosci nowych srodkow w stosunku do starych (warunek stopu - error = 0.01).
 * 9.Zerowanie tablic (ilosci obiektow przy centroidzie, tymczasowe wspolrzedne).
 * 10.Jesli error nieosiagniety (flaga w dalszym ciagu true), powtorzenie krokow od 5.
 * 
 * Lukasz Janus
 * 
 * Eksploracja Danych
 * 
 */

package Zad_5a_KNN;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Random;

public class Zad5a {

	/* sciezka do pliku zrodlowego i zmienna na naglowki tabeli */

	static String sciezka = "C:\\java-workspace\\Eksploracja\\src\\Zad_5a_KNN\\iris.csv";
	static String naglowki; // zmienna do przechowywania naglowkow

	/*------------------------------*/

	/* glowna lista obiektow iris - tu sa przechowywane poszczegolne wiersze pliku iris.csv w formie obiektow */

	static ArrayList<iris_kontener> IrisLista = new ArrayList<>();

	/*------------------------------*/

	// zmienne na wartosci maksymalen sepal length i sepal width
	// wykorzystane jako u³atwienie - wyznaczanie zakresu do generowania œrodkow klastrow
	// nie jest niezbedne - mozna wyznaczac srodki dowolnie, nalezy wtedy recznie wpisac ich wspolrzedne
	static double maxLength = 0;
	static double maxWidth = 0;

	/*------------------------------*/

	// trzy losowe œrodki

	static int n1 = 3; // liczba centroidow - jako liczba wierszy z ich wspolrzednymi
	static int n2 = 2; // wymiary tablicy na srodki - dwa wymiary = dwie wspolrzedne
	//static double n[][] = new double[n1][n2];
	static double n[][]={{3.3,3.3},{3.3,3.2},{3.5,2.3}};

	/*------------------------------*/
	/* MAIN */
	/*------------------------------*/

	public static void main(String[] args) {

		IrisLista = wczytajPlik(); // ladowanie iris.csv do listy obiektow
		//View(); //roboczo - wyswietlenie wczytanych danych z tablicy obiektow

		maxSepal(); // max sepal length i sepal width

		/* generownaie losowych srodkow z zakresu od 0 do maxlength/maxwidth */
		//randomCenter(n); //jak wylaczona, to wczytywane sa zadeklarowane srodki
		ViewSrodki(n);

		/* Algorytm k-srednich - uruchomienie */
		
		centroidy knn = new centroidy(n, IrisLista);
		knn.checkDistances();

	}

	/*------------------------------*/
	/* Pozostale Metody */
	/*------------------------------*/

	private static void randomCenter(double[][] n) {

		Random generator = new Random();

		for (int i = 0; i < 3; i++) {

			// roboczo - wyswietlenie wartosci max dla sepal length: 7.9; dla sepal width: 4.4
			// System.out.println(maxLength+" "+maxWidth);

			n[i][0] = (Math.round(generator.nextDouble() * maxLength * 10)) + 3.0; // +3 - 'minimum' dla length
			n[i][1] = (Math.round(generator.nextDouble() * maxWidth * 10)) + 1.0; // +1 - 'minimum' dla width
			n[i][0] /= 10; // zaokraglenie do jednego miesca po przecinku
			n[i][1] /= 10;

		}

	}

	/* wyswietlenie œrodków - wartosci pocz¹tkowe */

	private static void ViewSrodki(double[][] n3) {
		System.out.println("Wygenerowane losowo srodki:");
		for (int i = 0; i < n1; i++) {
			System.out.print((i + 1) + ". ");
			for (int j = 0; j < 2; j++) {
				System.out.print(n[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/* wczytanie pliku iris.csv */

	private static ArrayList<iris_kontener> wczytajPlik() {

		Path path = Paths.get(sciezka);

		ArrayList<String> odczyt = new ArrayList<String>();
		try {
			odczyt = (ArrayList<String>) Files.readAllLines(path); // najpierw pobrane sa cale wiersze jako jeden string
		} catch (IOException ex) {
			System.out.println(" Brak pliku! ");
		}

		naglowki = odczyt.get(0); //pierwszy wiersz zapisany jako oddzielna zmienna

		naglowki = naglowki.replace(",", "\t");
		// System.out.print(naglowki);
		odczyt.remove(0); // usuniecie wiersza z naglowkami - dzieki temu zostaja tylko dane

		// przykladowy wczytany format: '4.8,3.4,1.6,0.2,setosa'
		// System.out.println(odczyt.get(0));

		/* uruchomienie metody rozdzielajacej stringa, tworzacej obiekty i wypelniajacej liste */
		
		ArrayList<iris_kontener> IrisObiekty = naObiekty(odczyt);

		return IrisObiekty;
	}

	/* rozdzielenie string na obiekty - string.split, (separator ',') */
	
	private static ArrayList<iris_kontener> naObiekty(ArrayList<String> write) {
		
		ArrayList<iris_kontener> irisObjects = new ArrayList<>();

		for (String line : write) {
			String[] l = line.split(",");
			iris_kontener iris = new iris_kontener(Double.parseDouble(l[0]), Double.parseDouble(l[1]),
					Double.parseDouble(l[2]), Double.parseDouble(l[3]), l[4]);
			irisObjects.add(iris); // dodanie do listy obiektow
		}
		return irisObjects; // zwrocenie wszystkich obiektow z listy
	}

	/* wyswietlenie iris.csv juz jako obiektow */
	
	public static void View() {
		System.out.println(naglowki);
		System.out.println("############################################################################");

		for (iris_kontener iris : IrisLista) {
			System.out.println(iris.Eksport("\t\t"));
		}
		// System.out.println(IrisLista.get(0).getPetal_length()); //wyciagniecie
		// pojedynczej danej

	}
	
	/* wyszukanie maksymalnych wartosci - do generownaia srodkow */

	public static void maxSepal() {
		for (iris_kontener iris : IrisLista) {

			if (iris.getSepal_length() > maxLength)
				maxLength = iris.getSepal_length();
			if (iris.getSepal_width() > maxWidth)
				maxWidth = iris.getSepal_width();
		}
	}
}
