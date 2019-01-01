package Zad_6a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Aglomeracyjny {

	ArrayList<iris_kontener> Iris;
	ArrayList<cCenters> centers = new ArrayList(); // wspolerzedne srodkow klastrow
	ArrayList<cCenters> cClusters = new ArrayList<>(); // nr obiektow, odleglosc i nr klastra
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
			Iris.get(i).iCluster = i ; // nr klastra startowego dla obiektu iris
		}

		/*-------------*/

		double dTemp = 0; // zmienna robocza do przechowywania odleglosci srodkow
		double dDistanceTemp = Double.MAX_VALUE;

		for (int i = 0; i < Iris.size() - 1; i++) {
			for (int j = i + 1; j < Iris.size() - 1; j++) {
				// znajduje minimum odleglosci i zapisuje do tablicy obiektow - 'kazdy z kazdym'

				dTemp = fnCheckDistances(Iris.get(i), Iris.get(j), i, j);
				cCenters c = new cCenters(i, j, dTemp);
				cClusters.add(c);

				if (dTemp < dDistanceTemp) {
					dDistanceTemp = dTemp; // dDistanceTemp - zmienna koncowa - wynik przeszukania odleglosci miedzy
											// aktualnymi srodkami
				}
			}
		}

		/* sortowanie obiektow wg odleglosci */
		Collections.sort(cClusters);
		int iClusterTemp;
		/* petla glowna algorytmu */
		do {

			int a=cClusters.get(0).getiObiektA();
			int b=cClusters.get(0).getiObiektB();
			
			/* tu do poprawy - gdy a==b dalsza czesc instrukcji powinna byc przerwana */
			
			//System.out.println(a+ " klaster: "+ Iris.get(a).iCluster+", "+ b + " klaster: "+ Iris.get(b).iCluster);
			//System.out.println("Odleglosc "+ cClusters.get(0).dDist+", ");
			
			
			int tempClust=Iris.get(b).iCluster; //zmienna robocza sprzed zmiany nr-u klastra - potrzebna do petli scalajacej caly klaster
			Iris.get(b).iCluster=Iris.get(a).iCluster;
			
			/* uaktualnienie 'macierzy przyleglosci' - czyli scalenie klastra */
			
			for(int i=0;i<Iris.size();i++) {
				if(Iris.get(i).iCluster == tempClust) {
					Iris.get(i).iCluster=Iris.get(a).iCluster;
				}
			}
			
			cClusters.remove(0);
			System.out.println("Odleglosc "+ cClusters.size());
			//i++;
			licznik++;
			 //Scanner scan = new Scanner(System.in);
			 //scan.next();
		} while (cClusters.size() > 3);

		// System.out.println("\nkoniec petli");
		// System.out.println(cClusters.size());
		//System.out.println(dDistanceTemp);

		for (int i = 0; i < cClusters.size(); i++) {
			 System.out.println(i + " indeks obiekt " + cClusters.get(i).fnViewOb());
		}
		for (int i = 0; i < Iris.size(); i++) {
			//System.out.println(i + " " + Iris.get(i).iCluster);
		}
		System.out.println("\nWarunek stopu osiagniety w " + licznik + " krokach");
	}

	/*
	 * sprawdzanie odleglosci miedzy punktami - pierwiastek z sumy roznicy kwadratow
	 * wspolrzednych
	 * 
	 */

	private double fnCheckDistances(iris_kontener iris_kontener, iris_kontener iris_kontener2, int i, int j) {

		dDist = Math.sqrt(fnDistances(iris_kontener.getSepal_length(), iris_kontener2.getSepal_length())
				+ fnDistances(iris_kontener.getSepal_width(), iris_kontener2.getSepal_width()));

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
