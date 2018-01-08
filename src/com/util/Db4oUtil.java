package com.util;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Db4oUtil {
	
	//connect the database    
	private static ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "auto.yap");
	
	//save object in database
	public static void save(Object object){
		db.store(object);
	}
	
	//check class all objects
	public static <T> List<T> queryAll(Class<T> clazz){
		return db.query(clazz);
	}
	
}
