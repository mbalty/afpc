import java.io.*;
import java.util.*;


class E {
	
	public static void main(String[] args) throws IOException{
		// BufferedReader in = new BufferedReader (new FileReader("test.txt"));
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		int num_tests = Integer.parseInt(in.readLine());
		BufferedWriter out = new BufferedWriter (new OutputStreamWriter(System.out));

		StringBuilder buf = new StringBuilder();
		for(int j=0; j<num_tests; ++j)
		{
			String[] data_overview = in.readLine().split(" ");

			// the variables
			int n = Integer.parseInt(data_overview[0]);
			long p = Long.parseLong(data_overview[1]);
			long q = Long.parseLong(data_overview[2]);
			long r = Long.parseLong(data_overview[3]);
			long s = Long.parseLong(data_overview[4]);
			
			// initialize every postion with the sum from 0 to i
			long[] bowls = new long[n+1];
			long max = 0;
			for(int i=1; i<=n; ++i){
				long tmp = (((i)*p+q)%r)+s;
				max = Math.max(max, tmp);
				bowls[i] = bowls[i-1]+tmp;
			}

			long sum = bowls[n];
			long third = sum/3;

			long result=0;
			
			if(max>third){
				result = sum-max;
			}else{
				//  set relations
				long left_sum = 0, right_sum = sum, middle_sum = 0;
				int left = 0, right; 
				long maxSum = 0;

				// find the maximum sum Lea can be left with
				for(right=1; right<=n; ++right){
					left_sum = bowls[left];
					middle_sum=bowls[right]-bowls[left];
					right_sum = sum - bowls[right];
					while(middle_sum>third && left<=right){
						middle_sum = bowls[right]-bowls[left];
						left_sum = bowls[left];
						maxSum = Math.max(maxSum, sum - Math.max(Math.max(left_sum, middle_sum), right_sum));
						left++;
					}
				}
				result = maxSum;
			}
			// print
			buf.append("Case #");
			buf.append(j+1);
			buf.append(": ");
			buf.append(result);
			buf.append("\n");
		}
		System.out.println(buf);	
	}
}



