/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2002s_assignment1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *Program which balances a list of numbers from a text file and writes the
 * balanced list without any extreme values without using threads
 * @author moegamat
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
        
        
        FileWriter nf = new FileWriter(fileName[2]);
        String outputFile = fileName[2];
        //output file
     //   PrintWriter writer = new PrintWriter(new File(outputFile));
        //writer.println("The first line");
        //writer.println("The second line");
        //writer.close();
        BufferedWriter bw = new BufferedWriter(nf);
        
        
        // useful values
        int filterSize = Integer.parseInt(fileName[1]);
        int halfFilter = (int)((filterSize/2) - 0.5);
        int fileSize = Integer.parseInt(file.nextLine());
        float[] filtered = new float[filterSize];
        int printNum = halfFilter + 1;
        
        
        // print the first half of the filtersize to the file
        bw.write(fileSize);
        for (int i = 0; i < (halfFilter); i++){
            
            String[] current = file.nextLine().split(" ");
    //        writer.println(Arrays.toString(current));
            bw.write((i+1) + " " + Arrays.toString(current));
            filtered[i] = Float.valueOf(current[1]);  
        }
        
        // adds to the list filtered without printing to the file with writer
        for (int i = 0; i < (halfFilter + 1); i++){
            
            String[] current = file.nextLine().split(" ");
            filtered[i + halfFilter] = Float.valueOf(current[1]);
        }
        
        
        int counter = 0;
        long startTime = System.currentTimeMillis();
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
            
            
            // prints the end border of the array
            if (currentNum >= (fileSize-halfFilter)){
                
     //         writer.print(current);
                bw.write(printNum + " " + current);
                printNum++;
                //System.out.println(current);
                
                for (int j = 0; j < halfFilter; j++){
                    
                  String newCurrent = file.nextLine();
    //              writer.print(newCurrent);
                  bw.write(printNum + " " + newCurrent);
                  //System.out.println(newCurrent);
                  printNum++;
                }
                break;
            }
            
            //prints the median of the sorted filtersize of values
            else{
                
    //            writer.print(sorted[halfFilter]);
                bw.write(printNum + " " + sorted[halfFilter]+"");
                //System.out.println(sorted[halfFilter]);
                printNum++;
            }

            counter++;
        }
        String totTime = ""+((System.currentTimeMillis() - startTime) / 1000.0f);
        System.out.println(totTime);
        
    //    writer.close();
        bw.close();
        nf.close();
    }
    
}
