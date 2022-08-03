package com.api.projetoFinal.controllers;

import com.api.projetoFinal.domain.Consumidor;
import com.api.projetoFinal.domain.Pessoa;
import com.api.projetoFinal.domain.dtos.ConsumidorDTO;
import com.api.projetoFinal.domain.dtos.PessoaDTO;
import com.api.projetoFinal.services.PessoaService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "service/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService service;
    
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAllPessoa() {
        List<Pessoa> list = service.findAllPessoa();
        List<PessoaDTO> listDto = list.stream().map(pes -> new PessoaDTO(pes)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<Pessoa> findByEmail(@PathVariable String email) throws ObjectNotFoundException {
        Pessoa obj = service.findByEmail(email);
        return ResponseEntity.ok().body(new PessoaDTO(obj));
    }
}
