import java.io.*;
import java.util.*;


class C {
	
	// boolean smallerThan(int[] a, int[] b)
	// {
	// 	size_t la = a.size(), lb = b.size();
	// 	if(la < lb) return true;
	// 	if(lb < la) return false;
	// 	int i;
	// 	bool equal=true;
	// 	for(i=0; i < la-1; ++i)
	// 	{
	// 		equal = equal && (a[i]==b[i]);
	// 		if(a[i] > b[i])	return false;
	// 	}

	// 	return true!=equal;	
	// }

// 	// quick sort implementation that sorts arrays of arrays in descending order ---------------------------------------
// 	//	swap two elements in the array
// 	void swap(int[][] a, int i, int j){
// 		int[] temp = a[i];
// 		a[i] = a[j];
// 		a[j] = temp;
// 	}

// // 	partition array by middle pivot
// int partition(int[][] a, int start, int end)
// {
// 	int[] pivot = a[(start+end)/2];
// 	while(start<=end)
// 	{
// 		while(smallerThan(pivot, a[start])) start++;
// 		while(smallerThan(a[end], pivot)) end--;
// 		if(start<=end) 
// 		{
// 			swap(a, start, end);
// 			start++;
// 			end--;
// 		}
// 	}
// 	return start;
// }

// // 	inner quicksort recursive
// void quickSort_(int[][] a, int start, int end)
// {
// 	int middle = partition(a, start, end);
// 	if (start<middle-1) quickSort_(a, start, middle-1);
// 	if (middle<end) quickSort_(a, middle, end);
// }

// // 	user quicksort function
// void quickSort(int[][] a)
// {
// 	quickSort_<T>(a, 0, a.size()-1);
// }
// ----------------------------------------------------------------------------------------------------


// 	rearanges skill values in winning ranking:
// 		- sort each school skill values in descending order(each row in the matrix)
// 		- sort schools in descending order as well
	private static void do_championship(int[][] schools)
	{
		for(int i=0; i<schools.length; ++i)
		{
		    List<Integer>list = Ints.asList(schools[i]);
		    Collections.sort(list, Collections.reverseOrder());
		    schools[i] = ArrayUtils.toPrimitive(myList.toArray(new Integer[myList.size()]));
		}

		Arrays.sort(schools, new Comparator<int[]>() {
			@Override
	    	public int compare(int[] a, int[] b) {
	        	int la = a.length, lb = b.length;
				if(la < lb) return 1;
				if(lb < la) return -1;
				int i;
				boolean equal=true;
				for(i=0; i < la-1; ++i)
				{
					equal = equal && (a[i]==b[i]);
					if(a[i] > b[i])	return -1;
				}

				if (equal) return 0;

				return 1;	
	    	}
		});
	}





	

	public static void main(String[] args) throws IOException{
		Scanner scan = new Scanner(System.in).useDelimiter("\n");
		int num_tests = scan.nextInt();

		if (num_tests < 1 || num_tests > 20)
		{
			throw new Error("Error: Number of tests out of bounds!");
		}	

		long result;
		StringBuilder b = new StringBuilder();
		int[][] school;
		for(int i=0; i<num_tests; ++i)
		{
			int num_schools = scan.nextInt();
			
			// read scores matrix for each test
			school = new int[num_schools][5];
			for(int j=0; j < num_schools; ++j)
			{	
				for(int k=0; k < 5 && scan.hasNextInt() ; ++k)
				{
					school[j][k] = scan.nextInt();
				}
			}
			scan.close();

			b.append("Case #");
			b.append(i+1);
			b.append(":\n");

			// re-arrange martix in winning order
			do_championship(school);
		
			for(int j=0; j < school.length; ++j)
			{	
				int k;
				for(k=0; k < 4; ++k)
				{
					b.append(school[j][k]);
					b.append(" ");
				}
				b.append(school[j][k]);
				b.append("\n");
			}

			System.out.println(b);	
			b.delete(0, b.length());
		}

	}
}