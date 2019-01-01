/*
 * 24.12.2018  Algorytm Hierarchiczny Aglomeracyjny - liczenie od najmniejszych dystansów miêdzy punktami.
 * 01.01.2018  Wersja ostateczna - poprawiona petla glowna. 
 * 
 * Dzialanie programu:
 * 1. Program wczytuje plik iris.csv (nazwa i sciezka na sztywno) i zapisuje w tabeli string
 * 2. Przerobienie stringa za pomoca split (rozdzielenie danych do tablic) i zapisanie w tablicy obiektow (klasa iris_kontener).
 * 3. Start algorytmu (przekazanie listy obiektow do konstruktora obiektu agl.
 * 4. Przypisanie do kazdego obiektu iris nr klastrow.
 * 5. Stworzenie listy cClusters do ktorej wedrowac beda informacje o odleglosciach.
 * 6. Petla - Wyliczenie i wyszukanie najmniejszej odleglosci miedzy obiektami (kilka ma odleglosc = 0.0).
 * 7. Sortowanie listy cCenters wg odleglosci obiektow iris.
 * 8. Uruchomienie glownej petli programu - pobieranie z cClusters dwoch indeksow obiektow iris z najmniejsza odlegloscia (pierwsze z listy)
 * 9. Porownanie numerow klastrow dwoch obiektow iris.
 * 9a Jesli takie same, to usuniecie od razu pierwszego elementu listy.
 * 9b Jesli rozne, to nadpisanie do biezacego elementu nowego nru klastra i nastepnie w petli przeszukanie, czy inne elementy maja ten sam nr klastra (scalenie); potem usuniecie elementu z listy cClusters.
 * 10. Sprawdzenie warunku stop - dodanie do Set listy klastrow i sprawdzenie rozmiaru.
 * 11. Jesli rozmiar > 3, przerwanie petli; jesli wiekszy - powtorzenie krokow od pkt. 8.
 * 12. Wyswietlenie wynikow - lista elementow z aktualnym nr-em klastra.
 * 
 * Lukasz Janus
 * 
 * Eksploracja Danych
 * 
 */

package Zad_6a;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Random;

public class Zad6a {

	// plik zrodlowy i zmienna na naglowki tabeli

	static String sciezka = "C:\\GIT\\Java-other\\CS-study_Data Eksploration\\src\\Zad_6a\\iris.csv";
	
	static String naglowki; // zmienna do przechowywania naglowkow

	/*------------------------------*/

	// glowna lista obiektow iris

	static ArrayList<iris_kontener> IrisLista = new ArrayList<>();

	/*------------------------------*/
	/* MAIN */
	/*------------------------------*/

	public static void main(String[] args) {

		IrisLista = wczytajPlik(); // ladowanie iris.csv do listy obiektow
		//View(); //roboczo - wyswietlenie wczytanych danych z tablicy obiektow

		// Algorytm aglomeracyjny - uruchomienie

		Aglomeracyjny agl = new Aglomeracyjny(IrisLista);
		agl.fnStart();
	}

	/*------------------------------*/
	/* Pozostale Metody */
	/*------------------------------*/

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
	}

}
