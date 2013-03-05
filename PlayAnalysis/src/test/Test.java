package test;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import play.Play;
import configuration.PlayConfig;
import configuration.PlayType;

public class Test {
	
	public static void main(String [] args) throws IOException {
		PlayConfig config = new PlayConfig(PlayType.DEFAULT);
		if (args[0].length() == 0) {
			return;
		}
		config.set("fileName", args[0]);
		Play play = new Play(config);
		
		// Construct the features
		play.constructFeatures();
	}
}