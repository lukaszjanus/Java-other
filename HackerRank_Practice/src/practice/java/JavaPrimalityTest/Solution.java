package practice.java.JavaPrimalityTest;

import java.util.*;
import java.math.BigInteger;

/*
 * 'Java Primality Test'
 * 
 * difficulty level - easy
 * 
 * author:
 * Lukasz Janus
 * 
 * 11.2018
 * 
 */

public class Solution {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String n = scanner.nextLine();
		BigInteger wielkaliczba = new BigInteger(n);

		if (wielkaliczba.isProbablePrime(1))
			System.out.println("prime");
		else
			System.out.println("not prime");
		scanner.close();
	}
}
