package km2milesconverter;

import java.math.*;
import java.util.Scanner;

public class miles2km {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter Miles to Kilometer: ");
		int m = reader.nextInt();
		
		double kilometer;
		kilometer =(double)(m * (1.60934));
		reader.close();
		System.out.println( m + " Miles to kilometer is " + kilometer);
	}

}
