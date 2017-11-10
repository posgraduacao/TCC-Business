package br.com.felipe.tcc.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.tcc.dao.ChamadoDAO;
import br.com.felipe.tcc.model.Chamado;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoDAO dao;
	
	public Collection<Chamado> findAll(){
		System.out.println(new Date().toString() + " - Buscando a lista de chamados.");
		return dao.findAll();
	}
	
	public Chamado find(int id){
		Chamado c = dao.find(id);
		return c;
	}
	
	public void save(Chamado chamado){
		try {
			if (chamado.getId() == null) {
				System.out.println("Criando chamado " + chamado.getSolicitacao());
				chamado.setDataCriacao();
				chamado.setDataAlteracao();
				chamado.setDataAbertura();
				chamado.setStatus("Aberto");
				dao.save(chamado);
			} else {
				System.out.println("Alterando chamado " + chamado.getSolicitacao());
				chamado.setDataAlteracao();
				dao.update(chamado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id){
		try {
			Chamado chamado = dao.find(id);
			System.out.println("Deletando chamado " + chamado.getSolicitacao());
			dao.remove(chamado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
