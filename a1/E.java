import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;



class E {
	//	recursive approach to compute expression:
	// 		it iterates from end and recurses into the expression operation by operation
	public static int computeExpression(String expr) {
		// 	base case
		int ln = expr.length(), i = ln - 1;
		if (ln == 0) return 0;

		// parse last integer
		while(i >= 0){
			char c = expr.charAt(i);
			if(c<'0' || c>'9') break;
			i--;
		}
		//  if end is reached after parsing (another base case)
		if(i < 0) return Integer.parseInt(expr);

		//	start position of the value
		int valStart = i+1;
		//	parse value into int
		int value = Integer.parseInt(expr.substring(valStart, ln));

		//	parse the last operation
		while(i >= 0){
			char c = expr.charAt(i);
			if(c>='0' && c<='9') break;
			i--;
		}
		//	start position of the operation
		int opStart = i+1;
		//	extract operation
		String operation = expr.substring(opStart, valStart);

		// recurse towards left into the string
		int prevValue = computeExpression(expr.substring(0, opStart));

		// verify operation
		switch (operation) {
        	case "plus":
        		return prevValue + value;
        	case "minus":
        		return prevValue - value;
        	case "times":
        		return prevValue * value;
        	case "tothepowerof":
        	 	return (int)Math.pow(prevValue, value);
        	default:
        		throw new Error("Error: input is not correct!");
        } 
	}


	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		int num_tests = Integer.parseInt(in.readLine());
		BufferedWriter out = new BufferedWriter (new OutputStreamWriter(System.out));

		if (num_tests < 1 || num_tests > 20)
		{
			throw new Error("Error: Number of tests out of bounds!");
		}	

		long result;
		StringBuilder b = new StringBuilder();
		for(int i=0; i<num_tests; ++i)
		{
			result = computeExpression(in.readLine());
			b.append("Case #");
			b.append(i+1);
			b.append(": ");
			b.append(result);
			System.out.println(b);	
			b.delete(0, b.length());
		}
	}
}