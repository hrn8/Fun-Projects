#include <stdio.h> 
#include <stdlib.h> 
  
int i, j, k, l, MAX_PAGES = 0, numberCount = 20;

void LRU(int referenceString[], int pageStorage[MAX_PAGES][2]){
  
  int LRUCounter[MAX_PAGES];

  for(i = 0; i < MAX_PAGES; i++)
    LRUCounter[i] = 0; 

  //initial storage of the array
  for(i = 0; i < MAX_PAGES; i++)
  pageStorage[i][0] = -1;

  int inArrayFlag = 0;
  int swappedIndex = 0;
  int inStorageFlag = 0; 
  int swappedFlag = 0;
  int pageFaults = MAX_PAGES;

  printf("\nCurrently running the LRU Algorithm: Here are the values in the page table:\n");

  for(i = 0; i < numberCount; i++)
    printf("%d ", referenceString[i]);

   //setting the initial values
  for(i = 0; i < MAX_PAGES; i++){
    pageStorage[i][0] = referenceString[i];
 
    for (k = 0; k < i; k++){
      if (pageStorage[k][0] == pageStorage[i][0] && pageStorage[k][0] != -1){
      inStorageFlag = 1;
  
      j = k;
  
    do{
      inStorageFlag = 0;
      pageStorage[i][0] = referenceString[j]; 
   
      for(l = 0; l < i; l++)
        if(pageStorage[i][0] == pageStorage[l][0])
          inStorageFlag = 1;
     
      j++;
    }while(inStorageFlag == 1);
    }
  }
}

   if (j == 0)
      j = MAX_PAGES; 

  printf("\n\nAfter dealing with potential duplicates, here is the initial valeus in the page table: \n");

  for(i = 0; i < MAX_PAGES; i++)
    printf("%d ", pageStorage[i][0]);

  //setting priority for FIFO
  for(i = 0; i < MAX_PAGES; i++)
    pageStorage[i][1] = MAX_PAGES-i;

 //goes through entire referenceString starting from last index not stored in the pageStorage
  printf("\n\nHere are the values being stored into the table, starting from Index %d:\n", j);
  for (i = j; i < numberCount; i++){
     printf("%d ", referenceString[i]);
   }

   printf("\n\n"); 
   
  for (i = j; i < numberCount; i++){
    printf("\n"); 
    
    for(k = 0; k < MAX_PAGES; k++)
    if(pageStorage[k][0] == referenceString[i]) inArrayFlag = 1; //if inArrayFlag = 1, no need to swap
  
    //if the number is not in pageStorage
    if(inArrayFlag == 0){
      pageFaults++; 
    
    for(k = 0; k <= i; k++){
	    for(j = 0; j < MAX_PAGES; j++){
		    if (pageStorage[j][0] == referenceString[k]){
          LRUCounter[j] = k;
          //printf("\n %d, %d", j, LRUCounter[j]);
        }
	}

  

  

  
  

}

  swappedIndex = 0; 
      for(k = MAX_PAGES-1; k >= 1; k--)
        if(LRUCounter[k] < LRUCounter[swappedIndex])
          swappedIndex = k; 
      
    
    pageStorage[swappedIndex][0] = referenceString[i];
    pageStorage[swappedIndex][1] = 0;
        //printf("Index %d is getting swapped. at i = %d\n", swappedIndex, i);
      swappedFlag = 1;
        
    for(k = 0; k < MAX_PAGES-1; k++)
      LRUCounter[k] = 0;
    }

    printf("Iteration %2d (value %d):   ", i, referenceString[i]);
      for (k = 0; k < MAX_PAGES; k++){
          printf(" %d", pageStorage[k][0]);
        } 

        if(swappedFlag == 1){
          printf("  *(Index %d was swapped out)", swappedIndex); 
          
          for(k = 0; k < MAX_PAGES; k++)
            if(k != swappedIndex)
              pageStorage[k][1]++; 
        }
        
          
    inArrayFlag = 0; 
    swappedFlag = 0;
  }

  printf("\n\nThe total amount of page faults is %d.", pageFaults); 
}

