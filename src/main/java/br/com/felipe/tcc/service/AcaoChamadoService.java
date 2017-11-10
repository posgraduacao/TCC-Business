package br.com.felipe.tcc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.tcc.dao.AcaoChamadoDAO;
import br.com.felipe.tcc.enums.TipoAcao;
import br.com.felipe.tcc.model.AcaoChamado;
import br.com.felipe.tcc.model.Chamado;

@Service
public class AcaoChamadoService {

	@Autowired
	private AcaoChamadoDAO dao;
	
	@Autowired
	private ChamadoService chamadoService;

	public Collection<AcaoChamado> findAll() {
		return dao.findAll();
	}

	public AcaoChamado find(int id) {
		AcaoChamado a = dao.find(id);
		return a;
	}
	
	public Collection<AcaoChamado> getAcoesChamado(int id) {
		return dao.getAcoesChamado(id);
	}

	public void save(AcaoChamado acaoChamado) {
		try {
			if(acaoChamado.getTipo().equals(TipoAcao.EXECUTAR.name())){
				Chamado chamado = chamadoService.find(acaoChamado.getChamado().getId());
				chamado.setStatus("Em Execução");
				chamadoService.save(chamado);
			}
			
			if(acaoChamado.getTipo().equals(TipoAcao.IMPEDIR.name())){
				Chamado chamado = chamadoService.find(acaoChamado.getChamado().getId());
				chamado.setStatus("Impedido");
				chamadoService.save(chamado);
			}
			
			if(acaoChamado.getTipo().equals(TipoAcao.CANCELAR.name())){
				Chamado chamado = chamadoService.find(acaoChamado.getChamado().getId());
				chamado.setStatus("Cancelado");
				chamado.setDataFechamento();
				chamadoService.save(chamado);
			}
			
			if(acaoChamado.getTipo().equals(TipoAcao.FECHAR.name())){
				Chamado chamado = chamadoService.find(acaoChamado.getChamado().getId());
				chamado.setStatus("Concluído");
				chamado.setDataFechamento();
				chamadoService.save(chamado);
			}
			
			System.out.println(
					"Criando ação " + acaoChamado.getTipo() + " para o chamado " + acaoChamado.getChamado().getId());
			acaoChamado.setDataCriacao();
			acaoChamado.setDataAlteracao();
			dao.save(acaoChamado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		try {
			AcaoChamado acaoChamado = dao.find(id);
			System.out.println("Deletando a ação " + acaoChamado.getTipo() + " do o chamado " + acaoChamado.getChamado().getId());
			dao.remove(acaoChamado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
