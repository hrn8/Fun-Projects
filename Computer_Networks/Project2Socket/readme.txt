This program demonstrates socket programming between a server and a client (agent). When the program runs, a server is created and
the agent has a choice of 4 operations ("#JOIN", "#LIST", "#LOG", and "#LEAVE"). The log.txt file shows a sample case of 3 agents performing 
operations in the server.

How to run (Linux)
Create the server: ./server (number)
Eg: ./server 8614

Connecting the agent to the server: ./agent (IP of server) (socket # of server) (command)
Eg: ./agent 147.26.231.153 8614 "#JOIN" 
