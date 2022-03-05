package registrykey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Main{

    public static void main(String[] args) {
        
        String a = "abc";
        byte[] b = a.getBytes();
        
        byte[] x = {(byte)-126,(byte)-97,(byte)255};
        for (byte c : x) {
            System.out.println(c);
        }
        System.out.println(new String(x));
        String xx = "";
        for (byte y : x) {
            xx+=(char) y;
        }
        
        System.out.println(xx);
    }
//        try {
//        try {
//            String ls = WinRegistry.readString(WinRegistry.HKEY_LOCAL_MACHINE,
//                    "SOFTWARE\\","CDKey");
//            //String key = ls.stream().filter(st -> st.matches("IGI 2 Retail*")).findAny().get();
//            System.out.println(ls);
//        } catch (IllegalArgumentException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }catch (InvocationTargetException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        

//            ProcessBuilder builder = new ProcessBuilder("reg", "query",
//                    "HKEY_LOCAL_MACHINE\\SOFTWARE");
//            Process reg = builder.start();
//            try (BufferedReader output = new BufferedReader(
//                    new InputStreamReader(reg.getInputStream()))) {
//
//                Stream<String> keys = output.lines().filter(l -> !l.isEmpty());
//                Stream<String> matches = keys.filter(l -> l.contains("\\IGI"));
//                Optional<String> key = matches.findFirst();
//                System.out.println(key.get());
//                // Use key ... 
//            }
//            reg.waitFor();
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }


}
