/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author admin
 */
public class FileOperate {

    /**
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static  String BufferedReaderDemo(String path) throws FileNotFoundException, IOException {
         File file=new File(path);
         if(!file.exists()||file.isDirectory())
             throw new FileNotFoundException();
         BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));  
         String temp=null;
         StringBuffer sb=new StringBuffer();
         temp=br.readLine();
         while(temp!=null){
             sb.append(temp+" ");
             temp=br.readLine();
         }
         return sb.toString();
     }
}
