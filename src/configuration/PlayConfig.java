package configuration;

import java.io.*;
import java.util.*;


public class PlayConfig {
	private static PlayType type;
	private static String fileName;
	private static ArrayList<String> lines = null;
	private static HashMap<String, String> conf = new HashMap<String, String>();

	public PlayConfig(PlayType type) {
		this.type = type;
	}
	
	public void set(String key, String value) {
		conf.put(key, value);
	}
	
	public String get(String key) {
		return conf.get(key);
	}
	
	public ArrayList<String> returnLines() throws Exception {
		if (lines != null) {
			return lines;
		}
		lines = new ArrayList<String>();
		
		FileInputStream fstream = new FileInputStream(this.fileName);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String currentLine;
        while ((currentLine = br.readLine()) != null){
        	lines.add(currentLine);
        }
		return lines;
	}
}