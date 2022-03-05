/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mario.mavensnifer;



import java.net.*;
import java.io.*;

import net.sourceforge.jpcap.*;

     
public  class Snifer1 {
         
        /* variables */
        
             
            public static void main(String args[]) throws IOException {
                JpcapCaptor captor = null;
        NetworkInterface[] list;
        String str,infos;
        int x, choice;
        
                 /* first fetch available interfaces to listen on */
                list = JpcapCaptor.getDeviceList();
            System.out.println("Available interfaces: ");
             
            for(x=0; x<list.length; x++) {
                 System.out.println(x+" -> "+list[x].name);  
            }
                    
           
           
           
          /*Setup device listener */
          try {
                
                captor=openDevice(a, 65535, false, 20);
             /* listen for TCP/IP only */
             //captor.setFilter("udp", true);
              }
                 catch(IOException ioe) { System.out.println(ioe); }
           
           
          /* start listening for packets */
          while (true) { 
                     
                    Packet info = captor.getPacket();
                   if(info != null)
                   {
                   String strr = new String(info.data);
                   System.out.println(strr);
                   }
                   
                 }
            }
 
        
         
        /* get user input */
        public static String getInput(String q) {
          String input = "";
          System.out.print(q);
          BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
               try {
                   input = bufferedreader.readLine();
                 }
                   catch(IOException ioexception) { }
            return input;
            }
       
      
     /* return packet data in true text */
    static String getPacketText(Packet pack){
           int i=0,j=0;
           byte[] bytes=new byte[pack.header.length + pack.data.length];
               
              System.arraycopy(pack.header, 0, bytes, 0, pack.header.length);
              System.arraycopy(pack.data, 0, bytes, pack.header.length, pack.data.length);
              StringBuffer buffer = new StringBuffer();
               
              for(i=0; i<bytes.length;) {
                  for(j=0;j<8 && i<bytes.length;j++,i++) {
                      String d = Integer.toHexString((int)(bytes [i] &0xff));
                      buffer.append((d.length() == 1 ? "0" + d:d ) + " ");
                   
                  if(bytes[i]<32 || bytes[i]>126) 
                      bytes[i] = 46;
                     }
           }
               return new String(bytes,i - j, j);
           }
} /* end class */