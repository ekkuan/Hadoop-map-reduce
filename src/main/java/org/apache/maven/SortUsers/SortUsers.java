package org.apache.maven.SortUsers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.maven.SortMovies.idCompare;

public class SortUsers{

	public static void main(String[] args) {
		
		if(args.length != 2) {
			System.out.println("Please check input/output files.");
			System.exit(0);
		}
		String inFile = args[0];
		String outFile = args[1];
		HashMap<String, Integer> users = getUsers(inFile);
		@SuppressWarnings("unchecked")
		Map<String, Integer> map = sortByValues(users); 
		toFile(outFile, map);
	}
	
	private static void toFile(String file, Map<String,Integer> map) {
		List<String> list = new ArrayList<String>(map.keySet());
		try {
	         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	         for(int i = 0; i < 10; i++){
	        	    out.println( list.get(i) + " " + map.get(list.get(i)) );}
	         out.close();
	      } catch (IOException i) {i.printStackTrace();}
	}
	
	private static HashMap<String, Integer> getUsers(String file){
		HashMap<String, Integer> user = new HashMap<String, Integer>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line="";
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line,",");
				String id = st.nextToken().trim();
				String count = st.nextToken().trim();
				int ratingsCount = Integer.parseInt(count);
				user.put(id, ratingsCount);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static HashMap sortByValues(HashMap map) { 
	       List<String> list = new LinkedList(map.entrySet());
	       Collections.sort(list, new idCompare());
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
	
}
