package practice.java.JavaBigInteger;

/*
 * 'Java Big Integer'
 * 
 * difficulty level - easy
 * 
 * author:
 * Lukasz Janus
 * 
 * 11.2018
 * 
 */

import java.util.*;
import java.math.BigInteger;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        String n1=scanner.nextLine();
        String n2= scanner.nextLine();
    
        BigInteger N1=new BigInteger(n1);
    BigInteger N2=new BigInteger(n2);
        System.out.println(N1.add(N2));
        System.out.println(N1.multiply(N2));
        
    }
}