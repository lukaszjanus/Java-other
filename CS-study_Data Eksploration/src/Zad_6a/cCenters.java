package Zad_6a;

/*
 * 
 *  Klasa sluzaca do obslugi klastrow:
 *  - przechowuje wspolrzedne, informacje o klastrach
 *  
 *  
 *  */

public class cCenters implements Comparable<cCenters> {
	private double dX; // wspolrz x
	private double dY; // wspolrz y
	double dDist; // dystans
	private int iObiektA;
	private int iObiektB;
	int iCluster = -1;

	/* konstruktor do przechowywania wspolrzednych i nr klastra */

	public cCenters(double x, double y, int i) {
		dX = x;
		dY = y;
		iCluster = i;
	}

	/* -- konstruktor do informacji o nr-ach obiektów i nr-ze klastra -- */

	public cCenters(int a, int b, double dist) {
		iObiektA = a;
		iObiektB = b;
		dDist = dist;
	}

	/*
	 * -- metoda do przyjmowania sumy wspolrzednych (nie wykorzystywana -
	 * przygotowana na potrzeby wyliczania nowych wspolrzednych srodkow) --
	 */

	public void fnSuma(double x, double y) {
		dX += x;
		dY += y;
	}

	/* wyswietlanie - nry obiektow i odleglosc */

	public String fnViewOb() {

		return (iObiektA + " " + iObiektB + " " + dDist);
	}

	/* gettery, settery */

	public double getdX() {
		return dX;
	}

	public void setdX(double dX) {
		this.dX = dX;
	}

	public double getdY() {
		return dY;
	}

	public void setdY(double dY) {
		this.dY = dY;
	}

	public int getiObiektA() {
		return iObiektA;
	}

	public void setiObiektA(int iObiektA) {
		this.iObiektA = iObiektA;
	}

	public int getiObiektB() {
		return iObiektB;
	}

	public void setiObiektB(int iObiektB) {
		this.iObiektB = iObiektB;
	}

	/* metoda obslugujaca porownywanie obiektow - wg zmiennej dDistance */
	
	@Override
	public int compareTo(cCenters o) {

		if (this.dDist == o.dDist)
			return 0;
		else if (this.dDist > o.dDist)
			return 1;
		else
			return -1;

	}

}
