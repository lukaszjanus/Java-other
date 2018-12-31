package Zad_5a_KNN;

import java.util.ArrayList;
import java.util.Scanner;

public class centroidy {

	ArrayList<iris_kontener> Iris;
	double[][] centers; // wspolerzedne srodkow klastrow
	double[][] tempCenters; // tymczasowa tablica do wyliczania nowych wspolrzednych srodkow
	double[] dist; // dlugosci - tabela robocza
	static int n1; // dlugosc tablicy ze srodkami - czyli ilosc centroidow
	double srednia; // zmienna do porownywania odleglosci, czy osiagnela wartosc stopu

	static int licznik = 0; //ilosc krokow

	public centroidy(double[][] n, ArrayList<iris_kontener> IrisLista) {
		centers = n;
		Iris = IrisLista;
		n1 = centers.length;
		dist = new double[n1];
		tempCenters = new double[n1][2];
	}

	/*---- petla glowna algorytmu ----*/

	public void checkDistances() {

		// System.out.println(Iris.get(0).getSepal_length());
		int c; // nr centroidu;
		boolean flag = true;

		int centCount[] = new int[3];

		do {
			for (int i = 0; i < Iris.size(); i++) {

				// obliczam odleglosci dla wszystkich srodkow i wyliczam srednia wszystkich
				// wspolrzednych badanych obiektow:
				c = 0;
				dist[c] = checkData(Iris.get(i).getSepal_length(), Iris.get(i).getSepal_width(), c);
				c = 1;
				dist[c] = checkData(Iris.get(i).getSepal_length(), Iris.get(i).getSepal_width(), c);
				c = 2;
				dist[c] = checkData(Iris.get(i).getSepal_length(), Iris.get(i).getSepal_width(), c);

		//		 System.out.println(dist[0] + "\t\t" + dist[1] + "\t\t" + dist[2]); //roboczo - wyswietlenie odleglosci

				// wybieram najmniejsza odleglasc i przypisuje nr srodka klastra do obiektu; ktory_klastare ma wartosc MinDistance
				int ktory_klaster = Iris.get(i).center = MinDistance(dist);

				// zliczam, ile jest wystapien kazdego klastra, sztywno ustawione na '3'
				if (ktory_klaster == 0)
					centCount[0]++;
				if (ktory_klaster == 1)
					centCount[1]++;
				if (ktory_klaster == 2)
					centCount[2]++;

				// sumuje wspolrzedne danego klastra

				SumaWspolrzednych(Iris.get(i).getSepal_length(), Iris.get(i).getSepal_width(), ktory_klaster);

				// System.out.println(" Flag: "+flag);
			}
			/*	aktualizacja polozen centroidow: */
			System.out.println("\nLiczba elelemntow w danym klastrze:\n1: " + centCount[0] + "\n2: " + centCount[1]
					+ "\n3: " + centCount[2]);
			/* wyliczam srednia wspolrzednych i aktualizuje wspolrzedne srodkow; */
			flag = newCenters(n1, flag, centCount);

			/* zeruje licznik klastrow */
			for (int i = 0; i < centCount.length; i++) {
				centCount[i] = 0;
			}

			licznik++;
			// reczne zatrzymanie petli - do testu:
			// Scanner scan = new Scanner(System.in);
			// scan.next();

		} while (flag == true);

		System.out.println("\nWarunek stopu osiagniety w " + licznik + " krokach");
	}

	/*
	 * obliczenie nowych wspolrzednych srodkow, sprawdzenie warunku stopu - wartosc
	 * przesuniecia mniejsza niz wartosc zmiennej 'error'
	 * 
	 */

	private boolean newCenters(int g, boolean flag, int[] centCount) {

		double error = 0.01; // wartosc stop

		double[] srednia = new double[n1]; // przechowanie wartosci przesuniecia srodkow po zmianie wspolrzednych

		for (int i = 0; i < n1; i++) {
			// zmienne robocze do obliczenia odleglosci, o jaka sie przesunal srodek
			double aC = centers[i][0]; // klaster nr i, - zmienna robocza na wspolrzedna x
			double bC = centers[i][1]; // klaster nr i, - zmienna robocza na wspolrzedna y

			// warunek zabezpieczajacy przed dzieleniem przez 0:
			if (centCount[i] == 0) // przerwij zmiane wspolrzednych, gdy ilosc punktow przypisana do danego
									// centroida == 0; w tej sytuacji wspolrzedne startowe zostaja nadpisane
			{
				break;
			}

			centers[i][0] = tempCenters[i][0] / centCount[i]; // srednia sepal length:

			centers[i][1] = tempCenters[i][1] / centCount[i]; // serdnia sepal width:

			srednia[i] = Math.sqrt(distance(centers[i][0], aC) + distance(centers[i][1], bC));
		}

		for (int i = 0; i < n1; i++) {
			if (srednia[i] < error)
				flag = false;
			// System.out.println(" odleglosc strego nowego srodka: "+srednia[i]+ " "+flag);
		}

//		System.out.println("G: " + g);
		ViewSrodki(centers);

		zerujTempCenters(); // zerowanie tablicy tymczasowej

		return flag;
	}

	/* wyzerowanie tablicy tymczasowych wspolrzednych */

	private void zerujTempCenters() {
		for (int i = 0; i < n1; i++) {
			tempCenters[i][0] = tempCenters[i][1] = 0;
		}
	}

	/* wyliczanie sumy wspolrzednych - do aktualizacji polozenia srodkow */

	private void SumaWspolrzednych(double sepal_length, double sepal_width, int c) {

		tempCenters[c][0] += sepal_length;
		tempCenters[c][1] += sepal_width;
		// System.out.println(c + " srednie: " + tempCenters[c][0] + " " +
		// tempCenters[c][1]); // // roboczo
	}

	/*
	 * wyszukanie i przypisanie (jako return) do klastra z najmniejsza odlegloscia
	 */

	private int MinDistance(double[] tab) {

		if (tab[0] > tab[1]) {
			if (tab[1] > tab[2]) {
				return 2;
			} else {
				return 1;
			}

		} else {
			if (tab[0] < tab[2]) {
				return 0;
			} else {
				return 2;
			}
		}
		// return -1;
	}

	/*---- liczenie odleglosci miedzy dwoma tymi samymi wspolrzednymi ----*/

	public double distance(double a, double b) {

		return Math.pow((a - b), 2);
	}

	/*---- liczenie dystansu miedzy wspolrzednymi - srodek i wybrane wspolzedne ----*/

	public double checkData(double length, double width, int c) {

		for (int i = 0; i < n1; i++) {
			// odleglosc miedzy punktami
			srednia = Math.sqrt(distance(centers[c][0], length) + distance(centers[c][1], width));
		}
		return srednia;
	}

	/* wyswietlenie œrodków - wartosci aktualne srodkow */

	private static void ViewSrodki(double[][] n) {
		System.out.println("\nWspolrzedne aktualnych srodkow:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print((i + 1) + ". " + n[i][j] + " ");
			}
			System.out.println();
		}
	}
}
