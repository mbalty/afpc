#include <iostream>
#include <cstdlib>
// #include <string>


using namespace std;

int main(int argc, char** argv){
	

	int num_tests;
	cin >> num_tests;
		

	if (num_tests < 0 || num_tests > 20)
	{
		cout << "Error: Number of tests out of bounds!" <<  endl;
		exit(-1);
	}	

	string names[num_tests];
	for(int i=0; i<num_tests; ++i)
	{
		cin >> names[i]; 
	}

	for(int i=0; i<num_tests; ++i)
	{
		cout << "Case #" << i+1 << ": Hello " << names[i] << "!" << endl;	
	}

	return 0;
}