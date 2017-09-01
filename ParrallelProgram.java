package cs2002s_assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Program which balances a list of numbers from a text file and writes the
 * balanced list without any extreme values using threads
 * @author moegamat
 */
public class ParrallelProgram {
 
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException{
        
        
        //get file as input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] fileName = input.split(" ");
        
        File f = new File(fileName[0]);
        Scanner file = new Scanner(f);
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
        float[] filtered = new float[filterSize];
        
        // print first half to the file
        // then add the rest of the filter size to the list, but only write in the while loop
        
        // move the file ahead FULLFILTERSIZE length so boundaries overextended
        
        
        
        // print the first half of the filtersize to the file
        for (int i = 0; i < (halfFilter); i++){
            
            String[] current = file.nextLine().split(" ");
            writer.println(Arrays.toString(current));
            filtered[i] = Float.valueOf(current[1]);
            
        }
        
        // adds to the list filtered without printing to the file with writer
        for (int i = 0; i < (halfFilter + 1); i++){
            String[] current = file.nextLine().split(" ");
            filtered[i + halfFilter] = Float.valueOf(current[1]);
        }
        
        
        
        
        //add starting values to the printed file
        
        //send the array to the threads
        //change sum arrays code so that it takes values in the filtersize, then sorts them, then places the middle value down, then adds all the different threads doing this together
        
        //each thread does their calculations
        
        //add ending values to the printed file
    }
}
