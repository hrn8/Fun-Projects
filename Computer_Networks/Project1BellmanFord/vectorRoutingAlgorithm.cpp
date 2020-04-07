#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct vectorRoutingTable{
    int cost; //How much it takes to get to the destination
    int nextHop;  //Where the hop is changed
    bool isNeighbor;	//Determines if nodes are neighbors
};

int main(int argc, char *argv[])
{
    if(argc != 3){
        cout << "Please enter 3 valid arguments.\n";  
        exit(1);		
    }
    
    int totalMessages = 0, temporaryMessage = 0;	//calculating the communication
    int lastID = -1;
    int totalRounds = 0;
	
    int finalNode;	//displaying the final node that swapped data
    
    //Used for calculating path for 0 to 3 (topology1.txt)
    vector<int> pathDisplay1; //for 
	
    //Used for calculating path for 0 to 7 (topology2.txt)
    vector<int> pathDisplay2; //for 

    //Used for calculating path for 0 to 7 (topology2.txt)
    vector<int> pathDisplay3; //for 
	
    
	
    //Command-Line Arguments
    string file = argv[1]; 
    char *inputFile =  argv[1];
    const int rounds = atoi(argv[2]);
    
    if(rounds < 0){
        cout << "Invalid number for rounds. Please enter a positive number.\n"; 
        exit(1);
    }		
    
    ifstream fin;
    fin.open(inputFile);

    //Checking if the file opened properly
    if(!fin){
        cout << "Couldn't find " << inputFile << ". Terminating the program.\n";
        exit(1);
    }
   
    //for calculating array size, tempIndex1/tempIndex2 are used for the node path 
    int numPaths = 0, tempIndex1, tempIndex2;
    string tempString; 
    
    //Getting the array size, which is simply highest number + 1 (account for Node 0)
    while(!fin.eof()){
        fin >> tempIndex1;	
        fin >> tempIndex2;
        getline(fin, tempString);

        if(numPaths < tempIndex1) numPaths = tempIndex1+1;
        if(numPaths < tempIndex2) numPaths = tempIndex2+1;
    }
   
   //No valid arrays to use, therefore the code won't work
   if(numPaths < 1){
        cout << "There is nothing in the file, terminating the program.";
        return 1;
   }

    //reset file position
    fin.clear();
    fin.seekg(0, ios::beg);

    
    //For containing the path Data
    vectorRoutingTable **data = new vectorRoutingTable*[numPaths];
   
    //for 2-D Dynamically Allocated Arrays
    for(int i = 0; i < numPaths; ++i)
        data[i] = new vectorRoutingTable[numPaths];
    
	
    for(int i = 0; i < numPaths; i++)
        for(int k = 0; k < numPaths; k++)
            data[i][k].isNeighbor = false; 
    
	
    while(!fin.eof()){
        fin >> tempIndex1;  //gets index 1
        fin >> tempIndex2;  //gets index 2
        fin >> data[tempIndex1][tempIndex2].cost;  //gets the data between nodes given the current paths
        
        //index2/index1 is the mirror of index1/index2, so they share the same value
        data[tempIndex2][tempIndex1].cost = data[tempIndex1][tempIndex2].cost;  
        
        //They are neighbors
        data[tempIndex2][tempIndex1].isNeighbor = true;
        data[tempIndex1][tempIndex2].isNeighbor = true;
		
        //initialize the next Hop of the paths taken in
        data[tempIndex1][tempIndex2].nextHop = tempIndex2;
        data[tempIndex2][tempIndex1].nextHop = tempIndex1;
		
    }

   //for temporarily setting paths that don't exist for a max size so we can calculate a better path, else x to x nextHop is itself
    for(int i = 0; i < numPaths; i++) 
      for(int k = 0; k < numPaths; k++){
        if(data[i][k].isNeighbor == false && i != k) data[i][k].cost =  999999;
            if(i == k)	
                data[i][k].nextHop = i;
      }
	  
    //I use this for calculating path 0 to 23
    vectorRoutingTable temp;
	
    //The rounds
    for(int round = 0; round < rounds; round++){
        bool swapFlag = false;	//Calculates if something changed in the round
        //If a better path is found for a node, we update the existing one.
        for(int source = 0; source < numPaths; source++)
            for(int destination = 0; destination < numPaths; destination++){
                if(data[source][destination].isNeighbor) temporaryMessage++;
                    for(int alternativePath = 0; alternativePath < numPaths; alternativePath++){
                        //Bellman-Ford Algorithm: Distance(x,y) = minD(x,z) + minD(z,y)
                        if(data[source][destination].cost > data[source][alternativePath].cost + data[alternativePath][destination].cost){
                            data[source][destination].cost = data[source][alternativePath].cost + data[alternativePath][destination].cost;
                            data[source][destination].nextHop = data[source][alternativePath].nextHop;
                            swapFlag = true;
                            lastID = source; 
				
                            if(file == "topology3.txt" && source == 0 && destination == 23)
                                    temp.nextHop = alternativePath;
                            } 
                    }
                }
        if(swapFlag){
            totalRounds++; //If a swap occured, totalRounds will increase
            totalMessages = temporaryMessage;	//We increment DV messages if we had a successful round
        }
    }
	
    if(file == "topology1.txt" && data[0][3].cost != 999999){
        int tempHop = 3;
	
        while(tempHop != 0){
            pathDisplay1.push_back(tempHop);

            if(tempHop == data[0][tempHop].nextHop){
                tempHop = 0;
                pathDisplay1.push_back(tempHop);
            }
            else
                tempHop = data[0][tempHop].nextHop;
        }
		
            reverse(pathDisplay1.begin(), pathDisplay1.end());
	}
	
    if(file == "topology2.txt" && data[0][7].cost != 999999){
        int tempHop = 7;

        while(tempHop != 0){
            pathDisplay2.push_back(tempHop);

            if(tempHop == data[0][tempHop].nextHop){
                tempHop = 0;
                pathDisplay2.push_back(tempHop);
            }
            else
                tempHop = data[0][tempHop].nextHop;
            }

        reverse(pathDisplay2.begin(), pathDisplay2.end());
    }


    if(file == "topology3.txt" && data[0][23].cost != 999999){
        pathDisplay3.push_back(23);

        int tempHop = 23;

        while(temp.nextHop != 0){
            pathDisplay3.push_back(temp.nextHop);

            if(temp.nextHop == data[0][temp.nextHop].nextHop){
                temp.nextHop = 0;
                pathDisplay3.push_back(temp.nextHop);
            }
            else
                temp.nextHop = data[0][temp.nextHop].nextHop;
            }

        reverse(pathDisplay3.begin(), pathDisplay3.end());
    }

    //Displaying the results
    for(int source = 0; source < numPaths; source++){

        cout << "Node " << source << endl;
        cout << fixed << setw(8) << "Destination:" << setw(10) << "Cost:" << setw(16) << "NextHop:" << endl;
        for(int destination = 0; destination < numPaths; destination++){
            cout << setw(6) << destination;   //Path Node is going to
            //Packet has not reached its destination
            if(data[source][destination].cost == 999999) cout << setw(14) << "-";
            else 
                cout << setw(14) << data[source][destination].cost;

            //Packet has not reached its destination
            if(data[source][destination].cost == 999999) 
                cout << setw(15) << "N/A" << endl;
            else 
                cout << setw(15) << data[source][destination].nextHop << endl;
        }
    }

    cout << "\n\n\nThe total amount of rounds is: " << totalRounds << endl;
    cout << "The last ID of the node to converge is: " << lastID << endl;
    cout << "The total amount of messages exchanged is: " << totalMessages << endl; 

    if(file == "topology1.txt"){
        cout << "\n\nDisplaying the requested path for topology1.txt. Node 0 is sending a packet to Node 3. \nHere is the sequence: {";
        for (vector<int>::const_iterator i = pathDisplay1.begin(); i != pathDisplay1.end(); ++i){
            cout << *i;
            if(i+1 != pathDisplay1.end()) cout << ", ";
        }
        cout << "}" << endl << endl;
    }


    if(file == "topology2.txt"){
        cout << "\n\nDisplaying the requested path for topology2.txt. Node 0 is sending a packet to Node 7. \nHere is the sequence: {";
        for (vector<int>::const_iterator i = pathDisplay2.begin(); i != pathDisplay2.end(); ++i){
            cout << *i;
            if(i+1 != pathDisplay2.end()) cout << ", ";
        }
        cout << "}" << endl << endl;
    }

    if(file == "topology3.txt"){
        cout << "\n\nDisplaying the requested path for topology3.txt. Node 0 is sending a packet to Node 23. \nHere is the sequence: {";
        for (vector<int>::const_iterator i = pathDisplay3.begin(); i != pathDisplay3.end(); ++i){
            cout << *i;
            if(i+1 != pathDisplay3.end()) cout << ", ";
        }
        cout << "}" << endl << endl;
    }
    //deleting the arrays
    for(int i = 0; i < numPaths; i++) 
        delete[] data[i]; 

    delete [] data;

    return 0;
}