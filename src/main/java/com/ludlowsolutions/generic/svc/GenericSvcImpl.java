package com.ludlowsolutions.generic.svc;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.transaction.annotation.Transactional;

import com.ludlowsolutions.generic.dao.GenericDAO;

public class GenericSvcImpl<DAO_T extends GenericDAO<Entity_T, PK>, Entity_T, PK extends Serializable> 
	implements GenericSvc<DAO_T, Entity_T, PK> {
	
	protected final DAO_T dao;
	
	public GenericSvcImpl(DAO_T tDao) {
		this.dao = tDao;
	}
	
	@Override
	@Transactional
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Entity_T create(Entity_T entity)
	{
		dao.create(entity);
		
		return entity;
	}
	
	@Override
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Entity_T> getAll()
	{
		return dao.getAll();
	}
	
	@Override
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Entity_T get(@PathParam("id") PK id) {
		return dao.get(id);
	}
}
