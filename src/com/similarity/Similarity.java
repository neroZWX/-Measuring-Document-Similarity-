package com.similarity;

import java.util.Set;

public interface Similarity {
	
	public float getSimilarity(Set<String> doc1, Set<String> doc2);
}
