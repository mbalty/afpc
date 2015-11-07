import java.io.*;
import java.util.*;

class Tamas{
	public static void main(String[] args){
		double lmax = 0; 
		int p = 50;
		int r = 10;
		double coef = 1.+r/100.0;
		int y = 2;
		int months = 12*y;
		for(int i=0; i<	months; i++){
			lmax = (int)((lmax+p)/coef)+0.99;
			System.out.println(lmax);
		}
		System.out.println(lmax);
	}
}