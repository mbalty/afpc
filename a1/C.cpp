#include <iostream>
#include <cstdlib>
#include <vector>

using namespace std;

// smaller than operator overloading for std::vector
template <typename T>
bool operator< (vector<T> a, vector<T> b)
{
	size_t la = a.size(), lb = b.size();
	if(la < lb) return true;
	if(lb < la) return false;
	int i;
	bool equal=true;
	for(i=0; i < la-1; ++i)
	{
		equal = equal && (a[i]==b[i]);
		if(a[i] > b[i])	return false;
	}

	return true!=equal;	
}

// general quick sort implementation that sorts in descending order ---------------------------------------
//	swap two elements in the array
template <typename T>
void swap(vector<T> &a, int i, int j){
	T temp = a[i];
	a[i] = a[j];
	a[j] = temp;
}

// 	partition array by middle pivot
template <typename T>
int partition(vector<T> &a, int start, int end)
{
	T pivot = a[(start+end)/2];
	while(start<=end)
	{
		while(pivot < a[start]) start++;
		while(a[end] < pivot) end--;
		if(start<=end) 
		{
			swap(a, start, end);
			start++;
			end--;
		}
	}
	return start;
}

// 	inner quicksort recursive
template <typename T>
void quickSort_(vector<T> &a, int start, int end)
{
	int middle = partition<T>(a, start, end);
	if (start<middle-1) quickSort_<T>(a, start, middle-1);
	if (middle<end) quickSort_<T>(a, middle, end);
}

// 	user quicksort function
template <typename T>
void quickSort(vector<T> &a)
{
	quickSort_<T>(a, 0, a.size()-1);
}
// ----------------------------------------------------------------------------------------------------


// 	rearanges skill values in winning ranking:
// 		- sort each school skill values in descending order(each row in the matrix)
// 		- sort schools in descending order as well
void do_championship(vector<vector<int> > &schools)
{
	for(size_t i=0; i<schools.size(); ++i)
	{
		quickSort<int>(schools[i]);
	}
	quickSort<vector<int> >(schools);
}


int main(){
	ios_base::sync_with_stdio(false);
	int num_tests;
	cin >> num_tests;
	
	if (num_tests < 1 || num_tests > 20)
	{
		cout << "Error: Number of tests out of bounds!" <<  endl;
		exit(-1);
	}	


	// loop through tests
	vector< vector<int> > school;
	for(int i=0; i<num_tests; ++i)
	{
		int num_schools;
		cin >> num_schools;
		
		// read scores matrix for each test
		school = vector< vector<int> >(num_schools, vector<int>(5, 0));
		for(int j=0; j < num_schools; ++j)
		{	
			for(int k=0; k < 5; ++k)
			{
				cin >> school[j][k];
			}
		}

		// re-arrange martix in winning order
		do_championship(school);
	
		cout << "Case #" << i+1 << ":" << endl;
		for(size_t j=0; j < school.size(); ++j)
		{	
			int k;
			for(k=0; k < 4; ++k)
			{
				cout << school[j][k] << " ";
			}
			cout << school[j][k] << endl;
		}
	}


	return 0;
}




