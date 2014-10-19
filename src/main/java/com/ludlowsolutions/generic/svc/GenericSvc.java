package com.ludlowsolutions.generic.svc;

import java.io.Serializable;
import java.util.List;

import com.ludlowsolutions.generic.dao.GenericDAO;

public interface GenericSvc<DAO_T extends GenericDAO<Entity_T, PK>, Entity_T, PK extends Serializable> {
	
	Entity_T get(PK id);
	
	List<Entity_T> getAll();
	
	Entity_T create(Entity_T entity);
	
	/*Entity_T update(Entity_T entity);
	
	void delete(Entity_T entity);*/
}
