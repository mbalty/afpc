#include <iostream>
#include <cstdlib>


using namespace std;

int main(){
	ios_base::sync_with_stdio(false);
	int num_tests;
	cin >> num_tests;
	
	string name;
	for(int i=0; i<num_tests; ++i)
	{
		cin >> name; 
		cout << "Case #" << i+1 << ": Hello " << name << "!" << endl;	
	}

	return 0;
}