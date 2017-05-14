/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import com.mxgraph.model.SolutionBuild;

import net.sf.json.JSONObject;

/**
 *
 * @author admin
 */
public class JsonOperation {
    public static void main(String args[]) {
		System.out.println(ParseJsonToObject("examples/json.json"));
	}

	public static SolutionBuild ParseJsonToObject(String path) {
		StringBuffer sb = new StringBuffer();
		File file = new File(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null)
				sb.append(tempString);
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		JSONObject jsonObject1 = JSONObject.fromObject(sb.toString());

		SolutionBuild stu2 = (SolutionBuild) JSONObject.toBean(jsonObject1, SolutionBuild.class);
		return stu2;

	}

	public static void WriteJson(Object jsonObject, String jsonPath, String path) {

		JSONObject json = JSONObject.fromObject(jsonObject);
		File f = new File(jsonPath);

		File dir = new File(path);
		if (!dir.exists()) {

			dir.mkdir();
		}
		if (f.exists()) {
			f.delete();
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter(f);
			fw.write(json.toString());
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void WriteJson(Object jsonObject, String jsonPath) {

		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(jsonObject);
		File f = new File(jsonPath);
		String path = jsonPath.substring(0, jsonPath.lastIndexOf("/"));
		File dir = new File(path);
		if (!dir.exists()) {
			if (dir.isDirectory()) {
				dir.mkdir();
			}
		}
		if (f.exists()) {
			f.delete();
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter(f);
			fw.write(json.toString());
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
