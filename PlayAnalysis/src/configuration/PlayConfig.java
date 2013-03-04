package configuration;

import java.io.*;
import java.util.*;


public class PlayConfig {
<<<<<<< HEAD
	private PlayType type;
	private ArrayList<String> lines = null;
	private HashMap<String, String> conf = new HashMap<String, String>();
=======
	private static PlayType type;
	private static String fileName;
	private static ArrayList<String> lines = null;
	private static HashMap<String, String> conf = new HashMap<String, String>();
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626

	public PlayConfig(PlayType type) {
		this.type = type;
	}
	
	public void set(String key, String value) {
		conf.put(key, value);
	}
	
	public String get(String key) {
		return conf.get(key);
	}
	
<<<<<<< HEAD
	public ArrayList<String> returnLines() throws IOException {
=======
	public ArrayList<String> returnLines() throws Exception {
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
		if (lines != null) {
			return lines;
		}
		lines = new ArrayList<String>();
		
<<<<<<< HEAD
		FileInputStream fstream = new FileInputStream(conf.get("fileName"));
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
=======
		FileInputStream fstream = new FileInputStream(this.fileName);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String currentLine;
        while ((currentLine = br.readLine()) != null){
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
        	lines.add(currentLine);
        }
		return lines;
	}
}