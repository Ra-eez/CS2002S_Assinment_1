package cs2002s_assignment1;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

// Moegamat Ra-eez Stenekamp
// August 2017
// Class that calculates the median of a filtersize of a given array using threads

public class ThreadManager extends RecursiveAction  {
    
	  int lo; // arguments
	  int hi;
	  float[] arr;
	  static final int SEQUENTIAL_CUTOFF=500;
          int halfFilter;
          OrganisedArr finalAns;
          Float[] ans = new Float[hi+halfFilter]; // result 
	    
	  ThreadManager(float[] a, int l, int h, int halfFilter, OrganisedArr finalAns) { 
	    lo=l+1; hi=h; arr=a; this.halfFilter=halfFilter;this.finalAns=finalAns;
	  }


      
	  protected void compute(){
              
		  if((hi-lo) < SEQUENTIAL_CUTOFF) {
                      
                    // does calculations finding the median at each filtersize and posts answer to OrganisedArr
		    for(int i=lo; i < hi; i++){
                          
                        float[] toSort = new float[(2*halfFilter)+1];
                        int l = 0;
                        
                        // fills toSort with the values in the filtersize that need to be sorted
                        for (int j = i-halfFilter; j < i + halfFilter; j++){
                            
                            toSort[l] = arr[j];
                            l ++;
                         }
                        
                         Arrays.sort(toSort);
                         finalAns.ans[i] = toSort[halfFilter+1];
		      
                      }
		  }
		    else {
                      
                        // splits the work into the different threads
		    	ThreadManager left = new ThreadManager(arr,lo,(hi+lo)/2,halfFilter,finalAns);
		    	ThreadManager right= new ThreadManager(arr,(hi+lo)/2,hi,halfFilter,finalAns);
		    	
                        left.fork();
                        right.compute();
                        left.join();   
		    }
		  }

	  }


