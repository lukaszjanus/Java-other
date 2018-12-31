package Zad_6a;

import java.util.ArrayList;
import java.util.Scanner;

public class Aglomeracyjny {

	ArrayList<iris_kontener> Iris;
	ArrayList<cCenters> centers = new ArrayList(); // wspolerzedne srodkow klastrow
	ArrayList tempCenters = new ArrayList(); // tymczasowa tablica do wyliczania nowych wspolrzednych srodkow
	ArrayList distances = new ArrayList(); // dlugosci - tabela robocza

	double dMinDist = 0;
	double dDist = 0;

	static int licznik = 0;

	/*-- konstruktor --*/

	public Aglomeracyjny(ArrayList<iris_kontener> IrisLista) {
		Iris = IrisLista; // lista obiektow z wczytanymi danymi (wszystkimi)
	}

	/*---- start algorytmu - glowne obliczenia, petla glowna ----*/

	public void fnStart() {

		boolean flag = true;

		// startowe przypisanie srodkow - kazdy obiekt jest klastrem startowym,
		// 'i+1' aby ilosc klastrow nie byla liczona od '0', tylko od '1'

		for (int i = 0; i < Iris.size(); i++) {
			cCenters c = new cCenters(Iris.get(i).getSepal_length(), Iris.get(i).getSepal_width(), i + 1);
			// czyli wspolrzedne obiektu + nr indeksu jako nr klastra
			centers.add(c);
			Iris.get(i).iCluster = i + 1; // analogicznie nr klastra startowego dla obiektu iris
		}

		/*-------------*/

		double dTemp; // zmienna robocza do przechowywania odleglosci srodkow

		/* petla glowna algorytmu */

		do {
			double dDistanceTemp = Double.MAX_VALUE;
			dTemp = 0; // zerowanie dla ponownego liczenia odleglosci

			for (int i = 0; i < centers.size() - 1; i++) {
				for (int j = i + 1; j < centers.size() - 1; j++) {
					// znajduje minimum odleglosci i zapisuje do zmiennej dTemp

					dTemp = fnCheckDistances(centers.get(i), centers.get(j), i, j);
					if (dTemp < dDistanceTemp) {
						dDistanceTemp = dTemp; // dDistanceTemp - zmienna koncowa - wynik przeszukania odleglosci miedzy
												// aktualnymi srodkami
					}
				}
			}

			// scalam pierwsza najmniejsza wartosc - zapisana w iDistanceTemp.

			int iZamKlaster;
			for (int i = 0; i < centers.size(); i++) {
				for (int j = i + 1; j < centers.size() - 1; j++) {

					dTemp = fnCheckDistances(centers.get(i), centers.get(j), i, j); // zmienna tymczasowa w danej petli
																					// - druga iteracja

					// if(dTemp<0.1)System.out.println(i+" "+ j +" Dystans: " + dTemp + " Minimalny
					// " + dDistanceTemp); //testowo

					if (dTemp == dDistanceTemp) { // gdy odleglosc sposrod wyszukanych jest najmniejsza,
													// nadpisz klaster w obiekcie/liscie; usun nr kklastra z listy
						iZamKlaster = centers.get(j).iCluster; // wyswietlam znaleziony klaster, przypisuje do zmiennej
						System.out.println(" usuwany: " + iZamKlaster + " distance " + dTemp + " zamiana na "
								+ centers.get(i).iCluster);

						/* --- rozpoczynam zliczanie wspolrzednych do aktualizacji srodka --- */

						cCenters c;

						// po usunieciu srodka do obiekt daje nowy nr klastra
						for (int d = 0; d < Iris.size(); d++) { // przeszukuje obiekty iris w celu nadpisania klastra
							if (iZamKlaster == Iris.get(d).iCluster) {
								
								/*  strumieniowania wyswietlaja przebieg algorytmu - nadpisywanie i usuwanie poszczegolnych klastrow
								/*
								 * System.out.println(" Przed usunieciem - klaster iris " + Iris.get(d).iCluster + " roboczy klaster " + iZamKlaster
								 * + " ilosc klastrow " + centers.size());
								 * 
								 */
								Iris.get(d).iCluster = centers.get(i).iCluster; // czyli do iris przypisuje numer z
																				// pierwszego z dwóch porównanych
																				// centers

								/*
								 * System.out .println(" Klaster iris nadpisany " + Iris.get(d).iCluster);
								 */
							}
						}

						centers.remove(j); // usuniecie zbednego nru klastra (po aktualizacji obiektow iris

						/*
						 * czy trzeba zaktualizowac wspolrzedne srodkow - zliczac (w petli int d
						 * (powyzej) wspolrzedne i nadpisac dla danego klastra wspolrzedne nowego
						 * srodka? czy tylko porownoje wspolrzedne ?
						 * 
						 */

						if (centers.size() < 4)
							break; // warunek stopu
					}
				}
			}
			System.out.println(centers.size()); // ilosc srodkow

		} while (centers.size() > 3);

		// System.out.println("\nkoniec petli");
		// System.out.println(centers.size());

		for (int i = 0; i < Iris.size(); i++) {
			System.out.println(i + " " + Iris.get(i).iCluster);
		}
		for (int i = 0; i < centers.size(); i++) {
			System.out.println(i + " " + centers.get(i).iCluster);
		}
		System.out.println("\nWarunek stopu osiagniety w " + licznik + " krokach");
	}

	/*
	 * sprawdzanie odleglosci miedzy punktami - pierwiastek z sumy roznicy kwadratow
	 * wspolrzednych
	 * 
	 */

	private double fnCheckDistances(cCenters cCenters, cCenters cCenters2, int i, int j) {

		dDist = Math.sqrt(
				fnDistances(cCenters.getdX(), cCenters2.getdX()) + fnDistances(cCenters.getdY(), cCenters2.getdY()));

//roboczo - wyswietlanie iteracji, odleglosci i wspolrzednych
//		if(dDist<0.1)System.out.println(i+" "+ j +" Dystans: " +iris1.getSepal_length()+ " "+iris2.getSepal_length()+ " ");
//		if(dDist<0.1)System.out.println(i+" "+ j +" Dystans: " +iris1.getSepal_width()+ " "+iris2.getSepal_width()+ " ");
//		if(dDist<0.1)System.out.println(Math.sqrt(fnDistances(iris1.getSepal_length(), iris2.getSepal_length())));

		return dDist;
	}

	/*---- obliczenie dystansu miedzy dwoma tymi samymi wspolrzednymi ----*/

	public double fnDistances(double a, double b) {

		return Math.pow((a - b), 2);
	}

	/* wyliczanie sumy wspolrzednych - tu nie uzyta */

	private void fnSumaWspolrzednych(double sepal_length, double sepal_width, int c) {

		centers.get(c).setdX(sepal_length);
		centers.get(c).setdX(sepal_width);

		// System.out.println(c + " srednie: " + tempCenters[c][0] + " " +
		// tempCenters[c][1]); // // roboczo

	}

}
