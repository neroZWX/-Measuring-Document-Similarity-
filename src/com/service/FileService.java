package com.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileItemStream;

import com.dto.Document;
import com.similarity.Similarity;
import com.similarity.impl.JaccardUtil;
import com.similarity.impl.MinHashUtil;
import com.util.Db4oUtil;

public class FileService {
	
	Similarity jaccardUtil = new JaccardUtil();  //jaccard index algorithm
	Similarity minHashUtil =  new MinHashUtil(); //minHash algorithm
	
	/**
	 * 1.get document contenxt，analysis document object
	 * 2.compare with in db4o database document object
	 * 3.document object save in db4o
	 * @param fis
	 * @return
	 */
	public List<Map> excute(FileItemStream fis) throws Exception{
		
		List<Map> resultList = new ArrayList<>();
		
		//get document contenxt
		StringBuffer content = new StringBuffer();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(fis.openStream())));
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			content.append(lineTxt);
		}
		
		//convert document object 
		Document newDoc = new Document();
		newDoc.setName(fis.getName());
		newDoc.setContent(content.toString());
		newDoc.setWords(new HashSet<String>(Arrays.asList(content.toString().split(" "))));
		
		//check all documents and compare
		List<Document> docList = Db4oUtil.queryAll(Document.class);
		for(Document _doc : docList){
			Map map = new HashMap();
			map.put("name", _doc.getName());
			map.put("jaccard", jaccardUtil.getSimilarity(newDoc.getWords(), _doc.getWords()));
			map.put("minHash", minHashUtil.getSimilarity(newDoc.getWords(), _doc.getWords()));
			map.put("content", _doc.getContent());
			resultList.add(map);
		}
		
		//new document save in db4o
		Db4oUtil.save(newDoc);
		
		return resultList;
	}
}
