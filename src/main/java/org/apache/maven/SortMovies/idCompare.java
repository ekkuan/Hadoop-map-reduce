package org.apache.maven.SortMovies;

import java.util.Comparator;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class idCompare implements Comparator{
	
	@SuppressWarnings({ "unchecked" })
	public int compare(Object o1, Object o2) {
		return ((Comparable) ((Map.Entry) (o2)).getValue())
				.compareTo(((Map.Entry) (o1)).getValue());
	}
}
