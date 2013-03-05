package configuration;

import java.io.*;
import java.util.*;


public class PlayConfig {
	private PlayType type;
	private ArrayList<String> lines = null;
	private HashMap<String, String> conf = new HashMap<String, String>();

	public PlayConfig(PlayType type) {
		this.type = type;
	}
	
	public void set(String key, String value) {
		conf.put(key, value);
	}
	
	public String get(String key) {
		return conf.get(key);
	}
	
	public ArrayList<String> returnLines() throws IOException {
		if (lines != null) {
			return lines;
		}
		lines = new ArrayList<String>();
		
		FileInputStream fstream = new FileInputStream(conf.get("fileName"));
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
        	lines.add(currentLine);
        }
		return lines;
	}
}