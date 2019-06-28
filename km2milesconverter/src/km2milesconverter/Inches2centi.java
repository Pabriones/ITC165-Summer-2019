package km2milesconverter;

import java.util.Scanner;

public class Inches2centi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter Inches to Centimeter: ");
		int i = reader.nextInt();
		
		double centimeter;
		centimeter =(double)(i * (2.54));
		reader.close();
		System.out.println( i + " Inches to Centimeter is " + centimeter);
	}

}
