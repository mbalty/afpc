import java.util.Scanner;


class D {
	public static String unScramble(String scrambled){
		String[] parts = scrambled.split("#");
		int xVal = Integer.parseInt(parts[0]);
		
		StringBuilder msg = new StringBuilder(parts[1]);
		msg.append(parts[1]);
		return msg.substring(xVal, xVal+msg.length()/2);		
	}

	public static void main(String[] args){
		final long startTime = System.currentTimeMillis();
		Scanner scan = new Scanner(System.in).useDelimiter("\n");
		int num_tests = scan.nextInt();
		
		for(int i=0; i<num_tests; ++i)
		{
			String s = scan.next();
			System.out.println("Case #" + (i+1) +  ": " + unScramble(s));	
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Time is: " + (endTime-startTime));
	}
}