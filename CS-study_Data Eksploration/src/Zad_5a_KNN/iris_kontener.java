package Zad_5a_KNN;

/*
 * Klasa kontener do obslugi wczytanego obiektu z iris.csv
 * - pola odpowiadajace wszystkim kolumnom z pliku zrodlowego
 * - dodatkowe pole center do przechowywania informacji o n-rze klastra (domyslnie 0, potem nadpisywane)
 * - gettery i settery tylko dla pol oryginalnych z pliku
 * - pole o n-rze klastra uzupelnianie bezposrednio (dla przejrzystosci dzialania algorytmu)
 */

public class iris_kontener {

	private double sepal_length;
	private double sepal_width;
	private double petal_length;
	private double petal_width;
	private String species;
	public int center=0;
	
	/* konstruktor */

	public iris_kontener(double sl, double sw, double pl, double pw, String s) {
		sepal_length = sl;
		sepal_width = sw;
		petal_length = pl;
		petal_width = pw;
		species = s;
	}

	/* metoda do wyswietlania zawartosci pol */
	
	public String Eksport(String separator) {
		return getSepal_length() + separator + getSepal_width() + separator + getPetal_length() + separator
				+ getPetal_width() + separator + getSpecies();
	}
	
	/* gettery i settery */

	public double getSepal_length() {
		return sepal_length;
	}

	public void setSepal_length(double sepal_length) {
		this.sepal_length = sepal_length;
	}

	public double getSepal_width() {
		return sepal_width;
	}

	public void setSepal_width(double sepal_width) {
		this.sepal_width = sepal_width;
	}

	public double getPetal_length() {
		return petal_length;
	}

	public void setPetal_length(double petal_length) {
		this.petal_length = petal_length;
	}

	public double getPetal_width() {
		return petal_width;
	}

	public void setPetal_width(double petal_width) {
		this.petal_width = petal_width;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

}
