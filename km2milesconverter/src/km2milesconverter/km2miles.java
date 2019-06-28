package km2milesconverter;

import java.math.*;
import java.util.Scanner;

public class km2miles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter Kilometer to Miles: ");
		double n = reader.nextInt();
		
		double Miles;
		Miles =(double)(n * (0.6214));
		reader.close();
		System.out.println( n + " kilometers to miles is " + Miles);
			
	}

}
