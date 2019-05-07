package tdeuctests;

import java.util.Random;

public class Rough {

	public static void main(String[] args) {
		int rangeMin=1;
		int rangeMax=100;
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextFloat();
		   double roundOff = Math.round(randomValue*100.0)/100.0;
		   System.out.println(roundOff);
		   
		String number=Double.toString(roundOff);
		int decind=number.indexOf(".");
		String subnum=number.substring(decind+1, number.length());
		
		System.out.println("Number after decimal-"+subnum);
		if(subnum.length()==1){
			number=number+"0";
			System.out.println("Number till two decimal-"+number);
		}
		System.out.println("Number till two decimal-"+number);

	}

}
