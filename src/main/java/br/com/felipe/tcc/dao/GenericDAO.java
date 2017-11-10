package br.com.felipe.tcc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

/**
 * Essa classe fornece uma implementação padrão genérica para muitas
 * funcionalidades utilizado em mecanismos de persistência. Oferece métodos
 * padrão CRUD para JPA.
 * 
 * @param <T>
 *            o tipo que será persistido (exemplo: Usuario.class)
 * @param <ID>
 *            o tipo do identificador (exemplo: Long.class)
 */

public  class GenericDAO<T, ID extends Serializable> {
	
	private Class<T> persistentClass;
	

	@PersistenceContext
	protected EntityManager entityManager;
	
	protected SimpleDateFormat sdfSql = new SimpleDateFormat("yyyy-MM-dd");

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public T find(ID id) {
		return entityManager.find(persistentClass, id);
	}

	@Transactional
	public void save(T entity) {
		entityManager.persist(entity);
	}

	@Transactional
	public T update(T entity) {
		return entityManager.merge(entity);
	}

	@Transactional
	public void remove(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	public Collection<T> findAll() {
		return entityManager.createQuery(
				" SELECT t FROM " + persistentClass.getSimpleName() + " t",
				persistentClass).getResultList();
	}
	
	public Collection<T> findInRange(int firstResult, int maxResults) {
		return entityManager
				.createQuery(
						"SELECT t FROM " + persistentClass.getSimpleName()
								+ " t", persistentClass)
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}
	
	public Collection<T> findActive() {
		return entityManager
				.createQuery(
						"SELECT t FROM " + persistentClass.getSimpleName()
								+ " t WHERE t.status = 1", persistentClass)
				.getResultList();
	}

	public Long count() {
		return (Long) entityManager.createQuery(
				"SELECT COUNT(t) FROM " + persistentClass.getSimpleName()
						+ " t").getSingleResult();
	}
	
	public void executeUpdateNativeQuery(StringBuffer nativeQuery) {
		Query query  =  entityManager.createNativeQuery( nativeQuery.toString() );
		query.executeUpdate();
	}
}
