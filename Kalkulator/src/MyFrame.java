
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;



/**
 * Class with window (buttons, fields, labels) and logics.
 * @author £ukasz
 *
 */
public class MyFrame extends JFrame implements ActionListener {

	// krawedzie miedzy oknami
	Border krawedz;

	JPanel panel0;
	JPanel panel1;
	// kontener na liczby
	JPanel panel2;
	// kontener na klawisze z cyframi
	JPanel panel3;
	// kontener na klawisze z operacjami
	JPanel panel4;
	JPanel panel5;

	JLabel calc;
	// etykieta tekstowa gorna
	JLabel l1, err, a, b, dzial, wyn;
	// pola na wprowadzanie liczb
	JTextField t1, t2, t3;
	// klawisze funkcyjne
	JButton dodawanie, odejmowanie, mnozenie, dzielenie, czyszczenie;

	public MyFrame() {
		//wywolanie konstruktora z parametrem:
		//- ustawienie tytulu paska startowego
		super("Program zaliczeniowy");

		krawedz = BorderFactory.createLineBorder(Color.black);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// lokalizacja na ekranie - odleglosc w px od lewej/gornej krawedzi
		setLocation(50, 50);
		// ustawienie rozmiaru startowego okna programu:
		setSize(500, 300);
		// tlo okna Red-Green-Blue
		getContentPane().setBackground(new Color(204, 204, 255));
		// uklad elementow - siatka, 5 wierszy, 1 kolumna
		setLayout(new GridLayout(5, 1));
		// czy maja byc widoczne elementy w oknie - tak (true), nie (false - wtedy
		// program sie nie uruchomi)
		setVisible(true);

		/* napis nazwy programu - wiersz pierwszy */

		/*
		 * tworze panel, czyli tak¹ szufladkê (kontener) w pierwszym wierszu,
		 * ktory bedzie wypelniony tylko etykiet¹ z nazw¹ programus
		 */
		panel0 = new JPanel();
		// ustawiam tlo
		panel0.setBackground(new Color(255, 255, 153));
		// wysrodkowuje
		panel0.setLayout(new FlowLayout(FlowLayout.CENTER));
		// dodaje do etykiety moja nazwe programu
		calc = new JLabel("KALKULATOR");
		// dodaje etykiete do panelu
		panel0.add(calc);
		// a panel dodaje do ContentPane - automatycznie jest to pierwszy wiersz
		getContentPane().add(panel0);

		/* dalej analogicznie - drugi wiersz */

		panel1 = new JPanel();
		panel1.setBackground(new Color(255, 255, 153));
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

		// tworze etykiete do opisu pol
		l1 = new JLabel("Wprowadz liczby a i b:");
		// i dodaje do okienka glownego programu metoda 'add'
		panel1.add(l1);
		getContentPane().add(panel1);

		/* trzeci wiersz - miejsca na wpisanie liczb */

		panel2 = new JPanel();
		panel2.setBackground(new Color(251, 229, 246));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

		// zmienne na liczby; liczba w TextField jest oznaczeniem szerokosci okienka
		a = new JLabel("a:");
		panel2.add(a);
		t1 = new JTextField(10);
		t1.setText("0");
		panel2.add(t1);

		// etykieta na rodziaj wykonanej operacji
		dzial = new JLabel("    ");
		panel2.add(dzial);

		b = new JLabel("b:");
		panel2.add(b);
		t2 = new JTextField(10);
		t2.setText("0");
		panel2.add(t2);

		wyn = new JLabel(" = ");
		panel2.add(wyn);
		// zmienna na wynik
		t3 = new JTextField(10);
		panel2.add(t3);
		getContentPane().add(panel2);

		/* czwarty wiersz - dzia³ania na liczbach - klawisze */

		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 255, 153));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

		dodawanie = new JButton("+");
		panel3.add(dodawanie);
		// klikniecie w guzik dodawania:
		dodawanie.addActionListener(this);

		odejmowanie = new JButton("-");
		panel3.add(odejmowanie);
		odejmowanie.addActionListener(this);

		mnozenie = new JButton("*");
		panel3.add(mnozenie);
		mnozenie.addActionListener(this);

		dzielenie = new JButton("/");
		panel3.add(dzielenie);
		dzielenie.addActionListener(this);

		czyszczenie = new JButton("C");
		panel3.add(czyszczenie);
		czyszczenie.addActionListener(this);

		getContentPane().add(panel3);

		/*
		 * pi¹ty wiersz - informacje, co aktualnie siê zadzia³o w programie tutaj te¿
		 * informacja o wprowadzeniu liter, czy dzieleniu przez '0'
		 */

		panel4 = new JPanel();
		panel4.setBackground(new Color(251, 229, 246));
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER));

		// etykieta na informaje o bledach, na pocz¹tku jest to wartoœæ startowa
		err = new JLabel("Wybierz dzia³anie.");
		err.setLocation(200, 200);
		err.setSize(20, 50);
		panel4.add(err);
		getContentPane().add(panel4);
	}

	/**
	 * implements ActionListener -> dodaje obsluge klawiszy. Obsluga klawiszy odbywa
	 * sie przez ponizsza obowiazkowa metode
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {

		// pobieram zawartoœæ pól t1 i t2 i sprawdza, ich zawartosc
		String temp1 = t1.getText();
		String temp2 = t2.getText();

		/* zabezpieczenie przed pustymi polami */
		if (temp1.length() == 0 || temp2.length() == 0) {
			err.setText("Nie s¹ wprowadzone wszystkie liczby!");
			return;
		}

		/* zabezpieczenie przed literami */

		for (int i = 0; i < temp1.length(); i++) {
			// sprawdzenie wg ASCI - do ka¿dego znaku jest dopisana liczba
			// poprzez porównanie liczb sprawdzam litery; wg ASCII Cyfry s¹ na pozycjch od
			// 48 do 57
			
			/*
			 * Dzia³anie: po sprawdzeniu ca³ego wyrazu - jeœli bêdzie znaleziona litera, to:
			 * - wyswietl komunikat "wprowadzono litery"
			 * - pole z liter¹ wyczyœæ
			 */
			
			if (temp1.charAt(i) < 48 || (int) temp1.charAt(i) > 57) {
				err.setText("wprowadzono litery");
				t1.setText("");
				// przerywam petle, gdyz przy znalezeniu litery dalej nie ma potrzeby sprawdzac
				break;
			}
		}
		
		for (int i = 0; i < temp2.length(); i++) {
			if (temp2.charAt(i) < 48 || (int) temp2.charAt(i) > 57) {
				err.setText("wprowadzono litery");
				t2.setText("");
				break;
			}
		}

		/* dzialania */

		if (ae.getSource() == dodawanie) {
			{
				double suma = Double.parseDouble(t1.getText()) + Double.parseDouble(t2.getText());
				t3.setText(String.valueOf(suma));
				err.setText("dodawanie");
				dzial.setText("  +  ");
			}
		}

		if (ae.getSource() == odejmowanie) {
			double minus = Double.parseDouble(t1.getText()) - Double.parseDouble(t2.getText());
			t3.setText(String.valueOf(minus));
			err.setText("odejmowanie");
			dzial.setText("  -  ");
		}

		if (ae.getSource() == mnozenie) {
			double mnoz = Double.parseDouble(t1.getText()) * Double.parseDouble(t2.getText());
			t3.setText(String.valueOf(mnoz));
			err.setText("mno¿enie");
			dzial.setText("  *  ");
		}

		if (ae.getSource() == dzielenie) {
			// zabezpieczenie przed dzieleniem przez '0'
			if (Double.parseDouble(t2.getText()) == 0) {
				t2.setText("");

				err.setText("nie dziel przez '0', podaj inn¹ liczbê");
			} else {
				// jesli liczby s¹ ró¿ne do "0", mo¿na dzieliæ
				double dziel = Double.parseDouble(t1.getText()) / Double.parseDouble(t2.getText());
				t3.setText(String.valueOf(dziel));
				err.setText("dzielenie");
				dzial.setText("  /  ");
			}
		}

		// wyczyszczenie wszystkich pol
		if (ae.getSource() == czyszczenie) {
			// ustawiam wartoœci w polach liczb na '0'
			t1.setText("0");
			t2.setText("0");
			t3.setText("0");
			err.setText("czyszczenie");
		}
	}

}