package org.apache.maven.movieRatings;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapClass extends Mapper<LongWritable, Text, Text, Text>{
	 
    private Text word = new Text();
    
    @Override
    protected void map(LongWritable key, Text value,
    		Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		StringTokenizer st = new StringTokenizer(value.toString(),",");
		word.set(st.nextToken());
		st.nextToken();
		context.write(word, new Text(st.nextToken()));
	}
}
