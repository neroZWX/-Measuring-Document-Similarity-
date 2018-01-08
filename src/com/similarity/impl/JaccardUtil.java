package com.similarity.impl;

import java.util.Set;

import com.similarity.Similarity;

public class JaccardUtil implements Similarity{
	
	@Override
	public float getSimilarity(Set<String> doc1, Set<String> doc2){
		
		int doc1Count = doc1.size();
		int doc2Count = doc2.size();
		
		//calculate doc1 and doc2 intersection
		doc1.retainAll(doc2);
		int intersectionCount = doc1.size();
		
		return (float)intersectionCount/(doc1Count+doc2Count-intersectionCount);
	}
}
