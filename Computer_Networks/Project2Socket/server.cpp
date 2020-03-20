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
#include <cstdio>
#include <fstream>
#define SERVER_PORT 5432
#define MAX_PENDING 5
#define MAX_LINE 256

using namespace std;

void JOIN(vector<string> &agentHolder, string agent, char response[]){
	
	time_t  t = time(NULL);
	struct tm *mytime = localtime(&t);
	char timeHolder[100];
	strftime(timeHolder, 100, "%x - %I:%M%p", mytime);
	
	ofstream outfile;
	outfile.open("log.txt", std::ios_base::app); 
	
	printf("%s: Received a “#JOIN” action from agent “%s”\n\n", timeHolder, agent.c_str());
	outfile << timeHolder << ": Received a \"#JOIN\" action from agent \"" << agent.c_str() << "\"\n\n"; 
	
	if(std::find(agentHolder.begin(), agentHolder.end(), agent) != agentHolder.end()) {
		printf("%s: Responded to agent “%s” with “$ALREADY MEMBER”\n\n", timeHolder, agent.c_str()); 
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with \"$ALREADY MEMBER\"\n\n";   
		strcpy(response, "$ALREADY MEMBER");
	} 
	else {
		printf("%s: Responded to agent “%s” with “$OK”\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with \"OK\"\n\n"; 
		agentHolder.push_back(agent); 
		strcpy(response, "$OK");
	}
	
	outfile.close();
}

void LEAVE(vector<string> &agentHolder, string agent, char response[]){
	time_t  t = time(NULL);
	struct tm *mytime = localtime(&t);
	char timeHolder[100];
	strftime(timeHolder, 100, "%x - %I:%M%p", mytime);
	
	ofstream outfile;
	outfile.open("log.txt", std::ios_base::app);
	
	printf("%s: Received a “#LEAVE” action from agent “%s”\n\n", timeHolder, agent.c_str());
	outfile << timeHolder << ": Received a \"#LEAVE\" action from agent \"" << agent.c_str() << "\"\n\n";	
	
	if(find(agentHolder.begin(), agentHolder.end(), agent) != agentHolder.end()) {
		agentHolder.erase(remove(agentHolder.begin(), agentHolder.end(), agent), agentHolder.end());
		printf("%s: Responded to agent “%s” with “$OK”\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with \"OK\"\n\n"; 
		strcpy(response, "$OK");
	}
	else{
		printf("%s: Responded to agent “%s” with “$NOT MEMEBER”\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with \"$NOT MEMBER\"\n\n";
		strcpy(response, "$NOT MEMBER");
	}
	
	outfile.close();
}

void LIST(vector<string> agentHolder, string agent, char response[]){
	
	time_t  t = time(NULL);
	struct tm *mytime = localtime(&t);
	char timeHolder[100];
	strftime(timeHolder, 100, "%x - %I:%M%p", mytime);
	
	ofstream outfile;
	outfile.open("log.txt", std::ios_base::app);
	
	printf("%s: Received a “#LIST” action from agent “%s”\n\n", timeHolder, agent.c_str());
	outfile << timeHolder << ": Received a \"#LIST\" action from agent \"" << agent.c_str() << "\"\n\n";
	
	if(std::find(agentHolder.begin(), agentHolder.end(), agent) != agentHolder.end()) {
		printf("%s: Responded to agent “%s” with “$OK”\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with \"OK\"\n\n";
		strcpy(response, "$OK");
		//<agent_IP, seconds_online>.
		
		vector<string>::iterator agentIterator; 
	
		for(agentIterator = agentHolder.begin(); agentIterator != agentHolder.end(); ++agentIterator){
			cout << "<" << *agentIterator << ", tempTime" << ">" << endl; 
		}
	}
	else{
		printf("%s: : No response is supplied to agent “%s” (agent not active)\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": No response is supplied to agent \"" << agent.c_str() << "\" (agent not active)\n\n";
	}
	
	outfile.close();
}

void LOG(vector<string> agentHolder, string agent, char response[]){
	
	time_t  t = time(NULL);
	struct tm *mytime = localtime(&t);
	char timeHolder[100];
	strftime(timeHolder, 100, "%x - %I:%M%p", mytime);
	
	ofstream outfile;
	outfile.open("log.txt", std::ios_base::app);
	
	printf("%s: Received a “#LOG” action from agent “%s”\n\n", timeHolder, agent.c_str());
	outfile << timeHolder << ": Received a \"#LOG\" action from agent \"" << agent.c_str() << "\"\n\n";
	
	if(std::find(agentHolder.begin(), agentHolder.end(), agent) != agentHolder.end()) {
		ifstream fin;
		fin.open("log.txt");
		
		if(!fin){
			cout << "Error opening file. Terminating the program." << endl << endl;
			exit(-1); 
		}
		
		cout << "Below are the contents for log.txt:\n\n\n";
		
		string line;
		
		while(getline(fin, line)){
			cout << line << endl; 
		}
		outfile << timeHolder << ": Responded to agent \"" << agent.c_str() << "\" with \"OK\"\n\n";
		strcpy(response, "$OK");
		fin.close(); 
	}	
	else{
		printf("%s: : No response is supplied to agent “%s” (agent not active)\n\n", timeHolder, agent.c_str());
		outfile << timeHolder << ": No response is supplied to agent \"" << agent.c_str() << "\" (agent not active)\n\n";
	}
	
	outfile.close();
}

using namespace std;

int main(int argc,char* argv[])
{
	vector<string> agentHolder;
	
	struct sockaddr_in sin;
	
	socklen_t len;
	int s, new_s;
	/* build address data structure */
		//bzero((char *)&sin, sizeof(sin));

	sin.sin_family = AF_INET;
	//sin.sin_addr.s_addr = inet_addr(SERVER_ADDR);
	sin.sin_port = htons(atoi(argv[1]));
	
	/* setup passive open */
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
		char response[MAX_LINE] = "";
		listen(s, MAX_PENDING);
		/* wait for connection, then receive and print text */
	
		if ((new_s = accept(s, (struct sockaddr *)&sin,  
                       (socklen_t*)&len))<0) 
		{ 
			perror("accept"); 
			exit(EXIT_FAILURE); 
		} 
		
		//memset(buf,0,1024);
		
		char bufTemp[1024]; 
		int valread = read(new_s, buf, MAX_LINE); 
		
		if(strlen(buf) > valread) buf[strlen(buf)-(strlen(buf)-valread)] = '\0'; 
		
		char addressHolder[256];
		inet_ntop(AF_INET, &(sin.sin_addr), addressHolder, INET_ADDRSTRLEN);
		inet_ntop(AF_INET, &my_addr.sin_addr, myIP, sizeof(myIP));
		
		cout << "Cats" << endl;
		//printf("IP address is: %s\n", inet_ntoa(sin.sin_addr));
		
		if(strcmp(buf, "JOIN") == 0)
			JOIN(agentHolder, addressHolder, response); 
		else if(strcmp(buf, "LEAVE") == 0)
			LEAVE(agentHolder, addressHolder, response); 
		else if(strcmp(buf, "LIST") == 0)
			LIST(agentHolder, addressHolder, response); 
		else if(strcmp(buf, "LOG") == 0)
			LOG(agentHolder, addressHolder, response); 
		else
			return 0; 
		
		send(new_s , response, strlen(response) , 0 ); 
		close(new_s);
	}while(true);
}