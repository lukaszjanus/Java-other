package charactersInWindow;

//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Point;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * 2018.08.12
 * £ukasz Janus
 * 
 * Tutorial from 'KomputerSwiat' (refresh Java programming) - display pressed key in window
 * 
 */

public class CharactersInWindow {

	public static void main(String[] args) {
		Okno okno = new Okno();

	}
}

class Okno extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Okno() {
		setTitle("Rysunek");
		setSize(800, 150);
		Panel panel = new Panel();

		getContentPane().add(panel);
		setDefaultCloseOperation(3);
		setVisible(true);
	}
}

class Panel extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6897313272460408945L;
	String tekst = "";

	public Panel() {
		setFocusable(true);
		addKeyListener(this);
	}

	public void paintComponent(Graphics g) {

//			g.clearRect(45,50,300,350);
		g.clearRect(0, 0, 800, 150);

		Font czcionka = new Font("Monospaced", Font.BOLD, 30);
		g.setFont(czcionka);
		g.setColor(Color.red);
		g.drawString(tekst, 10, 50);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int kod = e.getKeyCode();
		char znak = e.getKeyChar();
		if (kod == KeyEvent.VK_BACK_SPACE) {
			tekst = tekst.substring(0, tekst.length() - 1);
		} else
			tekst = tekst + znak;
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
