import java.io.*;
import java.util.*;


class B {
	
	public static boolean putPosts(int d, int u, int v, int p, double dist) {
		double last = 0;
		int post = 1;
		for(int i = 1; i < p && last <= d; ++i) {
			last+=dist;
			if (last > u && last < v)
				last = v;
		}
		if(last > d)
			return false;
		return true;
	}


	public static void main(String[] args) throws IOException{
		// BufferedReader in = new BufferedReader (new FileReader("testD.txt"));
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		int num_tests = Integer.parseInt(in.readLine());
		BufferedWriter out = new BufferedWriter (new OutputStreamWriter(System.out));

		StringBuilder buf = new StringBuilder();
		for(int j=0; j<num_tests; ++j)
		{
			String[] data_overview = in.readLine().split(" ");

			// the variables
			int d = Integer.parseInt(data_overview[0]);
			int p = Integer.parseInt(data_overview[1]);
			int u = Integer.parseInt(data_overview[2]);
			int v = Integer.parseInt(data_overview[3]);
			
			double left = 0, right = d/(p-1);
			while(right-left>0.0001){
				double middle = (left+right)/2;
				if(putPosts(d, u, v, p, middle)){
					left = middle;
				} else {
					right = middle;
				}
			}			
			// print
			buf.append("Case #");
			buf.append(j+1);
			buf.append(": ");
			buf.append(right);
			buf.append("\n");
		}
		System.out.println(buf);	
	}
}



