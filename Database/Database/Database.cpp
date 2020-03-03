/**
    Employee Database
    Database.cpp
    Purpose: Mimics a database system, allows a verified admin to login and access the Employee Data

    @author Hunter Noey
    @version 1.1 3/1/20 
*/

#include "Employee.h"
#include <iostream>
#include <iomanip>
#include <vector>
#include <fstream>
#include <string>
#include <algorithm>

/**
    Validates if an Employee exists in the Database.

    @param 
    EmployeeRecords: The database containing the Employee data
    tempID: The ID being searched in the database.
    @return The Employee's status in the database
*/

bool isInDataBase(vector<Employee> EmployeeRecords, int tempID) {

    for (Employee e : EmployeeRecords) {
        if (e.getID() == tempID) return false;
    }
    return true;
}

/**
    Adds a user to the current database.

    @param 
    EmployeeRecords: The database containing the Employee data
*/

void addEmployee(vector<Employee> &EmployeeRecords) {
    string fN, lN;
    int tempID;
    double tempSalary;
    OCCUPATION o;
    int tempO;

    std::cout << "Please Enter the Employee Credentials.\n";
    std::cout << "First Name: ";
    cin >> fN;

    std::cout << "Last Name: ";
    cin >> lN;

    std::cout << "ID: ";
    cin >> tempID;

    while (!isInDataBase(EmployeeRecords, tempID)) {
        std::cout << "The employee ID already exists. Please enter a valid ID: ";
        cin >> tempID;
    }

    std::cout << "Salary: ";
    cin >> tempSalary;

    // DATA_ANALYST, PROGRAMMER, RECEPTIONIST, ENGINEER
    std::cout << "Occupation (0 for Data Analyst, 1 for Programmer, 2 for Receptionist, 3 for Engineer: ";
    cin >> tempO;

    while (tempO > 3 || tempO < 0) {
        std::cout << "invalid Occupation. Please enter a valid occupation. ";
    }

    o = static_cast<OCCUPATION>(tempO);

    Employee temp(fN, lN, tempID, tempSalary, o);
    EmployeeRecords.push_back(temp);

    std::cout << "The Employee has been entered into the database.\n\n";
}

/**
    Removes a user in the current database, assuming their ID is within the database.

    @param 
    EmployeeRecords: The database containing the Employee data
*/

void deleteFromDatabase(vector<Employee> &EmployeeRecords) {
    int tempID; 
    std::cout << "Enter the ID of the Employee you want to delete: ";
    cin >> tempID;

    for (std::vector<Employee>::iterator e = EmployeeRecords.begin(); e != EmployeeRecords.end(); e++) {
        if (e->getID() == tempID) {
            std::cout << "Deleting the Employee from the Database...\n\n";
            EmployeeRecords.erase(e);
            return;
        }
    }

    std::cout << "The Employee does not exist.\n\n";
}

/**
    Displays the users within the database

    @param 
    EmployeeRecords: The database containing the Employee data
*/

void displayEmployeeData(vector<Employee> EmployeeRecords) {
    std::cout << "----------------------------------------------------------------------------\n";
    std::cout << "ID      FIRST NAME      LAST NAME       OCCUPATION      SALARY       STRIKES\n";
    std::cout << "----------------------------------------------------------------------------\n";

    for (int i = 0; i < EmployeeRecords.size(); i++) {
        string occupation;

        if (EmployeeRecords[i].getOccupation() == DATA_ANALYST)
            occupation = "Data Analyst";
        else if (EmployeeRecords[i].getOccupation() == PROGRAMMER)
            occupation = "Programmer";
        else if (EmployeeRecords[i].getOccupation() == RECEPTIONIST)
            occupation = "Receptionist";
        else if (EmployeeRecords[i].getOccupation() == ENGINEER)
            occupation = "Engineer";

        std::cout << fixed << left << setw(8) << EmployeeRecords[i].getID()
                  << left << setw(16) << EmployeeRecords[i].getFirstName()
                  << setw(16) << EmployeeRecords[i].getLastName()
                  << setw(16) << occupation << setprecision(2)
                  << right << setw(8) << EmployeeRecords[i].getsalary()
                  << right << setw(7) << EmployeeRecords[i].getStrikes()
                  << endl;
    }

    std::cout << endl << endl;
}

/**
    Searches a user in the current database, displays their data if they are in the database.

    @param 
    EmployeeRecords: The database containing the Employee data
*/

