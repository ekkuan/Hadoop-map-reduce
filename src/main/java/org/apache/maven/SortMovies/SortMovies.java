package org.apache.maven.SortMovies;

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

public class SortMovies{

	public static void main(String[] args) {
		if(args.length != 3) {
			System.out.println("Please check input/output files.");
			System.exit(0);
		}
		String inFile = args[0];
		String movieFile = args[1];
		String outFile = args[2];
		HashMap<String, Double> moviesRating = getMovieRatings(inFile);
		HashMap<String, String> movieTitles = getTitles(movieFile);
		Map<String, Double> map = sortByValues(moviesRating); 
		toFile(outFile,map,movieTitles);
	}
	
	private static HashMap<String, String> getTitles(String file) {
		HashMap<String, String> titles = new HashMap<String, String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line="";
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line,",");
				String id = st.nextToken().trim();
				String year = st.nextToken();
				year += " " + st.nextToken();
				if(st.hasMoreTokens()){
					year += "," + st.nextToken();
				}
				titles.put(id, year);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return titles;
	}
	
	private static HashMap<String, Double> getMovieRatings(String file) {
		HashMap<String, Double> ratings = new HashMap<String, Double>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line="";
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line,",");
				String id = st.nextToken().trim();
				String rating = st.nextToken().trim();
				double count = Double.parseDouble(rating);
				ratings.put(id, count);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ratings;
	}
	
	private static void toFile(String outFile, Map<String,Double> ratings, HashMap<String,String> titles) {
		List<String> list = new ArrayList<String>(ratings.keySet());
		try {
	         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
	         for(int i = 0; i < 10; i++){
	        	    out.println( list.get(i) + ", " + titles.get(list.get(i)) +", "+ ratings.get(list.get(i)) );
	        	}
	         out.close();
	      }catch(IOException e) {
	         e.printStackTrace();
	      }
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static HashMap<String, Double> sortByValues(HashMap<String,Double> map) { 
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
