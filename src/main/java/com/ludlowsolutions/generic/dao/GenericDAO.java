package com.ludlowsolutions.generic.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, PK extends Serializable> {
	
	T get(PK id);
	
	List<T> getAll();
	
	T create(T entity);
	
	T update(T entity);
	
	void delete(T entity);
	
}
