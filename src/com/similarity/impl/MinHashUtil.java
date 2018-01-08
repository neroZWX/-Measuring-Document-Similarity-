package com.similarity.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.similarity.Similarity;

public class MinHashUtil implements Similarity{
	
	private int k = 10;
	private Random random = new Random();
	
	@Override
	public float getSimilarity(Set<String> doc1, Set<String> doc2){
		
		//save doc1 the less hash assemble
		Set<Integer> result1 = new TreeSet<Integer>();
		//save doc2 the less hash assemble
		Set<Integer> result2 = new TreeSet<Integer>();
		
		//Get k random numbers，hash calculate
		Set<Integer> hashes = createHashes();
		
		//calculate doc1、doc2 the less hash assemble，save in result1、result2 respectively
		for (Integer hash : hashes){
			result1.add(getMinHash(doc1, hash));
			result2.add(getMinHash(doc2, hash));
		}
		
		//calculate the intersection ,save in result1
		result1.retainAll(result2);
		
		return (float) result1.size()/ k;
	}
	
	//Create the set of hash integers as random numbers
	private Set createHashes(){
		Set<Integer> hashes = new TreeSet<>();
		for(int i=0; i<k; i++){
			hashes.add(random.nextInt());
		}
		return hashes;
	}
	
	/**
	 * calculate hash functions minimum value
	 * @param doc
	 * @param hash
	 * @return
	 */
	private Integer getMinHash(Set<String> doc, Integer hash){
		int min = Integer.MAX_VALUE; 
		for (String word : doc){
			int minHash = word.hashCode() ^ hash; 
			if (minHash < min){
				min = minHash;
			}
		}
		return min;
	}
	
	public static void main(String[] args) {
		Set<String> set1 = new HashSet<String>(){{
			add("John");
			add("Paul");
			add("Anne");
			add("Pat");
			add("Emer");
			add("Una");
			add("Enda");
		}};
		
		Set<String> set2 = new HashSet<String>(){{
			add("Peter");
			add("Anne");
			add("Francis");
			add("Enda");
			add("Joe");
			add("Alan");
			add("Padraig");
			add("Una");
		}};
		
		//System.out.println(MinHashUtil.getSimilarity(set1, set2));
	}

	
	
}
