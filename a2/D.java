import java.io.*;
import java.util.*;


// Uninon-Find Node
class UFNode{
	public UFNode parent; 
	public UFNode hate;
	public int size;
	public int order;

	public UFNode(){
		parent = this;
		size = 1;
		hate = null;
	}

	public void addChild(UFNode child) {
		child.parent = this;
		size += child.size;
	}
}

class D {
	// find and flatten path
	public static UFNode find(UFNode node) {
		UFNode n = node;
		if(n == null) return null;
		List<UFNode> branch = new ArrayList<>();
		while (n!=n.parent){
			branch.add(n);
			n = n.parent;
		}
		for(UFNode child : branch){
			child.parent = n;
		}
		return n;
	}	

	//  union
	public static UFNode union(UFNode a, UFNode b) {
		UFNode pa = find(a);
		UFNode pb = find(b);
		if(pb == null) return pa;
		if(pa == null) return pb;

		// UFNode yourEnemyIsmine = union(a.hate, b.hate);
		// a.hate = yourEnemyIsmine;
		// b.hate = yourEnemyIsmine; 
		
		if(pa == pb) return pa;
		if(pa.size > pb.size){
			pa.addChild(pb);
			return pa;
		} else {
			pb.addChild(pa);
			return pb;
		}
	}

	
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
			String[] data_overview = in.readLine().split(" ");

			int n = Integer.parseInt(data_overview[0]);
			int m = Integer.parseInt(data_overview[1]);
			
			//  the countries, initialize UFNodes
			//  keep also an array that contains in i the root of all hated countries by country on posotion i in countries[]
			UFNode[] countries = new UFNode[n];
			for(int i=0; i<n; ++i){
				countries[i] = new UFNode();
			}

			//  set relations
			class Pair{
				public int o1;
				public int o2;
				Pair(int a, int b){
					o1 = a;
					o2 = b;
				}
			}

			List<Pair> blabla = new ArrayList<>();
			for(int i=0; i<m; ++i){
				String[] line = in.readLine().split(" ");
				
				int o1 = Integer.parseInt(line[1]) - 1;
				int o2 = Integer.parseInt(line[2]) - 1;

				// if friend relation, make alliance(union)
				// else make alliance with the according root in the hate array
				
				if(line[0].equals("F")) {
					blabla.add(new Pair(o1, o2));
				} else {
					UFNode yourEnemyIsmine = union(countries[o1].hate, countries[o2]);
					countries[o1].hate = yourEnemyIsmine;
					yourEnemyIsmine = union(countries[o1], countries[o2].hate);
					countries[o2].hate = yourEnemyIsmine;
				}
			}

			for(Pair p : blabla){
				union(countries[p.o1], countries[p.o2]);
				UFNode yourEnemyIsmine = union(countries[p.o1].hate, countries[p.o2].hate);
				countries[p.o1].hate = yourEnemyIsmine;
				countries[p.o2].hate = yourEnemyIsmine;
			}

			UFNode pLea = find(countries[0]);
`			// print
			buf.append("Case #");
			buf.append(j+1);
			buf.append(": ");
			if(pLea.size > n/2)
				buf.append("yes\n");
			else
				buf.append("no\n");
				

		}
		System.out.println(buf);	
	}
}



