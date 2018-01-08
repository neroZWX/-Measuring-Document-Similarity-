package com;

import java.util.List;

import com.dto.Document;
import com.util.Db4oUtil;

public class Test {
	public static void main(String[] args) {
		Document doc = new Document();
		doc.setName("first");
		Document doc1 = new Document();
		doc1.setName("first3");
		Db4oUtil.save(doc1);
		
		List<Document> list = Db4oUtil.queryAll(Document.class);
		for(Document _doc : list){
			System.out.println(_doc.getName());
		}
	}
}
