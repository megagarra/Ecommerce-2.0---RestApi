package com.api.projetoFinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.projetoFinal.domain.Empreendedor;
import com.api.projetoFinal.domain.Loja;
import com.api.projetoFinal.domain.dtos.LojaDTO;
import com.api.projetoFinal.repositories.EmpreendedorRepository;
import com.api.projetoFinal.repositories.LojaRepository;
import com.api.projetoFinal.services.exceptions.ObjectNotFoundException;

@Service
public class LojaService {
	@Autowired
	private EmpreendedorRepository empRepository;
	@Autowired
	private LojaRepository repository;

	public Loja findById(Integer id) {
		Optional<Loja> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado: " + id));
	}

	public List<Loja> findAllLojas() {
		return repository.findAll();
	}

	public Integer findLojaIDByEmail(String email) {
		return repository.findLojaIDByEmail(email);
	}

	public Loja findLojaByEmail(String email) {
		Optional<Loja> obj = repository.findLojaByEmail(email);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado: " + email));
	}

	public Loja create(LojaDTO objDto, Integer idEmpreendedor) {
		objDto.setIdLoja(idEmpreendedor);
		Optional<Empreendedor> obj = empRepository.findById(idEmpreendedor);
		objDto.setEmpreendedor(obj.get());

		Loja newObj = new Loja(objDto);
		return repository.save(newObj);
	}

	public Loja update(Integer id, LojaDTO objDto) {

		Loja oldObj = findById(id);

		objDto.setIdLoja(oldObj.getIdLoja());
		objDto.setEmpreendedor(oldObj.getEmpreendedor());

		return repository.save(new Loja(objDto));
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
