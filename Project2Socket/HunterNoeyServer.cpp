#include <unistd.h> 
#include <stdio.h> 
#include <sys/socket.h> 
#include <stdlib.h> 
#include <netinet/in.h> 
#include <string> 
#include <cstring>
#include <iostream>
#include <iomanip>
#include <vector>
#include<iterator>
#include <algorithm>
#include <netdb.h>
#include <arpa/inet.h>
#include <sys/timeb.h>
#include <sys/time.h>
#include <cstdio>
#include <fstream>
#include<sstream>
#include <sys/stat.h>
#include <fcntl.h>
#define SERVER_PORT 5432
#define MAX_PENDING 5
#define MAX_LINE 256
#define MAXBUF 1024

using namespace std;

void JOIN(vector<string> &agentHolder, vector<time_t> &timeHold, string agent, char response[]){
	
	//calculating time
	time_t t = time(NULL);
	struct tm *mytime = localtime(&t);
	timeval milliHolder;
	gettimeofday(&milliHolder, NULL);
	char timeHolder[100];
	strftime(timeHolder, 100, "%T.", mytime);
	int msHolder = milliHolder.tv_usec / 1000;
	stringstream msString;
	msString << msHolder;
	string msStorer;
	msString >> msStorer;
	strcat(timeHolder, msStorer.c_str());
	
	int bytesSent = 0; //number of bytes sent
	
	//opens the file to write to
	ofstream outfile;
	outfile.open("log.txt", std::ios_base::app); 
	
	//LOG data
	printf("%s: Received a “#JOIN” action from agent “%s”\n\n", timeHolder, agent.c_str());
	outfile << timeHolder << ": Received a \"#JOIN\" action from agent \"" << agent.c_str() << "\"\n\n"; 
	
	if(std::find(agentHolder.begin(), agentHolder.end(), agent) != agentHolder.end()) {
		//Once again calculating the time.
		mytime = localtime(&t);
		gettimeofday(&milliHolder, NULL);
		memset(timeHolder, 0, sizeof timeHolder);
		strftime(timeHolder, 100, "%T.", mytime);
		msHolder = milliHolder.tv_usec / 1000;
		msString << msHolder;  //conversion
		msString >> msStorer;  //conversion
		strcat(timeHolder, msStorer.c_str());
		printf("%s: Responded to agent “%s” with “$ALREADY MEMBER”\n\n", timeHolder, agent.c_str()); 
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with \"$ALREADY MEMBER\"\n\n";   
		strcpy(response, "$ALREADY MEMBER");
		bytesSent = strlen(response);
	} 
	else {
		//adding the agent/time to the vectors
		agentHolder.push_back(agent);
		
		//Once again calculating the time.
		mytime = localtime(&t);
		gettimeofday(&milliHolder, NULL);
		memset(timeHolder, 0, sizeof timeHolder);
		strftime(timeHolder, 100, "%T.", mytime);
		msHolder = milliHolder.tv_usec / 1000;
		msString << msHolder;	//conversion
		msString >> msStorer;	//conversion
		strcat(timeHolder, msStorer.c_str());	//time holder
		
		//Response
		printf("%s: Responded to agent “%s” with “$OK”\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with \"OK\"\n\n";
		time(&t);
		timeHold.push_back(t); 
		strcpy(response, "$OK");
		bytesSent = strlen(response);
	}
	
	cout << "Total amount of bytes sent: " << bytesSent << endl << endl;
	
	outfile.close();
}

void LEAVE(vector<string> &agentHolder, vector<time_t> &timeHold, string agent, char response[]){
	
	//calculating time
	time_t t = time(NULL);
	struct tm *mytime = localtime(&t);
	timeval milliHolder;
	gettimeofday(&milliHolder, NULL);
	char timeHolder[100];
	strftime(timeHolder, 100, "%T.", mytime);
	int msHolder = milliHolder.tv_usec / 1000;
	stringstream msString;
	msString << msHolder;
	string msStorer;
	msString >> msStorer;
	strcat(timeHolder, msStorer.c_str());
	
	int bytesSent = 0; //number of bytes sent
	
	//opens the file to write to
	ofstream outfile;
	outfile.open("log.txt", std::ios_base::app);
	
	//LOG data
	printf("%s: Received a “#LEAVE” action from agent “%s”\n\n", timeHolder, agent.c_str());
	outfile << timeHolder << ": Received a \"#LEAVE\" action from agent \"" << agent.c_str() << "\"\n\n";	
	
	//if agent is found, deletes it from the vector (along with its time_t partner)
	if(find(agentHolder.begin(), agentHolder.end(), agent) != agentHolder.end()) {
		
		//deletion 
		vector<string>::iterator it = find(agentHolder.begin(), agentHolder.end(), agent);
		int pos = distance(agentHolder.begin(), it);
		agentHolder.erase(remove(agentHolder.begin(), agentHolder.end(), agent), agentHolder.end());
		timeHold.erase(timeHold.begin() + pos);
		
		//calculating time
		mytime = localtime(&t);
		gettimeofday(&milliHolder, NULL);
		memset(timeHolder, 0, sizeof timeHolder);
		strftime(timeHolder, 100, "%T.", mytime);
		msHolder = milliHolder.tv_usec / 1000;
		msString << msHolder;
		msString >> msStorer;
		strcat(timeHolder, msStorer.c_str());
		
		//response
		printf("%s: Responded to agent “%s” with “$OK”\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with \"OK\"\n\n"; 
		strcpy(response, "$OK");
		bytesSent = strlen(response);
	}
	else{
		//calculating time
		mytime = localtime(&t);
		gettimeofday(&milliHolder, NULL);
		memset(timeHolder, 0, sizeof timeHolder);
		strftime(timeHolder, 100, "%T.", mytime);
		msHolder = milliHolder.tv_usec / 1000;
		msString << msHolder;  //conversion
		msString >> msStorer;  //conversion
		strcat(timeHolder, msStorer.c_str());
		
		//response
		printf("%s: Responded to agent “%s” with “$NOT MEMEBER”\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with \"$NOT MEMBER\"\n\n";
		strcpy(response, "$NOT MEMBER");
		bytesSent = strlen(response);
	}
	
	cout << "Total amount of bytes sent: " << bytesSent << endl << endl;
	
	outfile.close();
}


void LOG(vector<string> agentHolder, string agent, char response[], int new_s){
	
	//calculating time
	time_t t = time(NULL);
	struct tm *mytime = localtime(&t);
	timeval milliHolder;
	gettimeofday(&milliHolder, NULL);
	char timeHolder[100];
	strftime(timeHolder, 100, "%T.", mytime);
	int msHolder = milliHolder.tv_usec / 1000;
	stringstream msString;
	msString << msHolder;
	string msStorer;
	msString >> msStorer;
	strcat(timeHolder, msStorer.c_str());
	
	int bytesSent = 0;
	
	//opens the file to write to
	ofstream outfile;
	outfile.open("log.txt", std::ios_base::app);
	
	//LOG data
	printf("%s: Received a “#LOG” action from agent “%s”\n\n", timeHolder, agent.c_str());
	outfile << timeHolder << ": Received a \"#LOG\" action from agent \"" << agent.c_str() << "\"\n\n";
	
	//writes to the agent if it is active
	if(std::find(agentHolder.begin(), agentHolder.end(), agent) != agentHolder.end()) {
		ifstream fin;
		fin.open("log.txt");
		
		if(!fin){
			cout << "Error opening file. Terminating the program." << endl << endl;
			exit(-1); 
		}
		
		
		string line;
		
		char response2[MAXBUF];
		
		int fileRead = open("log.txt", O_RDONLY); 
		
		int bytesRead;
		
		//writing the char array to the agent
		do{
			memset(response2, 0, MAXBUF);
			bytesRead = read(fileRead, response2, sizeof(response2));
			if(bytesRead < 0) break;
			write(new_s, response2, bytesRead);
			bytesSent += bytesRead; 
		}while(bytesRead > 0); 
		
		close(fileRead); 
		
		//calculating time
		mytime = localtime(&t);
		gettimeofday(&milliHolder, NULL);
		memset(timeHolder, 0, sizeof timeHolder);
		strftime(timeHolder, 100, "%T.", mytime);
		msHolder = milliHolder.tv_usec / 1000;
		msString << msHolder; //conversion
		msString >> msStorer; //conversion
		strcat(timeHolder, msStorer.c_str());
		
		//response
		printf("%s: Responded to agent “%s” with with the contents of log.txt.\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with the contents of log.txt.\n\n";
		//strcpy(response, "$OK");
		fin.close(); 
	}	
	else{
		//calculating time
		mytime = localtime(&t);
		gettimeofday(&milliHolder, NULL);
		memset(timeHolder, 0, sizeof timeHolder);
		strftime(timeHolder, 100, "%T.", mytime);
		msHolder = milliHolder.tv_usec / 1000;
		msString << msHolder; //conversion
		msString >> msStorer; //conversion
		strcat(timeHolder, msStorer.c_str());
		printf("%s: No response is supplied to agent “%s” (agent not active)\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": No response is supplied to agent \"" << agent.c_str() << "\" (agent not active)\n\n";
	}
	
	cout << "Total amount of bytes sent: " << bytesSent << endl << endl;
	
	outfile.close();
}

void LIST(vector<string> agentHolder, vector<time_t> timeHold, string agent, char response[], int new_s){
	
	//calculating time
	time_t t = time(NULL);
	struct tm *mytime = localtime(&t);
	timeval milliHolder;
	gettimeofday(&milliHolder, NULL);
	char timeHolder[100];
	strftime(timeHolder, 100, "%T.", mytime);
	int msHolder = milliHolder.tv_usec / 1000;
	stringstream msString;
	msString << msHolder;
	string msStorer;
	msString >> msStorer;
	strcat(timeHolder, msStorer.c_str());
	
	int bytesSent = 0;
	
	//opens the file to write to
	ofstream outfile;
	outfile.open("log.txt", std::ios_base::app);
	
	//LOG data
	printf("%s: Received a “#LIST” action from agent “%s”\n\n", timeHolder, agent.c_str());
	outfile << timeHolder << ": Received a \"#LIST\" action from agent \"" << agent.c_str() << "\"\n\n";
	
	if(std::find(agentHolder.begin(), agentHolder.end(), agent) != agentHolder.end()) {
		
		vector<string>::iterator agentIterator; 

		int i = 0;
		
		char response[MAXBUF] = ""; 
	
		for(agentIterator = agentHolder.begin(); agentIterator != agentHolder.end(); ++agentIterator){
			time_t temp = timeHold.at(i);
			//time_t temp2 = std::chrono::system_clock::to_time_t (std::chrono::system_clock::now()); 
			time_t temp2;
			time (&temp2);
			int seconds = difftime(temp2, temp);
			//cout << "<" << *agentIterator << ", " << seconds << ">" << endl;
			stringstream secondHolder;
			secondHolder << seconds;
			string secondHolder2;
			secondHolder >> secondHolder2;
			string tempConverter = "<" + *agentIterator + ", " + secondHolder2 + ">\n";
			strcat(response, tempConverter.c_str()); 
			//if(line.length() != 0) strcpy(response2, line.c_str());
			
			i++; 
		}
		
		bytesSent += write(new_s , response, strlen(response));
		memset(response, 0, MAXBUF);
		
		mytime = localtime(&t);
		gettimeofday(&milliHolder, NULL);
		memset(timeHolder, 0, sizeof timeHolder);
		strftime(timeHolder, 100, "%T.", mytime);
		msHolder = milliHolder.tv_usec / 1000;
		msString << msHolder;
		msString >> msStorer;
		strcat(timeHolder, msStorer.c_str());
		
		printf("%s: Responded to agent “%s” with the list of the active agents.\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with the list of the active agents.\n\n";
		//strcpy(response, "$OK");
		//<agent_IP, seconds_online>.
	}
	else{
		mytime = localtime(&t);
		gettimeofday(&milliHolder, NULL);
		memset(timeHolder, 0, sizeof timeHolder);
		strftime(timeHolder, 100, "%T.", mytime);
		msHolder = milliHolder.tv_usec / 1000;
		msString << msHolder;
		msString >> msStorer;
		strcat(timeHolder, msStorer.c_str());
		printf("%s: No response is supplied to agent “%s” (agent not active)\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": No response is supplied to agent \"" << agent.c_str() << "\" (agent not active)\n\n";
	}
	
	cout << "Total amount of bytes sent: " << bytesSent << endl << endl;
	
	outfile.close();
}

using namespace std;

int main(int argc,char* argv[])
{
	vector<string> agentHolder;
	vector<time_t> timeHold; 
	
	struct sockaddr_in sin;
	
	socklen_t len = sizeof(struct sockaddr);
	int s, new_s;
	
	
	bzero((char *)&sin, sizeof(sin));

	sin.sin_family = AF_INET;
	//sin.sin_addr.s_addr = INADDR_ANY;
	sin.sin_port = htons(atoi(argv[1]));
	
	// create a new socket to use for communication
	if ((s = socket(PF_INET, SOCK_STREAM, 0)) < 0) {
		perror("simplex-talk: socket");
		exit(-1);
	}

	if ((bind(s, (struct sockaddr *)&sin, sizeof(sin))) < 0) {
		perror("simplex-talk: bind");
		exit(-1);
	}
	
	do{
		char buf[MAX_LINE];
		memset(buf,0,MAX_LINE);
		char response[MAX_LINE] = "";
		listen(s, MAX_PENDING);
		
		//connect/read response
		if ((new_s = accept(s, (struct sockaddr *)&sin,  
                       (socklen_t*)&len))<0) 
		{ 
			perror("accept"); 
			exit(EXIT_FAILURE); 
		} 
		
		char bufTemp[1024]; 
		int valread = read(new_s, buf, MAX_LINE); 
		
		if(strlen(buf) > valread) buf[strlen(buf)-(strlen(buf)-valread)] = '\0'; 
		
		char addressHolder[256];
		
		inet_ntop(AF_INET, &(sin.sin_addr), addressHolder, INET_ADDRSTRLEN);
		
		if(strcmp(buf, "JOIN") == 0 || strcmp(buf, "#JOIN") == 0)
			JOIN(agentHolder, timeHold, addressHolder, response); 
		else if(strcmp(buf, "LEAVE") == 0 || strcmp(buf, "#LEAVE") == 0)
			LEAVE(agentHolder, timeHold, addressHolder, response); 
		else if(strcmp(buf, "LIST") == 0 || strcmp(buf, "#LIST") == 0)
			LIST(agentHolder, timeHold, addressHolder, response, new_s); 
		else if(strcmp(buf, "LOG") == 0 || strcmp(buf, "#LOG") == 0)
			LOG(agentHolder, addressHolder, response, new_s); 
		else
			return 0; 
		
		//send argument to the agent
		send(new_s , response, strlen(response) , 0 ); 
		
		close(new_s);
		
	}while(true);
	
	return 0;
}