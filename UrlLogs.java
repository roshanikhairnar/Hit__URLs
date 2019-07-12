/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urllogs;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.Map.Entry.comparingByValue;
import java.util.Set;
import java.util.regex.*;  
import static java.util.stream.Collectors.toMap;


/**
 *
 * @author ACER
 */
public class UrlLogs {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader reader;
        Map<String, Integer> frequency = new HashMap<>();
        try {
                reader = new BufferedReader(new FileReader( "C:\\Users\\ACER\\Downloads\\access.log"));
                String line = reader.readLine();
                String url;
                while (line != null) {
                  url=log(line.substring(line.indexOf("]") + 3));
                    // System.out.println(url);   
                        line = reader.readLine();
                        if(frequency.containsKey(url))
                        {
                            frequency.put(url, frequency.get(url) + 1);
			} else 
                        {
                            frequency.put(url, 1);
			}
                }
            Iterator<Entry<String, Integer>> itr = frequency.entrySet().iterator(); 
          
       /* while(itr.hasNext()) 
        { 
                   Entry<String, Integer> entry = itr.next(); 
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
        }   */  
        Map<String, Integer> sorted = frequency.entrySet() .stream() .sorted(comparingByValue()) .collect( toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        sorted = frequency.entrySet() .stream() .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) .collect( toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)); 
        System.out.println("map after sorting by values in descending order: " + sorted);


        reader.close();
        } catch (IOException e) {
        }
    }
    private static String log(String string) {
		return string;
 
	}
    
}
