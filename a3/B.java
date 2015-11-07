import java.io.*;
import java.util.*;


class Edge {
	public final int in;
	public final int out;
	public final int weight;

	public Edge(int i, int o, int w) {
		in = i;
		out = o;
		weight = w;
	}

	
	public static int compareByWeight(Edge a, Edge b) {
		if(a.weight == b.weight) return 0;
		if(a.weight < b.weight) return -1;
		return 1;
	}

	public static int compareByNodes(Edge a, Edge b) {
		if(a.in != b.in) 
			return a.in-b.in;
		else 
			return a.out-b.out;
	}
}



// Uninon-Find Node
class UFNode{
	public UFNode parent; 
	public int size;

	public UFNode(){
		parent = this;
		size = 1;
	}

	public void addChild(UFNode child) {
		child.parent = this;
		size += child.size;
	}
}

class B {
	// find and flatten path
	public static UFNode find(UFNode n) {
		List<UFNode> branch = new ArrayList<>();
		while (n!=n.parent){
			branch.add(n);
			n = n.parent;
		}
		for(UFNode child : branch)
			child.parent = n;
		return n;
	}	

	//  union
	public static void union(UFNode a, UFNode b) {
		UFNode pa = find(a);
		UFNode pb = find(b);
		if(pa == pb) return;
		if(pa.size > pb.size){
			pa.addChild(pb);
		} else {
			pb.addChild(pa);
		}
	}

	public static boolean trouble(List<Edge> connections, Edge trouble, int n) {
		int MSTsize = 0;
		UFNode[] UF = new UFNode[n];
		for(int i=0; i<n; ++i){
			UF[i] = new UFNode(); 
		}

		for(Edge e : connections) {
			if(MSTsize > n-1) break;
			if(e != trouble && find(UF[e.in]) != find(UF[e.out])){
				union(UF[e.in], UF[e.out]);
				MSTsize++;
			}
		}
		return (MSTsize < n-1)? true : false;
	}

	public static void main(String[] args) throws IOException{
		// BufferedReader input = new BufferedReader (new FileReader("testB.txt"));
		BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
		int num_tests = Integer.parseInt(input.readLine());

		StringBuilder buf = new StringBuilder();
		for(int j=0; j<num_tests; ++j)
		{
			
			// the separation line
			if (j>0) input.readLine();

			List<Edge> connections = new ArrayList<Edge>();
			List<Integer> troubleEdges = new ArrayList<Integer>();
			

			String line =  input.readLine();
			String[] nodes = line.split(" ");
			int n = Integer.parseInt(nodes[0]);
			int m = Integer.parseInt(nodes[1]);
			
			for(int i = 0; i < m; i++){
				line =  input.readLine();
				nodes = line.split(" ");
				int in = Integer.parseInt(nodes[0]);
				int out = Integer.parseInt(nodes[1]);
				connections.add(new Edge(in-1, out-1, 1));
			}

			for(int i = 0; i < m; i++) {
				if(trouble(connections, connections.get(i), n))
					troubleEdges.add(i);
			}

			// print
			buf.append("Case #");
			buf.append(j+1);
			buf.append(": ");
			for(int e : troubleEdges){
				buf.append(e+1);
				buf.append(" ");
			}
			buf.append("\n");
		}
		System.out.println(buf);	
	}
}



