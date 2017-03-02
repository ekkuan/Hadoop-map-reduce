package org.apache.maven.movieRatings;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceClass extends Reducer<Text, Text, Text, Text> {
	
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		double rate = 0.0;
		int count = 0;
		String line;
		Iterator<Text> i = values.iterator();
		while(i.hasNext()){
			line = i.next().toString();
			StringTokenizer itr = new StringTokenizer(line,",");
			rate += Double.parseDouble(itr.nextToken());
			count++;
		}
		rate /= count;
		DecimalFormat df = new DecimalFormat("0.##");
		String movieInfo = ",";
		movieInfo  += df.format(rate);
		Text movieText = new Text(movieInfo);
		context.write(key, movieText);
	}
}
