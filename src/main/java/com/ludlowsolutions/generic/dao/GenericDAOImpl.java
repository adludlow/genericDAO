package com.ludlowsolutions.generic.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDAOImpl<T, PK extends Serializable> 
	implements GenericDAO<T, PK>{
	
	protected Class<T> entityClass;
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public GenericDAOImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
    }
 
    @Override
    public T get(PK id) {
    	return this.entityManager.find(entityClass, id);
    }
    
    @Override
    public List<T> getAll() {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.select(root);
		return entityManager.createQuery(cq).getResultList();
    }
	
    @Override
    @Transactional
	public T create(T entity) {
		this.entityManager.persist(entity);
		return entity;
	}
	
    @Override
    @Transactional
	public T update(T entity) {
		return this.entityManager.merge(entity);
	}
	
    @Override
    @Transactional
	public void delete(T entity) {
		// Convert to managed object
		entity = this.entityManager.merge(entity);
		this.entityManager.remove(entity);
	}
    
}