void searchUser(vector<Employee> EmployeeRecords) {
    int tempID;
    std::cout << "Please enter a valid Employee ID: ";
    cin >> tempID;

    for (Employee e : EmployeeRecords) {
        if (e.getID() == tempID) {
            std::cout << "The Employee exists! Fetching their data...\n\n";
            std::cout << "----------------------------------------------------------------------------\n";
            std::cout << "ID      FIRST NAME      LAST NAME       OCCUPATION      SALARY       STRIKES\n";
            std::cout << "----------------------------------------------------------------------------\n";

            string occupation;

            if (e.getOccupation() == DATA_ANALYST)
                occupation = "Data Analyst";
            else if (e.getOccupation() == PROGRAMMER)
                occupation = "Programmer";
            else if (e.getOccupation() == RECEPTIONIST)
                occupation = "Receptionist";
            else if (e.getOccupation() == ENGINEER)
                occupation = "Engineer";

            std::cout << fixed << left << setw(8) << e.getID()
                << left << setw(16) << e.getFirstName()
                << setw(16) << e.getLastName()
                << setw(16) << occupation << setprecision(2)
                << right << setw(8) << e.getsalary()
                << right << setw(7) << e.getStrikes()
                << endl << endl;
            return;
        }
    }

    std::cout << "The Employee does not exist.\n\n";
}

/**
    Sorts two Employees based on ID

    @param 
    EmployeeA, EmployeeB
    @return The Employee's sorted by highest ID
*/

bool byID(Employee A, Employee B) {
    return A.getID() > B.getID();
}

/**
    Sorts two Employees based on first name

    @param 
    EmployeeA, EmployeeB
    @return The Employee's sorted alpabedically by first name
*/

bool byFN(Employee A, Employee B) {
    return A.getFirstName() < B.getFirstName();
}

/**
    Sorts two Employees based on last name

    @param 
    EmployeeA, EmployeeB
    @return The Employee's sorted alpabedically by last name
*/

bool byLN(Employee A, Employee B) {
    return A.getLastName() < B.getLastName();
}

/**
    Sorts two Employees based on Salary

    @param 
    EmployeeA, EmployeeB
    @return The Employee's sorted by highest Salary
*/

bool bySalary(Employee A, Employee B) {
    return A.getsalary() > B.getsalary();
}

(Likely a scrapped feature)

bool byStrikes(Employee A, Employee B) {
    return A.getStrikes() > B.getStrikes();
}

void sortDatabase(vector<Employee>& EmployeeRecords) {
    std::cout << "How would you like to sort the data?\n";
    std::cout << "   A. By ID\n";
    std::cout << "   B. By First Name\n";
    std::cout << "   C. By Last Name\n";
    std::cout << "   D. By Salary\n";
    std::cout << "   E. By Strikes\n";
    std::cout << "Please enter yoru choice: ";
    char c;
    cin >> c;

    switch (c) {
        case 'A':
            sort(EmployeeRecords.begin(), EmployeeRecords.end(), byID);
            break;
       case 'B':
            sort(EmployeeRecords.begin(), EmployeeRecords.end(), byFN);
            break;
        case 'C':
            sort(EmployeeRecords.begin(), EmployeeRecords.end(), byLN);
            break;
        case 'D':
            sort(EmployeeRecords.begin(), EmployeeRecords.end(), bySalary);
            break;
        case 'E':
            sort(EmployeeRecords.begin(), EmployeeRecords.end(), byStrikes);
            break;
        default:
            std::cout << "Thud!";
    }

    std::cout << "The Database has been sorted.\n\n";
}

/**
    Modifies an existing user in the current database.

    @param 
    EmployeeRecords: The database containing the Employee data
*/

void modifyUser(vector<Employee> &EmployeeRecords) {
    
    int tempID;
    std::cout << "Enter the ID of the Employee you want to delete: ";
    cin >> tempID;

    for (int i = 0; i < EmployeeRecords.size(); i++) {
        if (EmployeeRecords[i].getID() == tempID) {
            std::cout << "How would you like to modify the employee?\n";
            std::cout << "   A. Change First Name\n";
            std::cout << "   B. Change Last Name\n";
            std::cout << "   C. Change Salary\n";
            std::cout << "   D. Increment Strikes\n";
            std::cout << "Please enter yoru choice: ";

            char c;
            cin >> c;
            string temp;

            switch(c) {
                case 'A':
                    std::cout << "Please enter the new first name: ";
                    cin >> temp;
                    EmployeeRecords[i].setFirstName(temp);
                    std::cout << "\n\nThe changes have been saved.\n\n";
                    return;
                    break;
                case 'B':
                    std::cout << "Please enter the new last name: ";
                    cin >> temp;
                    EmployeeRecords[i].setLastName(temp);
                    std::cout << "\n\nThe changes have been saved.\n\n";
                    return;
                    break;
                case 'C':
                    std::cout << "Please enter the new last name: ";
                    cin >> temp;
                    EmployeeRecords[i].setLastName(temp);
                    std::cout << "\n\nThe changes have been saved.\n\n";
                    return;
                    break;
                case 'D':
                    std::cout << "Added a strike to the selected Employee.\n\n";
                    EmployeeRecords[i].incrementStrike();
                    std::cout << "\n\nThe changes have been saved.\n\n";
                    return;
                    break;
                default:
                    break;
            }
        }
    }

    std::cout << "The Employee does not exist.\n\n";
}

