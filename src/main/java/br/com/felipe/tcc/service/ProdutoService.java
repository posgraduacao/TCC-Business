package br.com.felipe.tcc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.tcc.dao.ProdutoDAO;
import br.com.felipe.tcc.model.Produto;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoDAO dao;
	
	public Collection<Produto> findAll(){
		return dao.findAll();
	}
	
	public Collection<Produto> findActive(){
		return dao.findActive();
	}
	
	public Produto find(int id){
		Produto p = dao.find(id);
		return p;
	}
	
	public void save(Produto p){
		try {
			if (p.getId() == null) {
				System.out.println("Criando produto " + p.getNome());
				p.setDataCriacao();
				p.setDataAlteracao();
				dao.save(p);
			} else {
				System.out.println("Alterando produto " + p.getNome());
				p.setDataAlteracao();
				dao.update(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id){
		try {
			Produto p = dao.find(id);
			System.out.println("Deletando produto " + p.getNome());
			dao.remove(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
