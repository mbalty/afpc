import java.io.*;
import java.util.*;


// Uninon-Find Node
class UFNode{
	public int money;
	public UFNode parent; 
	public int size;
	public boolean married;

	public UFNode(int m){
		money = m;
		parent = this;
		size = 1;
		married = false;
	}

	public void addChild(UFNode child) {
		child.parent = this;
		size += child.size;
	}
}

class A {
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
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		int num_tests = Integer.parseInt(in.readLine());
		BufferedWriter out = new BufferedWriter (new OutputStreamWriter(System.out));

		StringBuilder buf = new StringBuilder();
		for(int j=0; j<num_tests; ++j)
		{
			
			// the separation line
			if (j>0) in.readLine();
			String[] data_overview = in.readLine().split(" ");

			int a = Integer.parseInt(data_overview[0]);
			int b = Integer.parseInt(data_overview[1]);
			int c = Integer.parseInt(data_overview[2]);
			
			//  the notable people, initialize UFNodes 
			String[] money_str = in.readLine().split(" ");
			UFNode[] notable_people = new UFNode[a];
			for(int i=0; i<a-1; ++i){
				int money = Integer.parseInt(money_str[i]);
				notable_people[i] = new UFNode(money);
			}
			notable_people[a-1]= new UFNode(-1);

			//  set relatives
			for(int i=0; i<b; ++i){
				String[] relatives = in.readLine().split(" ");
				int o1 = Integer.parseInt(relatives[0]) - 1;
				int o2 = Integer.parseInt(relatives[1]) - 1;
				union(notable_people[o1], notable_people[o2]);
			}

			//  set married 
			for(int i=0; i<c; ++i){
				String[] married = in.readLine().split(" ");
				int o1 = Integer.parseInt(married[0]) - 1;
				int o2 = Integer.parseInt(married[1]) - 1;
				notable_people[o1].married = true;
				notable_people[o2].married = true;
				union(notable_people[o1], notable_people[o2]);
			}

			int max_money = -1;
			for(int i=0; i<a-1; ++i){
				if(find(notable_people[i]) != find(notable_people[a-1]) && !notable_people[i].married)
					max_money = Math.max(max_money, notable_people[i].money);
			}

			// print
			buf.append("Case #");
			buf.append(j+1);
			buf.append(": ");
			if(max_money<0)
				buf.append("impossible");
			else
				buf.append(max_money);
			buf.append("\n");
		}
		System.out.println(buf);	
	}
}



