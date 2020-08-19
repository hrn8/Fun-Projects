/******************************
 * CacheStats.cpp submitted by: enter your first and last name and net ID
 * CS 3339 - Spring 2019
 * Project 4 Branch Predictor
 * Copyright 2019, all rights reserved
 * Updated by Lee B. Hinkle based on prior work by Martin Burtscher and Molly O'Neil
 ******************************/
#include <iostream>
#include <cstdlib>
#include <iomanip>
#include "CacheStats.h"
using namespace std;

CacheStats::CacheStats() {
  cout << "Cache Config: ";
  if(!CACHE_EN) {
    cout << "cache disabled" << endl;
  } else {
    cout << (SETS * WAYS * BLOCKSIZE) << " B (";
    cout << BLOCKSIZE << " bytes/block, " << SETS << " sets, " << WAYS << " ways)" << endl;
    cout << "  Latencies: Lookup = " << LOOKUP_LATENCY << " cycles, ";
    cout << "Read = " << READ_LATENCY << " cycles, ";
    cout << "Write = " << WRITE_LATENCY << " cycles" << endl;
  }

  loads = 0;
  stores = 0;
  load_misses = 0;
  store_misses = 0;
  writebacks = 0;
  
  for (int i =0; i < SETS; i++){
      for (int j = 0; j < WAYS; j++){
        
        cacheArray[i][j] = 0; 
	modifiedArray[i][j] = 0; 
	validArray[i][j] = 0; 
	robinArray[i] = 0;
      }
  }
}

int CacheStats::access(uint32_t addr, ACCESS_TYPE type) {
    
    if(!CACHE_EN) { // cache is off
        return (type == LOAD) ? READ_LATENCY : WRITE_LATENCY;
    }
	
    //gets tag and index
    uint32_t tag = (addr >> 8); 
    uint32_t index = (addr >> 5) & 0x7; 
	
    if (type == LOAD){
        for (int i = 0; i < WAYS; i++)
            if (cacheArray[index][i] == tag){   //returns 0 as we hit
                return 0;
            }
    //if initialized to 0, sets a new value and moves round robin    
    if (validArray[index][robinArray[index]]  != 1) {
        cacheArray[index][robinArray[index]] = tag;	
        validArray[index][robinArray[index]] = 1; 
	robinArray[index] = (robinArray[index]+1)%4; 
	load_misses++; 
        return 30; 
    }
     //if modified, writes back/sets modified to 0
     else if ( modifiedArray[index][robinArray[index]] == 1){
        writebacks++; 
        cacheArray[index][robinArray[index]]  = tag;
        modifiedArray[index][robinArray[index]] = 0; 
        robinArray[index] = (robinArray[index]+1)%4; 
        load_misses++; 
        return 40;
    }    
    //if its valid but not modified, modifies it
    else if (validArray[index][robinArray[index]]  == 1){
        cacheArray[index][robinArray[index]]  = tag;
        modifiedArray[index][robinArray[index]] = 1; 
        robinArray[index] = (robinArray[index]+1)%4; 
        load_misses++; 
        return 30; 
	}
}
 
    if (type == STORE){
        for (int i =0; i < WAYS; i++)
            if (cacheArray[index][i] == tag){   //returns 0 as we hit
             return 0;
            }
    //if initialized to 0, sets a new value and moves round robin    
    if (validArray[index][robinArray[index]] == 0){
        cacheArray[index][robinArray[index]]  = tag;	
        validArray[index][robinArray[index]] = 1; 
        robinArray[index] = (robinArray[index]+1)%4; 
	store_misses++; 
	return 10; 
    }
    //if its valid but not modified, modifies it
    else if (validArray[index][robinArray[index]]  == 1){
        cacheArray[index][robinArray[index]]  = tag;
	modifiedArray[index][robinArray[index]] = 1; 
	robinArray[index] = (robinArray[index]+1)%4; 
	store_misses++;  
	return 10; 
    }
}
	
}

void CacheStats::increaseLoadStore(ACCESS_TYPE type){
    if (type == LOAD)//increments loads
        loads++;
    
    if (type == STORE)  //increments stores
        stores++;
}

void CacheStats::printFinalStats() {
  /* TODO: your code here (don't forget to drain the cache of writebacks) */

  int accesses = loads + stores;
  int misses = load_misses + store_misses;
  cout << "Accesses: " << accesses << endl;
  cout << "  Loads: " << loads << endl;
  cout << "  Stores: " << stores << endl;
  cout << "Misses: " << misses << endl;
  cout << "  Load misses: " << load_misses << endl;
  cout << "  Store misses: " << store_misses << endl;
  cout << "Writebacks: " << writebacks << endl;
  cout << "Hit Ratio: " << fixed << setprecision(1) << 100.0 * (accesses - misses) / accesses;
  cout << "%" << endl;
}
