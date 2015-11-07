import java.io.*;
import java.util.*;

/// edge class contains the in and out node indexes and the weight

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

// graph Node
class Node {
	public int val;
	public List<Node> children;
	public boolean visited;
	public Node(int v) {
		val = v;
		children = new ArrayList<Node>();
		visited = false;
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

class E {
	// recurse to the graph and fill the right path
	private static Stack<Integer> path = new Stack<Integer>();
	static private boolean getPath(Node a, Node n) {
		if(n.val == a.val) {
			path.push(a.val);
			return true;
		} else if(a.children.isEmpty()){
			return false;
		}
		a.visited = true;
		for(Node c : a.children){
			if(!c.visited && getPath(c, n)) {
				path.push(a.val);
				return true;
			}
		}
		return false;
	}


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

	public static void main(String[] args) throws IOException{
		// BufferedReader in = new BufferedReader (new FileReader("testE.txt"));
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		int num_tests = Integer.parseInt(in.readLine());
		BufferedWriter out = new BufferedWriter (new OutputStreamWriter(System.out));

		StringBuilder buf = new StringBuilder();
		for(int j=0; j<num_tests; ++j)
		{
			
			// the separation line
			if (j>0) in.readLine();

			int n = Integer.parseInt(in.readLine());
						
			List<Edge> connections = new ArrayList<Edge>();

			UFNode[] UF = new UFNode[n];
			Node[] G = new Node[n];
			for(int i=0; i<n; ++i){
				UF[i] = new UFNode(); 
				G[i] = new Node(i+1);
			}
			for(int i=0; i<n; ++i){
				String[] line = in.readLine().split(" ");
				for(int k=i+1; k<n; ++k){
					connections.add(new Edge(i, k, Integer.parseInt(line[k])));
				}
			}

			Collections.sort(connections, new Comparator<Edge>() {
				@Override
				public int compare(Edge a, Edge b){
					return Edge.compareByWeight(a, b);
				}
			});
			int MSTsize = 0;
			for(Edge e : connections) {
				if(MSTsize > n-1) break;
				if(find(UF[e.in]) != find(UF[e.out])){
					union(UF[e.in], UF[e.out]);
					// System.out.println(e.in+" "+e.out);
					G[e.in].children.add(G[e.out]);
					G[e.out].children.add(G[e.in]);
					MSTsize++;
				}
			}


			// finds path between 1 and n
			getPath(G[0], G[n-1]);
					

			// print
			buf.append("Case #");
			buf.append(j+1);
			buf.append(":\n");
			while(!path.empty()){
				buf.append(path.pop());
				buf.append(" ");
			}
			buf.append("\n");

		}
		System.out.println(buf);	
	}
}



