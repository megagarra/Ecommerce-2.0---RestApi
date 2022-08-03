package com.api.projetoFinal.services;

import com.api.projetoFinal.domain.Consumidor;
import com.api.projetoFinal.domain.Pessoa;
import com.api.projetoFinal.repositories.PessoaRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa findByEmail(String email) throws ObjectNotFoundException {
        Optional<Pessoa> obj = repository.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Pessoa> findAllPessoa() {
        return repository.findAll();
    }
}
