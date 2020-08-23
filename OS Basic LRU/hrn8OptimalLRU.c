#include <stdio.h> 
#include <stdlib.h> 
  
int i, j, k, l, MAX_PAGES = 0, numberCount = 20;

void initializeValues(int referenceString[], int pageStorage[MAX_PAGES][2]){

  bool inStorageFlag = 0;
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
}

void initializeValues(int referenceString[], int pageStorage[MAX_PAGES]){

    int initialTableSize = 0;
    int currentlyStored[6] = {-1, -1, -1, -1, -1, -1};
  
    //setting the initial values
    for(i = 0; i < numberCount; i++){
        if(currentlyStored[referenceString[i]] == -1){
            currentlyStored[referenceString[i]] = 1;
            pageStorage[initialTableSize] = referenceString[i];
            initialTableSize++;
        }
        if(initialTableSize == MAX_PAGES){
            j = i;
            return;
        }
    }
}

void LRU(int referenceString[], int pageStorage[MAX_PAGES]){
  
    int swappedIndex = 0;
    int swappedFlag = 0;
    int pageFaults = MAX_PAGES;
    
    //we use 6 because our biggest number stored in the page is 5
    int LRUCurrentIndex[6];

    //initialize all the indexes to -1
    for(i = 1; i < 6; i++)
        LRUCurrentIndex[i] = -1; 

    //Fill up our initial array
    initializeValues(referenceString, pageStorage);
    printf("\nCurrently running the LRU Algorithm: Here are the values in the page table:\n");

    //The page table values
    for(i = 0; i < numberCount; i++)
        printf("%d ", referenceString[i]);

    //If there were no duplicates, we can start from the end
    if (j == 0)
        j = MAX_PAGES; 

    printf("\n\nAfter dealing with potential duplicates, here is the initial valeus in the page table: \n");

    for(i = 0; i < MAX_PAGES; i++)
        printf("%d ", pageStorage[i]);

    //goes through entire referenceString starting from last index not stored in the pageStorage
    printf("\n\nHere are the values being stored into the table, starting from Index %d:\n", j);
  
    //The values we will be working with
    for (i = j; i < numberCount; i++)
        printf("%d ", referenceString[i]);

    printf("\n\n"); 

    //Keeping track of the current indexes within our array
    for (i = 0; i < MAX_PAGES; i++)
        LRUCurrentIndex[pageStorage[i]] = i;
    
    for (i = j; i < numberCount; i++){
        printf("\n"); 
        
        swappedFlag = 0;
        swappedIndex = 0;
        
        //if it is -1 then we need to add it to the table
        if(LRUCurrentIndex[referenceString[i]] == -1){
        //array element getting swapped
            int arrayElement = MAX_PAGES-1;
            swappedIndex = pageStorage[MAX_PAGES-1];
            swappedFlag = 1; //for swapped tag
            pageFaults++;
            for(k = 0; k < MAX_PAGES-1; k++){
                if(LRUCurrentIndex[pageStorage[k]] < LRUCurrentIndex[pageStorage[arrayElement]]){
                    swappedIndex = pageStorage[k];
                    arrayElement = k;
                }
            }
            //Updating our table
            pageStorage[arrayElement] = referenceString[i];
            LRUCurrentIndex[swappedIndex] = -1;
        }
        
        //Keeping track of the LRU
        LRUCurrentIndex[referenceString[i]] = i;
        printf("Iteration %2d (value %d):   ", i, referenceString[i]);
        
        for (k = 0; k < MAX_PAGES; k++)
            printf(" %d", pageStorage[k]);
        
        //If there is a swap that occured
        if(swappedFlag == 1)
            printf("  *(Index %d was swapped out)", swappedIndex); 
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
    for(i = 0; i < MAX_PAGES; i++)
        OptimalLocation[i] = 0; 
    
    initializeValues(referenceString, pageStorage);

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
    for (i = j; i < numberCount; i++)
        printf("%d ", referenceString[i]);
    
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

void BeladyAnomaly(int referenceString[], int pageStorage[MAX_PAGES]){
    
    j = 0;
    
    int inCurrentTable[6] = {-1, -1, -1, -1, -1, -1};  //For current Index
    int pageFaults = MAX_PAGES; 
    
    printf("\n\nCurrently running the Belady's Anomaly Algorithm: Here are the values in the page table:\n");

    for(i = 0; i < numberCount; i++)
       printf("%d ", referenceString[i]);

    //setting the initial values
    initializeValues(referenceString, pageStorage);

    if (j == 0)
        j = MAX_PAGES; 

    printf("\n\nAfter dealing with potential duplicates, here is the initial values in the page table: \n");
    
    for(i = 0; i < MAX_PAGES; i++){
        printf("%d ", pageStorage[i]);
        inCurrentTable[pageStorage[i]] = i;  //storage position
    }

    printf("\n\n");   
    
    for (i = j; i < numberCount; i++){
        printf("\n"); 
        int swappedFlag = -1;
        //if the number is not in the current table, we operate on it
        if(inCurrentTable[referenceString[i]] == -1){
            
            //Set the index to equal -1 as it is being removed
            inCurrentTable[pageStorage[0]] = -1;
            
            //move it to the back
            for(k = 0; k < MAX_PAGES-1; k++)
                pageStorage[k] = pageStorage[k+1];
            
            pageStorage[MAX_PAGES-1] = referenceString[i];
            inCurrentTable[referenceString[i]] = 1;
            pageFaults++; 
            swappedFlag = 1;
            
        }
        printf("Iteration %2d (value %d):   ", i, referenceString[i]);
        
        for (k = 0; k < MAX_PAGES; k++)
            printf(" %d", pageStorage[k]);
        
        if(swappedFlag == 1){
            printf("  *(Index %d was swapped out)", 0); 
        }
    }
    
    printf("\n\nThe total amount of page faults is %d.", pageFaults);
} 

int main(void){ 
    do {
        printf("Please enter the number of frames (1-4): ");
        scanf("%d", &MAX_PAGES);
    } while (MAX_PAGES < 1 || MAX_PAGES > 4);

    int generatedArray[numberCount]; 
    int LRUpageHolder[MAX_PAGES][2];
    int OptimalpageHolder[MAX_PAGES][2]; 
    int BeladyAnomalypageHolder[MAX_PAGES];
  
    for(i = 0; i < numberCount; i++)
      generatedArray[i] = BeladyAnomalypageHolder[i] = rand()% 5+ 1;
    
    //runs the LRU Algorithm
    //BeladyAnomaly(generatedArray, generatedArray);
    Optimal(generatedArray, OptimalpageHolder);

    return 0; 
} 