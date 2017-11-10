package br.com.felipe.tcc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.felipe.tcc.enums.TipoAcao;

@Service
public class TipoAcaoService {
	
	public List<String> findAll(){
		List<String> tipos = new ArrayList<>();
		for(TipoAcao tAcao : TipoAcao.values()) {
			tipos.add(tAcao.name());
		}
		return tipos;
	}
	
}
