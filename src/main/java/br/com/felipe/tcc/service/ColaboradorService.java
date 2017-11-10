package br.com.felipe.tcc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.tcc.dao.ColaboradorDAO;
import br.com.felipe.tcc.model.Colaborador;

@Service
public class ColaboradorService {
	
	@Autowired
	private ColaboradorDAO dao;
	
	public Collection<Colaborador> findAll(){
		return dao.findAll();
	}
	
	public Collection<Colaborador> findActive(){
		return dao.findActive();
	}
	
	public Colaborador find(int id){
		Colaborador c = dao.find(id);
		return c;
	}
	
	public void save(Colaborador c){
		try {
			if (c.getId() == null) {
				System.out.println("Criando colaborador " + c.getNome());
				c.setDataCriacao();
				c.setDataAlteracao();
				dao.save(c);
			} else {
				System.out.println("Alterando colaborador " + c.getNome());
				c.setDataAlteracao();
				dao.update(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id){
		try {
			Colaborador c = dao.find(id);
			System.out.println("Deletando colaborador " + c.getNome());
			dao.remove(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
