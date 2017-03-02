package org.apache.maven.userRatings;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceClass extends Reducer<Text, IntWritable, Text, IntWritable>{

	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		Iterator<IntWritable> valuesIt = values.iterator();
		while(valuesIt.hasNext()){
			int iterations = valuesIt.next().get();
			sum += iterations;
		}
		String splitter = key.toString() + ",";
		context.write(new Text(splitter), new IntWritable(sum));
	}	
}
