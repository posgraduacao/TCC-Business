package br.com.felipe.tcc.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.felipe.tcc.model.AcaoChamado;

@Repository
public class AcaoChamadoDAO extends GenericDAO<AcaoChamado, Serializable> {

	public Collection<AcaoChamado> getAcoesChamado(int id) {
		TypedQuery<AcaoChamado> query = entityManager.createQuery("SELECT a FROM AcaoChamado a WHERE a.chamado.id = :idChamado", AcaoChamado.class);
		return query.setParameter("idChamado", id).getResultList();
	}

}
