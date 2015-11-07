import java.io.*;
import java.util.*;


class TestA {

	public static void main(String[] args) throws IOException{
		PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
		Random rand = new Random();
		StringBuilder buf = new StringBuilder();
		writer.println(20);
		for(int j=0; j<20; ++j)
		{
			// the separation line
			int a = 10000;
			int b = 7000;
			int c = 3000;
			
			writer.println(a+" "+b+" "+c);

			//  the notable people, initialize UFNodes 
			UFNode[] notable_people = new UFNode[a];
			for(int i=0; i<a-1; ++i){
				writer.print(rand.nextInt(1000000)+" ");
			}
			writer.println();
						
			//  set relatives
			for(int i=0; i<b; ++i){
				writer.println((rand.nextInt(10000-1)+1) + " " + (rand.nextInt(10000-1)+1));
			}

			//  set married 
			for(int i=0; i<c; ++i){
				int p = 3*(i+1);
				writer.println(p + " " + (p+1));	
			}
			writer.println();
		}
		
		writer.close();
	}
}



