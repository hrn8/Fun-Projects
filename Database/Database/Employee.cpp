#include "Employee.h"
#include <iostream>
using namespace std;

Employee::Employee(string fn, string ln, int i, double d, OCCUPATION o) {
    this->firstName = fn;
    this->lastName = ln;
    this->ID = i;
    this->salary = d;
    this->strikes = 0;
    this->occupation = o;
}

void Employee::setFirstName(string s) {
    this->firstName = s;
}

void Employee::setLastName(string s) {
    this->lastName = s;
}

void Employee::setID(int i) {
    this->ID = i;
}

void Employee::setsalary(double d) {
    this->salary = d;
}

void Employee::setOccupation(OCCUPATION o) {
    this->occupation = o;
}
