package com.fdmgroup.StockAnalyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonDownloader {

	public String importJson(String u) {

		try {
			URL url = new URL(u);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine + "\n");
			}
			in.close();
			con.disconnect();

			return content.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void writeLineToFile(String fileToWrite, String content) {
		String filename = fileToWrite;
		try {
			Writer writer = new FileWriter(filename, false);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			bufferedWriter.write(content);
			bufferedWriter.newLine();

			bufferedWriter.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
