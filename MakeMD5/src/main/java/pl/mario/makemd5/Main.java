package pl.mario.makemd5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz
 */
public class Main {
    
   public static void main(String args[]) throws IOException{
        Scanner file = new Scanner(new File("checksum.txt"));
        int size = Integer.parseInt(file.nextLine());
        String [] tab = new String[size];
        String path;
        for(int i=0; i<size; i++){
            path = file.nextLine();
            FileInputStream fis = new FileInputStream(new File(path));
            String md5 = DigestUtils.md5Hex(fis);
            tab[i] = path+"("+md5+")";
            fis.close();
            
        }
        file.close();
        PrintWriter checksum = new PrintWriter(new File("checksum.txt"));
        checksum.append(size+"\n");
        for(String el : tab){
            checksum.append(el+"\n");
        }
        checksum.close();
   }
    
}