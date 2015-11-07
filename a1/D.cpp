#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <string>

#define C 299792458

using namespace std;

string unScramble(string scrambled) {
	string xVal;
	getline(stringstream(scrambled), xVal, '#');
	int swap = stoi(xVal);
	cout << scrambled << endl;
	return "";
}



int main(){
	
	int num_tests;
	cin >> num_tests;
		

	if (num_tests < 1 || num_tests > 200000)
	{
		cout << "Error: Number of tests out of bounds!" <<  endl;
		exit(-1);
	}	

	// long masses[num_tests];
	// for(int i=0; i<num_tests; ++i)
	// {
	// 	cin >> masses[i];
	// 	if(masses[i] < 1 || masses[i] > 100) 
	// 	{
	// 		cout << "Error: Mass value out of bounds!" <<  endl;
	// 		exit(-1);	
	// 	} 
	// }

	// for(int i=0; i<num_tests; ++i)
	// {
	// 	cout << "Case #" << i+1 << ": " << masses[i]*C*C << endl;	
	// }

	unScramble("12#abc");

	return 0;
}