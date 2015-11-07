import java.io.*;
import java.util.*;


class Stick {
	public int points;
	public Set<Stick> above;
	public Set<Stick> under;
	public boolean removed;
	public Stick(int v) {
		points = v;
		above = new HashSet<Stick>();
		under = new HashSet<Stick>();
		removed = false;
	}

	public void putOn(Stick c) {
		under.add(c);
		c.above.add(this);
	}

	public int remove() {
		if(removed || !above.isEmpty())
			return -1;
		removed = true;
		for(Stick s : under) {
			s.above.remove(this);
		}
		return points;
	}
}


class D {
	public static void main(String[] args) throws IOException{
		// BufferedReader in = new BufferedReader (new FileReader("testD.txt"));
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		int num_tests = Integer.parseInt(in.readLine());
		BufferedWriter out = new BufferedWriter (new OutputStreamWriter(System.out));

		StringBuilder buf = new StringBuilder();
		for(int j=0; j<num_tests; ++j)
		{
			
			// the separation line
			if (j>0) in.readLine();
			String[] line = in.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			int m = Integer.parseInt(line[1]);
			
			line = in.readLine().split(" ");
						
			Stick[] G = new Stick[n];
			for(int i=0; i<n; ++i){
				G[i] = new Stick(Integer.parseInt(line[i]));
			}

			for(int i=0; i<m; ++i){
				line = in.readLine().split(" ");
				int a = Integer.parseInt(line[0])-1;
				int b = Integer.parseInt(line[1])-1;
				G[a].putOn(G[b]);
			}

			int removedAll = 0;
			int removedOnce = 1;
			int points = 0;
			while(removedOnce>0 && removedAll<n){
				removedOnce = 0;
				for(Stick s : G) {
					int p = s.remove(); 
					if(p>=0){
						points += p;
						removedOnce++;

					}
				}
			}


			// print
			buf.append("Case #");
			buf.append(j+1);
			buf.append(": ");
			buf.append(points);
			buf.append("\n");
		}
		System.out.println(buf);	
	}
}



