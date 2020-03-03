#include <iostream>
using namespace std;

enum OCCUPATION {
    DATA_ANALYST, PROGRAMMER, RECEPTIONIST, ENGINEER, ADMIN
};

class Employee {
private:
    string firstName, lastName;
    int ID;
    double salary;
    int strikes;
    OCCUPATION occupation;

public:
    Employee(string, string, int, double, OCCUPATION);
    void setFirstName(string);
    void setLastName(string);
    void setID(int);
    void setsalary(double);
    void incrementStrike(){strikes++;}
    void setOccupation(OCCUPATION);
    string getFirstName() { return firstName; }
    string getLastName() { return this->lastName; }
    int getID() { return ID; }
    double getsalary() { return this->salary; }
    int getStrikes() { return this->strikes; }
    OCCUPATION getOccupation() { return this->occupation; }
    ~Employee() {}
};