int main()
{   
    ifstream afin("ADMINDATA.txt");

    if (!afin) {
        std::cout << "EMPLOYEEDATA.txt failed to open. Terminating the program.\n";
        exit(-1);
    }

    vector<pair<string, string>> ADMIN_ACCOUNTS;

    while (afin) {
        string tempuserName, tempPassword;
        std::getline(afin, tempuserName, '~');
        std::getline(afin, tempPassword);
        pair<string, string> tempEmployee(tempuserName, tempPassword);
        ADMIN_ACCOUNTS.push_back(tempEmployee);
    }

    std::cout << endl << endl << endl << endl << fixed << right << setw(60) << "-----WELCOME TO THE INITECH ADMIN PANEL-----\n\n\n\n\n";

    string userName;
    string password;

    std::cout << "Username: ";
    cin >> userName;

    std::cout << "Password: ";
    cin >> password;

    int tries = 3;

    for (auto a : ADMIN_ACCOUNTS) {
        if (a.first == userName && a.second == password) {
            goto granted;
        }
        else if (a.first == userName && a.second != password) {
            while (a.second != password) {
                tries--;
                if (tries == 0) {
                    std::cout << "You have exceeded the maximum login attempts. Terminating the program.\n\n";
                    exit(-2);
                }
                std::cout << "The password you entered is incorrect.";
                std::cout << "Please enter the password again: ";
                cin >> password;
            }
            goto granted;
        }
    }

    std::cout << "You entered an incorrect username. Terminating the program...\n\n\n";
    exit(-3);

    granted:
    
    std::cout << "\n\n                          ACCESS GRANTED\n\n\n\n\n\n";
    
    //vector<ADMIN> ADMINRECORDS;
    vector<Employee> EmployeeRecords; 

    ifstream fin("EMPLOYEEDATA.txt");

    if (!fin) {
        std::cout << "EMPLOYEEDATA.txt failed to open. Terminating the program.\n";
        exit(-1); 
    }

    while (fin) {
        string tempF, tempL, temp;
        int tempID;
        double tempS;
        OCCUPATION tempO;
        int tempI;
        std::getline(fin, tempF, '~');
        std::getline(fin, tempL, '~');
        std::getline(fin, temp, '~');
        tempID = stoi(temp); 
        std::getline(fin, temp, '~');
        tempS = stol(temp);
        fin >> tempI;
        fin.ignore();
        tempO = static_cast<OCCUPATION>(tempI);
        Employee tempEmployee(tempF, tempL, tempID, tempS, tempO);
        EmployeeRecords.push_back(tempEmployee);
    }

    fin.close();

    while (true) {
        std::cout << "Enter a number to perform an operation.\n";
        std::cout << "1. Add a User to the Database.\n";
        std::cout << "2. Delete a User from the Database.\n";
        std::cout << "3. Search a User in the Database.\n";
        std::cout << "4. Display the Database.\n";
        std::cout << "5. Save the Database\n";
        std::cout << "6. Sort the Database\n";
        std::cout << "7. Modify a User's Data\n";
        std::cout << "8. Terminate the program.\n";
        std::cout << "Please enter your choice (1-8): ";
        int input = 0;
        cin >> input;

        std::cout << "\n\n";

        while (input > 8 || input < 1) {
            std::cout << "Invalid choice. Please enter a choice between 1-6: ";
            cin >> input;
            std::cout << "\n\n";
        }

        ofstream fout;

        switch (input) {
            case 1:
                addEmployee(EmployeeRecords);
                break;
            case 2:
                deleteFromDatabase(EmployeeRecords);
                break;
            case 3:
                searchUser(EmployeeRecords);
                break;
            case 4:
                displayEmployeeData(EmployeeRecords);
                break;
            case 5:
                fout.open("EMPLOYEEDATA.txt");
                for (int i = 0; i < EmployeeRecords.size(); i++) {
                    fout << EmployeeRecords[i].getFirstName() << "~"
                        << EmployeeRecords[i].getLastName() << "~"
                        << EmployeeRecords[i].getID() << "~"
                        << EmployeeRecords[i].getsalary() << "~"
                        << EmployeeRecords[i].getOccupation();
                        
                    if(i+1 < EmployeeRecords.size()) fout << endl;
                }
                std::cout << "The database has been modified.\n\n";
                fout.close();
                break;
            case 6:
                sortDatabase(EmployeeRecords);
                break;
            case 7:
                modifyUser(EmployeeRecords);
                break;
            case 8:
                return 0;
                break;
            default:
                std::cout << "We should not be here, but here we are. Terminating the program\n";
                exit(-1);
        }
    }

    return 0;
}
