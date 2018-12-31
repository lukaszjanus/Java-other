package Zad_6a;

/*
 * 
 *  Klasa sluzaca do obslugi klastrow:
 *  - przechowuje wspolrzedne, informacje o klastrach
 *  
 *  
 *  */

public class cCenters {
	private double dX;
	private double dY;
	int iCluster = -1;

	/* konstruktor */
	
	public cCenters(double x, double y, int i) {
		dX = x;
		dY = y;
		iCluster = i;
	}

	/* -- konstruktor do obiektu tymczasowego (nie wykorzystywany - przygotowany na potrzeby wyliczania nowych wspolrzednych srodkow) -- */
	
	public cCenters() {
		dX = 0;
		dY = 0;
	}
	
	/* -- metoda do przyjmowania sumy wspolrzednych (nie wykorzystywana - przygotowana na potrzeby wyliczania nowych wspolrzednych srodkow) -- */

	public void fnSuma(double x, double y) {
		dX += x;
		dY += y;
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

}
