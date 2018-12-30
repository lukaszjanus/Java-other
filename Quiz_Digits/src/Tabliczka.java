import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

/**
 * @author £ukasz 2018.08.12 Aplet (refreshing Java Language - course from
 *         'Komputer Swiat') - simply quiz with digits
 * 
 */
public class Tabliczka extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField pole = null;
	JLabel liczba1, liczba2;
	JPanel panel2;
	Border krawedz;

	public Tabliczka() {
		liczba1 = new JLabel();
		liczba2 = new JLabel();
		krawedz = BorderFactory.createLineBorder(Color.black);
	}

	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					utworz();
				}
			});
		} catch (Exception e) {
		}
	}

	/**************************/

	public void utworz() {
		setSize(300, 105);
		getContentPane().setBackground(new Color(204, 204, 255));
		getContentPane().setLayout(new GridLayout(3, 1));

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 255, 153));
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel1.add(new JLabel("Tabliczka mnozenia"));
		panel1.setBorder(krawedz);
		getContentPane().add(panel1);

		panel2 = new JPanel();
		panel2.setBackground(new Color(251, 229, 246));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

		Random rand = new Random();
		int r1 = 1 + rand.nextInt(10);
		int r2 = 1 + rand.nextInt(10);

		liczba1 = new JLabel();
		liczba2 = new JLabel();
		liczba1.setText("" + r1);
		liczba2.setText("" + r2);
		panel2.add(liczba1);
		panel2.add(new JLabel(" * "));
		panel2.add(liczba2);
		panel2.add(new JLabel(" = "));

		pole = new JTextField(4);
		panel2.add(pole);
		panel2.setBorder(krawedz);
		getContentPane().add(panel2);

		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 255, 153));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

		JButton reset = new JButton("Wyczysc");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pole.setText("");
				pole.setBackground(Color.white);
			}
		});
		panel3.add(reset);
		JButton losuj = new JButton("wylosuj");
		losuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				int r1 = 1 + rand.nextInt(10);
				int r2 = 1 + rand.nextInt(10);
				liczba1.setText("" + r1);
				liczba2.setText("" + r2);

				pole.setText("");
				pole.setBackground(Color.WHITE);
				panel2.revalidate();
			}
		});
		panel3.add(losuj);
		JButton sprawdz = new JButton("sprawdz");
		sprawdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pole.getText() != null && !pole.getText().equals("")) {
					int num1 = Integer.parseInt(liczba1.getText());
					int num2 = Integer.parseInt(liczba2.getText());
					int wynik = num1 * num2;
					int wynikU = Integer.parseInt(pole.getText());
					if (wynikU == wynik) {
						pole.setBackground(new Color(204, 255, 153));
					} else if (wynikU != wynik) {
						pole.setBackground(Color.RED);
					}

				}
			}
		});
		panel3.add(sprawdz);
		panel3.setBorder(krawedz);
		getContentPane().add(panel3);
	}

}
