
import java.awt.EventQueue;

/**
 * 
 * Simple calculator in FRame for friend, who started learn Java.
 * There is one mistake to find by him - he must find and change
 * condition to enter digits with decimal separator.
 * On this version decimal separator is recognized as letter.
 * 
 * @author £ukasz
 * 02.06.2019
 * 
 */

public class Kalkulator  {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MyFrame();
			}
		});
	}
}