/******************************
 * CacheStats.h submitted by: enter your first and last name and net ID
 * CS 3339 - Spring 2019
 * Project 5 Data Cache Simulation
 * Copyright 2019, all rights reserved
 * Updated by Lee B. Hinkle based on prior work by Martin Burtscher and Molly O'Neil
 ******************************/

#ifndef __CACHE_STATS_H
#define __CACHE_STATS_H

#include <cstdint>
#include "Debug.h"
using namespace std;

#ifndef CACHE_EN
#define CACHE_EN 1
#endif

#ifndef BLOCKSIZE
#define BLOCKSIZE 32
#endif

#ifndef SETS
#define SETS 8
#endif

#ifndef WAYS
#define WAYS 4
#endif

#ifndef LOOKUP_LATENCY
#define LOOKUP_LATENCY 0
#endif

#ifndef READ_LATENCY
#define READ_LATENCY 30
#endif

#ifndef WRITE_LATENCY
#define WRITE_LATENCY 10
#endif

enum ACCESS_TYPE { LOAD, STORE };

class CacheStats {
  private:
    /* TODO: you probably want to add some member variables here to represent
     * the parts of the cache contents you need to model! */
      
    uint32_t tag;
    uint32_t index;
    uint32_t word_offset;
    uint32_t byte_offset; 
    int round_robin; 
    
    uint32_t cacheArray[4][32]; 
    bool modifiedArray[4][32]; 
    
    uint32_t read_way[4][4]; 
    uint32_t write_way[4][4]; 
    
    uint32_t cacheArray[WAY][SETS];
    bool modifiedArray[WAY][SETS]; 
    uint32_t robinArray[SETS];
    
    
    int loads;
    int stores;
    int load_misses;
    int store_misses;
    int writebacks;
    
    

  public:
    CacheStats();
    int access(uint32_t, ACCESS_TYPE);
    void increaseLoadStore(ACCESS_TYPE type); 
    void printFinalStats();
};

#endif
