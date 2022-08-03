package com.api.projetoFinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.projetoFinal.domain.Loja;
import com.api.projetoFinal.domain.Produto;
import com.api.projetoFinal.domain.dtos.ProdutoDTO;
import com.api.projetoFinal.repositories.LojaRepository;
import com.api.projetoFinal.repositories.ProdutoRepository;
import com.api.projetoFinal.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private LojaRepository lojaRepository;
	public List<Produto> findAllProduto() {
		return repository.findAll();
	}

	public Produto findById(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
	}

	public Produto create(ProdutoDTO objDto, Integer id_loja) {
		objDto.setId(null);
		Optional<Loja> obj = lojaRepository.findById(id_loja);
		Produto newObj = new Produto(objDto);
		newObj.setLoja(obj.get());
		obj.get().getProdutos().add(newObj);
		return repository.save(newObj);
	}

	public Produto update(Integer id, ProdutoDTO objDto) {
		Produto oldObj = findById(id);

		objDto.setId(oldObj.getId());
		objDto.setLoja(oldObj.getLoja());
		objDto.setDataCriacao(oldObj.getDataCriacao());

		return repository.save(new Produto(objDto));
	}

	public List<Produto> relatorioProdutosMes(Integer mes) {
		return repository.listarProdMes(mes);
	}
	
	public List<Produto> relatorioProdutosSemana(Integer semana) {
		return repository.listarProdSemana(semana);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}
}