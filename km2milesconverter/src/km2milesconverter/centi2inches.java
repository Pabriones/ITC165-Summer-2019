package km2milesconverter;

import java.util.Scanner;

public class centi2inches {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter Inches to Centimeter: ");
		int c = reader.nextInt();
		
		double inches;
		inches =(double)(c * (0.3937));
		reader.close();
		System.out.println( c + " Centimeter to Inches is " + inches);
	}

}
