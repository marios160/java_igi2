
package getobjectid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mateusz
 */
public class GetObjectID {

      
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> listid = new ArrayList<>();
        int [] ids = new int[4096];
        File obj = new File("objects.qsc");
        if(!obj.exists()){
            System.out.println("Nie ma pliku");
            return;
        }
        Scanner file = new Scanner(obj);
        String linia = null;
        while(file.hasNextLine()){
            linia = file.nextLine();
            int pos = linia.indexOf("Task_New(");
            if(pos>-1){
                String id = linia.substring(pos+9,linia.indexOf(",",pos+9));
                listid.add(Integer.parseInt(id));
                ids[Integer.parseInt(id)] = Integer.parseInt(id);
                
            }
        }
        file.close();
        int max = 1, min = 4096;
        for (Integer integer : listid) {
            System.out.print(integer+", ");
            if (max==15){
                max=0;
                System.out.println("");
            }
            max++;
        }
//        for (int i=0;i<4096; i++) {
////            if(integer > max)
////                max = integer;
////            if (integer<min)
////                min = integer;
//            //if(ids[i] > 0)
//                System.out.println(ids[i]);
//        }
        
       /* System.out.println("");
        System.out.println(min+" "+max);
        System.out.println(listid.size());*/
        
    }
    
}
