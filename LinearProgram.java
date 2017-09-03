/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2002s_assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *Program which balances a list of numbers from a text file and writes the
 * balanced list without any extreme values without using threads
 * @author Moegamat Ra-eez Stenekamp
 * August 2017
 */
public class LinearProgram {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        
        //get file as input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] fileName = input.split(" ");
        
        File f = new File(fileName[0]);
        Scanner file = new Scanner(f);
        String outputFile = fileName[2];
        
        
        //output file
        PrintWriter writer = new PrintWriter(new File(outputFile));
        
        
        // useful values
        int filterSize = Integer.parseInt(fileName[1]);
        int halfFilter = (int)((filterSize/2) - 0.5);
        int fileSize = Integer.parseInt(file.nextLine());
        float[] filtered = new float[filterSize];
        String[] ans = new String[fileSize]; 
        int p = 0;
        int k = 1;
        
        writer.println(fileSize);
        
        // add the first half of the filtersize to the ans
        for (int i = 0; i < (halfFilter); i++){
            
            String[] current = file.nextLine().split(" ");
            ans[p] = k + " " + current[1];
            p++;
            k++;
            filtered[i] = Float.valueOf(current[1]);  
        }
        
        // adds to the list filtered without adding to ans
        for (int i = 0; i < (halfFilter + 1); i++){
            
            String[] current = file.nextLine().split(" ");
            filtered[i + halfFilter] = Float.valueOf(current[1]);
        }
        
        
        int counter = 0;
        long startTime = System.currentTimeMillis(); //start timer
        
        // loop that does all the calculations to find the medians
        while(file.hasNext()){
            
            if (counter == filterSize){
                counter = 0;
            }
            
            String current = file.nextLine();
            String[] currentList = current.split(" ");
            int currentNum = Integer.parseInt(currentList[0]);
            
            // replaces the oldest value in the filterSize with the latest
            filtered[counter] = Float.valueOf(currentList[1]);
            
            // sort the values withing the filtersize
            float[] sorted = filtered;
            Arrays.sort(sorted);
            
            
            // adds the end border of the array to ans
            if (currentNum >= (fileSize-halfFilter)){
                
                ans[p] = k + " " + currentList[1];
                p++;
                k++;
                
                for (int j = 0; j < halfFilter; j++){
                    
                  String newCurrent = file.nextLine();
                  String[] newCurrentL = newCurrent.split(" ");
                  ans[p] = k + " " + newCurrentL[1];
                  p++;
                  k++;
                }
                break;
            }
            
            // adds the median of the sorted filtersize of values to ans
            else{
                
                ans[p] = k + " " + sorted[halfFilter];
                p++;
                k++;
            }

            counter++;
        }
        
        String totTime = ""+((System.currentTimeMillis() - startTime) / 1000.0f);// end timer
        System.out.println(totTime);
        
        // write the new filtered array to the file
        for (int i = 0; i < fileSize; i++){
            
            writer.println(ans[i]);
        }
        
        writer.close();
    }
    
}
