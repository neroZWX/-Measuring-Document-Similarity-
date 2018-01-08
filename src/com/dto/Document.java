package com.dto;

import java.util.Set;

public class Document {
	
	//document name
	private String name;
	//document word
	private Set<String> words;
	//document content
	private String content;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getWords() {
		return words;
	}
	public void setWords(Set<String> words) {
		this.words = words;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
