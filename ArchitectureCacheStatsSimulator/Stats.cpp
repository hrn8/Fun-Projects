/******************************
 * Name:  Hunter Noey hrn8
 * CS 3339 - Spring 2019
 ******************************/
#include "Stats.h"

Stats::Stats() {
  cycles = PIPESTAGES - 1; // pipeline startup cost
  flushes = 0;
  bubbles = 0;
  stalls = 0;

  memops = 0;
  branches = 0;
  taken = 0;
  isLoadStore = false; 

  for(int i = IF1; i < PIPESTAGES; i++) {
    resultReg[i] = -1;
  }
}

void Stats::clock() {
  cycles++;

  // advance all pipeline flip flops
  for(int i = WB; i > IF1; i--) {
    resultReg[i] = resultReg[i-1];
  }
  // inject a no-op into IF1
  resultReg[IF1] = -1;
}

void Stats::registerSrc(int r) {
    if (r == 0)
        return; 
    
    for (int i = EXE1; i < WB; i++)
        if (resultReg[i] == r){
            int sum = WB - i;       //calculate range to insert bubbles
            
            while (sum > 0){
                //cycles++; 
                bubble();
                sum--;
            }
            break;
        } 
}

void Stats::registerDest(int r) {
    resultReg[ID] = r;      //register dest
}

void Stats::flush(int count) { // count == how many ops to flush
    
    //increment 2 cycles via flush
    for (int i = 0; i < count; i++){
        clock();  
        flushes++; 
    }
}

void Stats::bubble() {
    bubbles++;
    cycles++;
}

void Stats::incrementStalls(int cycle){
    for (int i = 0; i < cycle; i++){
        stalls++;
        clock(); 
    }
        
}
