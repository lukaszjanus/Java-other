/*
 * 24.12.2018  Algorytm Hierarchiczny Aglomeracyjny - liczenie od najmniejszych dystansów miêdzy punktami.
 * 
 * Dzialanie programu:
 * 1. Program wczytuje plik iris.csv (nazwa i sciezka na sztywno) i zapisuje w tabeli string
 * 2. Przerobienie stringa za pomoca split (rozdzielenie danych do tablic) i zapisanie w tablicy obiektow (klasa iris_kontener).
 * 3. Start algorytmu (przekazanie listy obiektow do konstruktora obiektu agl.
 * 4. Przypisanie do kazdego obiektu iris nr klastrow (jako oddzielnej listy obiektow klasy cCenters)
 * 5. Wyliczenie i wyszukanie najmniejszej odleglosci miedzy obiektami (kilka ma odleglosc = 0.0).
 * 6. Uruchomienie petli glownej programu - ponowne liczenie odleglosci i porownywanie z minimalna odlegloscia.
 * 7. W przypadku znalezienia odleglosci minimalnej aktualizacja nr klastrow i zmniejszanie listy klastrow o zduplikowane srodki.
 * 8. Powtarzanie petli do momentu uzyskania trzech klastrow. 
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
