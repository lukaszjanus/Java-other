import java.awt.*;
import javax.swing.*;
import java.util.*;

/*
 * 
 * Simple clock - course with 'Komputer Swiat' (refreshing knowledge about java):
 * - with threads(implements Runnable - static constructor from class Panel go to method,
 * 	 which is supported by threads - public void run(), next i run watek.start();
 * - window - JFrame, interface to window in JPanel (class Panel inherits from it) - swing i awt
 * - run() method - here variables of date and time are constantly updated
 * - datas about actual time/date realized by class GregorianCalendar (java.util.*)
 * 
 * Lukasz Janus
 * 
 * 2018.08.12
 * 
 */


public class Zegar {

	public static void main(String[] args) {

		JFrame okno = new JFrame("Zegar");
		okno.setSize(400,150);
		Panel panel = new Panel();
		okno.getContentPane().add(panel);
		okno.setDefaultCloseOperation(3);
		okno.setVisible(true);

	}

}

class Panel extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rok, mc, dzien, godz, min, sek;
	private String smc, sdzien, sgodz, smin, ssek;
	
	Thread watek=null;
	
	public Panel() {		
		watek = new Thread(this);
		watek.start();		
	}
	
	public void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, 400,150);  //refreshing window before display next 'sekundy', 
		//without this method digits are place up, without clean older digits 
		Font czcionka = new Font("Monospaced", Font.BOLD,30);
		g.setFont(czcionka);
		if(mc<9) smc="0"; else smc="";
		if(dzien<10) sdzien="0"; else sdzien="";
		g.drawString(rok+"."+smc+(mc+1)+"."+sdzien+dzien, 70, 40);
		if(godz<10) sgodz="0"; else sgodz="";
		if(min<10) smin="0"; else smin="";
		if(sek<10) ssek="0"; else ssek="";
		g.drawString(sgodz+godz+":"+smin+min+":"+ssek+sek, 70, 80);
	}

	@Override
	public void run() {
		while(true)
		{
			GregorianCalendar czas=new GregorianCalendar();
						
			rok=czas.get(Calendar.YEAR);
			mc=czas.get(Calendar.MONTH);
			dzien=czas.get(Calendar.DAY_OF_MONTH);
			godz=czas.get(Calendar.HOUR_OF_DAY);
			min=czas.get(Calendar.MINUTE);
			sek=czas.get(Calendar.SECOND);
			
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e)
			{
				System.out.println("Wystapil blad");
			}
			repaint(); //start method paintComponent -> change data in window
		}
	}
}