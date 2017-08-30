/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2002s_assignment1;

import java.io.File;
import java.io.FileNotFoundException;
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
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        
        //get file as input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println("this"+input);
        String[] fileName = input.split(" ");
        
        File f = new File(fileName[0]);
        Scanner file = new Scanner(""+f+ "");
        String outputFile = fileName[2];
        
        //output file
        PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
        //writer.println("The first line");
        //writer.println("The second line");
        //writer.close();
        
        // useful values
        int filterSize = Integer.parseInt(fileName[1]);
        int halfFilter = (int)((filterSize/2) - 0.5);
        int fileSize = Integer.parseInt(file.nextLine());
        
        String[] filtered = new String[filterSize];
        
        // print first half to the file
        // then add the rest of the filter size to the list, but only write in the while loop
        
        // move the file ahead FULLFILTERSIZE length so boundaries overextended
        for (int i = 0; i < (halfFilter); i++){
            String[] current = file.nextLine().split(" ");
            writer.println(current);
            filtered[i] = current[1];
        }
        
        // adds to the list filtered without printing to the file with writer
        for (int i = 0; i < (halfFilter + 1); i++){
            String[] current = file.nextLine().split(" ");
            filtered[i + halfFilter] = current[1];
        }
        
        while(file.hasNext()){
            String current = file.nextLine();
            String[] currentList = current.split(" ");
            int currentNum = Integer.parseInt(currentList[0]);
            
            Arrays.sort(filtered);
            filtered[0] = current;
            
            int[] values = new int[filterSize];
            
            // for the end of the data string, print the values as is and then break
            if (currentNum >= (fileSize-halfFilter)){
                //for (int j = 0; j<halfFilter){
                  //  System.out.println("");
                  writer.print(current);
                }
            
            else{
                // get each value, sort, take mid value//..... currentlist.sort()
                for (int j = 0; j < filterSize; j++){
                    currentList = filtered[j].split(" ");
                    
                    values[j] = Integer.parseInt(currentList[1]);
                }
                Arrays.sort(values);
                writer.print(currentNum + " " + values[halfFilter + 1]);
            }
        }
        writer.close();
    }
    
}
