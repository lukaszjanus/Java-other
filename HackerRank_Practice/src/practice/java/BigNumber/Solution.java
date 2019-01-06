package practice.java.BigNumber;

import java.math.BigDecimal;
import java.util.*;

/*
 * 'Java BigDecimal'
 * 
 * difficulty level - medium
 * 
 * author:
 * Lukasz Janus
 * 
 * 11.2018
 * 
 */
class Solution {
	public static void main(String[] args) {
		// Input
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] s = new String[n + 2];
		for (int i = 0; i < n; i++) {
			s[i] = sc.next();
		}
		sc.close();

		/* start coding */

		BigDecimal num[] = new BigDecimal[n];
		int tech[] = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = new BigDecimal(s[i]);
			tech[i] = i;

		}

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (num[i].compareTo(num[j]) == -1) {
					BigDecimal temp = num[j];
					num[j] = num[i];
					num[i] = temp;

					int itemp = tech[j];
					tech[j] = tech[i];
					tech[i] = itemp;

				}
			}
		}
		String[] ss = new String[n];
		for (int i = 0; i < n; i++) {
			ss[i] = s[i];
			// System.out.println(tech[i]+" "+num[i]);
		}
		for (int i = 0; i < n; i++) {
			s[i] = ss[tech[i]];
			// s[i]=num[i].toString();
		}

		/* stop coding */

		// Output
		for (int i = 0; i < n; i++) {
			System.out.println(s[i]);
		}
	}
}