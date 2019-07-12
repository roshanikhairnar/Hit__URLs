/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import static java.util.Map.Entry.comparingByValue;
import java.util.Set;
import java.util.regex.*;  
import static java.util.stream.Collectors.toMap;


/**
 *
 * @author ACER
 */
public class Hits_Urls
 {

	/**
	 * @param args the command line arguments
	 * @throws java.io.FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		BufferedReader reader;
		Map<String, Integer> frequency = new HashMap<>();
		Map<String, String> returncode = new HashMap<>();
		//Properties properties=new Properties();
		try {
			reader = new BufferedReader(new FileReader( "/home/roshani/Downloads/access.log"));
			String line = reader.readLine();
			//String url="";
			String url,url2;
			//int length1=0;
			//String Return_status="";
			while (line != null) {
				url=new String(line);
				//url=url.substring(url.indexOf("/")+1, url.indexOf("?"));
				Pattern p=Pattern.compile("\"([^\"]*)\"");
				Matcher m=p.matcher(line);
				while(m.find())
				{
					url=m.group(1);
					// length1=url.length();
					//line = reader.readLine();
					break;
				}

				line = reader.readLine();
				if(frequency.containsKey(url))
				{
					frequency.put(url, frequency.get(url) + 1);
				} else 
				{
					frequency.put(url, 1);
				}
			}
			//printing hit urls	


			Map<String, Integer> sorted = frequency.entrySet() .stream() .sorted(comparingByValue()) .collect( toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
			sorted = frequency.entrySet() .stream() .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) .collect( toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)); 
			System.out.println("map after sorting by values in descending order: " + sorted);
			//printing return status code

			reader.close();
		} 
		catch (IOException e) {
		}
	}






}





//
//url=log(line.substring(line.indexOf("]") + 3));
// System.out.println(url);  

/*Iterator<Entry<String, String>> itr = returncode.entrySet().iterator(); 

while(itr.hasNext()) 
{ 
	Entry<String, S> entry = itr.next(); 
	System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
} */  



/*String[] arrsplit=line.split("\\s");
for(int i=0;i<arrsplit.length;i++)
{
	Return_status=arrsplit[8];
}
returncode.put(url, Return_status);
for(Map.Entry<String, String>entry:returncode.entrySet()) {
	properties.put(entry.getKey(), entry.getValue());
}
properties.store(new FileOutputStream("/home/roshani/Desktop/access_logs/returncode.txt"), null);
 */
