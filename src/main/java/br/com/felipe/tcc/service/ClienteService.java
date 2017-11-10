package br.com.felipe.tcc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.tcc.dao.ClienteDAO;
import br.com.felipe.tcc.model.Cliente;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteDAO dao;
	
	public Collection<Cliente> findAll(){
		return dao.findAll();
	}
	
	public Collection<Cliente> findActive(){
		return dao.findActive();
	}
	
	public Cliente find(int id){
		Cliente c = dao.find(id);
		return c;
	}
	
	public void save(Cliente cliente){
		try {
			if (cliente.getId() == null) {
				System.out.println("Criando cliente " + cliente.getNome());
				cliente.setDataCriacao();
				cliente.setDataAlteracao();
				dao.save(cliente);
			} else {
				System.out.println("Alterando cliente " + cliente.getNome());
				cliente.setDataAlteracao();
				dao.update(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id){
		try {
			Cliente cliente = dao.find(id);
			System.out.println("Deletando cliente " + cliente.getNome());
			dao.remove(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
