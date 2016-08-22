package com.shark.common.weixin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName: HttpClient
 * @Description: HTTP
 */
public class HttpClient {
	private static final String ENCODING = "UTF-8";

	public static String get(String location) {
		HttpURLConnection conn = null;
		InputStream in = null;
		BufferedReader br = null;
		try {
			URL url = new URL(location);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
	        conn.setRequestProperty("contentType", ENCODING);  
			in = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(in, ENCODING));
			String lines;
			StringBuffer sb = new StringBuffer();
			while ((lines = br.readLine()) != null)
				sb.append(lines);
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null)
				conn.disconnect();
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	public enum ContentType {
		JSON("application/json; charset=UTF-8"), TEXT("application/text; charset=UTF-8"), XML("text/xml; charset=UTF-8");
		private String context;

		public String getContext() {
			return this.context;
		}

		private ContentType(String context) {
			this.context = context;
		}
	}

	public static String post(String location, String data, ContentType type) {
		HttpURLConnection conn = null;
		PrintWriter out = null;
		BufferedReader br = null;
		try {
			URL url = new URL(location);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type", type.getContext());
			conn.connect();
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), ENCODING));
			out.write(data);
			out.flush();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), ENCODING));
			String lines;
			StringBuffer sb = new StringBuffer();
			while ((lines = br.readLine()) != null)
				sb.append(lines);
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null)
				conn.disconnect();
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

}