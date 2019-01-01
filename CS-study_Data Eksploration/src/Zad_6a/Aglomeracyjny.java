package Zad_6a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Aglomeracyjny {

	ArrayList<iris_kontener> Iris; // lista na obiekty z pliku iris.csv
	ArrayList<cCenters> cClusters = new ArrayList<>(); // lista - nr-y obiektow i odleglosc

	int r; // rozmiar listy wczytanych obiektow
	static int licznik = 0;

	/*---------------------------------------*/
	/*------------ konstruktor --------------*/
	/*---------------------------------------*/

	public Aglomeracyjny(ArrayList<iris_kontener> IrisLista) {
		Iris = IrisLista;
		r = Iris.size();
		// r = 8; //do testow
	}

	/*---------------------------------------*/
	/*-----------  Metody  ------------------*/
	/*---------------------------------------*/

	/*--- start algorytmu - petla glowna ----*/

	public void fnStart() {

		/* startowe przypisanie srodkow - kazdy obiekt jest klastrem startowym */

		for (int i = 0; i < r; i++) {
			Iris.get(i).iCluster = i;
		}

		double dTemp;

		/* iloczyn kartezjanski - odleglosc 'kazdy obiekt z kazdym' */

		for (int i = 0; i < r - 1; i++) {
			for (int j = i + 1; j < r - 1; j++) {
				// for (int i = 0; i < 7 - 1; i++) {
				// for (int j = i + 1; j < 7 - 1; j++) {

				dTemp = fnCheckDistances(Iris.get(i), Iris.get(j), i, j);
				cCenters c = new cCenters(i, j, dTemp);
				cClusters.add(c);
				// System.out.println(i+" "+ j + " dtemp: "+ dTemp); //roboczo
			}
		}

		/* sortowanie obiektow wg odleglosci */

		Collections.sort(cClusters);

		/* petla glowna algorytmu */

		int g;
		Set<Integer> Count;
		do {

			int a = cClusters.get(0).getiObiektA();
			int b = cClusters.get(0).getiObiektB();

			if (Iris.get(b).iCluster == Iris.get(a).iCluster) {

				licznik++;
				cClusters.remove(0); // przerwanie jesli ten sam klaster
				
				// System.out.println(" zduplikowane " + a + " " + b);
				//roboczo - do recznych iteracji

			} else {
				licznik++;
				// System.out.println(" zamieniane " + a + " " + b);
				//roboczo - do recznychiteracji

				
				int tempClust = Iris.get(b).iCluster; // zmienna nadpisywanego nr-u klastra do petli scalajacej
				Iris.get(b).iCluster = Iris.get(a).iCluster; // przypisanie klastra

				/* uaktualnienie 'macierzy przyleglosci' - czyli scalenie klastrów */

				for (int i = 0; i < r; i++) {
					if (Iris.get(i).iCluster == tempClust) {
						Iris.get(i).iCluster = Iris.get(a).iCluster;
					}
				}
				cClusters.remove(0);
			}

			/* przygotowanie warunku stop - ile jest unikalnych nr-ow klastrow */

			Count = new HashSet<>();
			for (int i = 0; i < r; i++) {
				Count.add(Iris.get(i).iCluster);
				// System.out.println("Dodawanie wszystkich klastrow: "+ Iris.get(i).iCluster);
			}
			g = Count.size();
			//System.out.println("Rozmiar po dodaniu: " + g);

			// Scanner scan = new Scanner(System.in); // do recznych iteracji - roboczo
			// scan.next();
		} while (g > 3);

		System.out.println("\nkoniec petli glownej");

		fnClusterView();
		fnIrisView();

		System.out.println("\nWarunek stopu osiagniety w " + licznik + " krokach");
	}

	/* wyswietlenie calej listy iris - nr obiektu i nr klastra */

	private void fnIrisView() {

		System.out.println(" Numery klastrow: ");

		for (int i = 0; i < r; i++) {
			System.out.println(i + " " + Iris.get(i).iCluster);
		}
	}

	/* wyswietlanie tablicy z klastramni */

	private void fnClusterView() {

		System.out.println(" Macierz odleglosci miedzy obiektami:: ");

		for (int i = 0; i < cClusters.size(); i++) {
			System.out.println(i + " indeks obiekt " + cClusters.get(i).fnViewOb());
		}
	}

	/*
	 * sprawdzanie odleglosci miedzy punktami - pierwiastek z sumy roznicy kwadratow
	 * wspolrzednych
	 * 
	 */

	private double fnCheckDistances(iris_kontener iris_kontener, iris_kontener iris_kontener2, int i, int j) {

		double dDist = Math.sqrt(fnDistances(iris_kontener.getSepal_length(), iris_kontener2.getSepal_length())
				+ fnDistances(iris_kontener.getSepal_width(), iris_kontener2.getSepal_width()));

		return dDist;
	}

	/*---- obliczenie dystansu miedzy dwoma tymi samymi wspolrzednymi ----*/

	public double fnDistances(double a, double b) {

		return Math.pow((a - b), 2);
	}

}
