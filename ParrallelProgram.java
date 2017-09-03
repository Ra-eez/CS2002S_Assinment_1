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
 * @author Moegamat Ra-eez Stenekamp
 * August 2017
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
        
        // useful values
        int filterSize = Integer.parseInt(fileName[1]);
        int halfFilter = (int)((filterSize-1)/2);
        int fileSize = Integer.parseInt(file.nextLine());
        float[] list = new float[fileSize];
        OrganisedArr finalAns = new OrganisedArr(fileSize);
        
        // sets list and ans(from OrganisedArr) to the values of the given array
        for (int i = 0; i < fileSize; i++){
            
            String[] currentLine = file.nextLine().split(" ");
            float current = Float.valueOf(currentLine[1]);
            list[i] = current;
            finalAns.ans[i] = current;
        }
        
        // creates, runs and times the computation and thread making process
        ThreadManager threads = new  ThreadManager(list, halfFilter, fileSize - halfFilter, halfFilter, finalAns);
        long startTime = System.currentTimeMillis(); // start timer
        threads.compute();
        String totTime = ""+((System.currentTimeMillis() - startTime) / 1000.0f); // stop timer
        System.out.println(totTime);
        
        writer.println(fileSize);
        
        // write the answers to the file
        for (int i = 0; i < fileSize; i++){
            
            writer.println((i+1)+" "+finalAns.ans[i]);
        }
        
        writer.close();
    }
}