void Optimal(int referenceString[], int pageStorage[MAX_PAGES][2]){
  j = 0;
  int inStorageFlag = 0; 
  int OptimalLocation[MAX_PAGES];
  int swappedIndex = -1; 
  int pageFaults = MAX_PAGES; 

   printf("\n\nCurrently running the Optimal Algorithm: Here are the values in the page table:\n");

  for(i = 0; i < numberCount; i++)
    printf("%d ", referenceString[i]);

  //set initial values of the page
  for(i = 0; i < MAX_PAGES; i++){
    OptimalLocation[i] = 0; 
  }
 
  //setting the initial values
  for(i = 0; i < MAX_PAGES; i++){
    pageStorage[i][0] = referenceString[i];
  
  for (k = 0; k < i; k++){
    if (pageStorage[k][0] == pageStorage[i][0] && pageStorage[k][0] != -1){
      inStorageFlag = 1;
      j = k;
      
      do{
        inStorageFlag = 0;
        pageStorage[i][0] = referenceString[j]; 
   
        for(l = 0; l < i; l++)
          if(pageStorage[i][0] == pageStorage[l][0])
          inStorageFlag = 1;
     
          j++;
        }while(inStorageFlag == 1);
      }
    }
  }

  if (j == 0)
    j = MAX_PAGES; 

  printf("\n\nAfter dealing with potential duplicates, here is the initial valeus in the page table: \n");

  //setting priority for FIFO
  for(i = 0; i < MAX_PAGES; i++)
    pageStorage[i][1] = MAX_PAGES-i;

  inStorageFlag = 0; 

  for(i = 0; i < MAX_PAGES; i++)
    printf("%d ", pageStorage[i][0]);

  //goes through entire referenceString starting from last index not stored in the pageStorage
  printf("\n\nHere are the values being stored into the table, starting from Index %d:\n", j);
  for (i = j; i < numberCount; i++){
     printf("%d ", referenceString[i]);
   }

  printf("\n\n"); 
 
  //if the number is in the page table no swapping necessary
  for (i = j; i < numberCount; i++){
    for (k = 0; k < MAX_PAGES; k++)
      if (pageStorage[k][0] == referenceString[i]) inStorageFlag = 1;

      printf("\n"); 

      //number is going to be swapped into the array
      if(inStorageFlag == 0){
        for(j = numberCount; j > i; j--)
          for(k = 0; k < MAX_PAGES; k++)
            if (pageStorage[k][0] == referenceString[j]) OptimalLocation[k] = j;
     
      //Highest Index
      for (k = 0; k < MAX_PAGES; k++)
        if(OptimalLocation[k] >= OptimalLocation[swappedIndex]) swappedIndex = k; 

      
      //Checks if the number is not used, therefore the earliest iteration gets swapped. 
      for (k = MAX_PAGES-1; k >= 0; k--)
      if(OptimalLocation[k] == 0) swappedIndex = k;

       

      for (k = MAX_PAGES-1; k >= 0; k--)
        if (OptimalLocation[k] == OptimalLocation[swappedIndex] && pageStorage[k][1] > pageStorage[swappedIndex][1]) swappedIndex = k;

        

      pageStorage[swappedIndex][0] = referenceString[i];
      pageStorage[swappedIndex][1] = 0;
  }
  
     printf("Iteration %2d (value %d):  ", i, referenceString[i]);
      for (k = 0; k < MAX_PAGES; k++){
          printf(" %d", pageStorage[k][0]);
          OptimalLocation[k] = 0; 

          if(k != swappedIndex)
              pageStorage[k][1]++;
    } 

    if(swappedIndex != -1){
      printf("  *(Index %d was swapped out)", swappedIndex); 
      pageFaults++; 
    }
      
 
    swappedIndex = -1; 
    inStorageFlag = 0; 
  }

  printf("\n\nThe total amount of page faults is %d.", pageFaults); 
}

int main(void) 
{ 
  
  
  
  do {
    printf("Please enter the number of frames (1-4): ");
    scanf("%d", &MAX_PAGES);
  } while (MAX_PAGES < 1 || MAX_PAGES > 4);

  int generatedArray[numberCount]; 
  int LRUpageHolder[MAX_PAGES][2];
  int OptimalpageHolder[MAX_PAGES][2]; 
  
  for(i = 0; i<numberCount; i++)
    generatedArray[i] = rand()% 5+ 1;
    
  //runs the LRU Algorithm
  LRU(generatedArray, LRUpageHolder);
  Optimal(generatedArray, OptimalpageHolder);

  return 0; 
} 