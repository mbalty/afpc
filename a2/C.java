import java.io.*;
import java.util.*;


class C {
	public static boolean pay(int l, int r, int p, int y) {
		int result = l, new_result = 0;

		for(int i = 0; i<y*12 && 0 < result ; ++i){
			new_result = result - p;
			new_result = (int)Math.floor(new_result + new_result/100.0*r);
			if(new_result < result)
				result=new_result;
			else break;
		}
		return (result<=0)?true:false;
	}	
	
	public static void main(String[] args) throws IOException{
		// BufferedReader in = new BufferedReader (new FileReader("test.txt"));
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		int num_tests = Integer.parseInt(in.readLine());
		BufferedWriter out = new BufferedWriter (new OutputStreamWriter(System.out));

		StringBuilder buf = new StringBuilder();
		for(int j=0; j<num_tests; ++j)
		{
			String[] data_overview = in.readLine().split(" ");

			int l = Integer.parseInt(data_overview[0]);
			int r = Integer.parseInt(data_overview[1]);
			int p = Integer.parseInt(data_overview[2]);
			int y = Integer.parseInt(data_overview[3]);
			
			int left = 0, right = p*12*y;
			while(left<=right) {
				int middle = (left+right)/2;
				if(pay(middle, r, p, y)){
					left = middle + 1;
				} else {
					right = middle-1;
				}
			}

			int l_max = right;

			left = 0;
			right = 100;

        	if(p<l) {
				while (pay(l, right, p, y)) {
                	right++;
            	}
				while(left<=right) {
					int middle = (left+right)/2;
					if(pay(l , middle, p, y)){
						left = middle + 1;
					} else {
						right = middle - 1;
					}
				}
			}

			String r_max;

			if(p>=l) 
				r_max = "infinity";
			else if(right < 0)
				r_max = "impossible";
			else 
				r_max = Integer.toString(right); 

			left = 0;
			right = l;

			while(left<=right) {
				int middle = (left+right)/2;
				if(pay(l , r, middle, y)){
					right = middle - 1;
				} else {
					left = middle + 1;
				}
			}

			int p_min = left;			

			// print
			buf.append("Case #");
			buf.append(j+1);
			buf.append(": ");
			buf.append(l_max+" ");
			buf.append(r_max+" ");
			buf.append(p_min+"\n");
		}
		System.out.println(buf);	
	}
}



