package com.api.projetoFinal.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.api.projetoFinal.domain.enums.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.projetoFinal.domain.Consumidor;
import com.api.projetoFinal.domain.dtos.ConsumidorDTO;
import com.api.projetoFinal.services.ConsumidorService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "service/consumidores")
public class ConsumidorController {

	@Autowired
	private ConsumidorService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ConsumidorDTO> findById(@PathVariable Integer id) {
		Consumidor obj = service.findById(id);
		return ResponseEntity.ok().body(new ConsumidorDTO(obj));
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<Integer> findIdByEmail(@PathVariable String email) {
		Integer obj = this.service.findIdByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@PreAuthorize("hasAnyRole('ROLE_CONSUMIDOR', 'ROLE_ADMIN')")
	@GetMapping(value = "/mes/{mes}")
	public ResponseEntity<List<ConsumidorDTO>> relatorioConsumidoresMes(@PathVariable Integer mes) {
		List<Consumidor> list = service.relatorioConsumidoresMes(mes);
		List<ConsumidorDTO> listDto = list.stream().map(obj -> new ConsumidorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping
	public ResponseEntity<List<ConsumidorDTO>> findAllConsumidor() {
		List<Consumidor> list = service.findAllConsumidor();
		List<ConsumidorDTO> listDto = list.stream().map(con -> new ConsumidorDTO(con)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<ConsumidorDTO> createConsumidor(@Valid @RequestBody ConsumidorDTO objDto) {
		Consumidor newObj = service.create(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ROLE_CONSUMIDOR', 'ROLE_ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ConsumidorDTO> updateConsumidor(@PathVariable Integer id, @RequestBody ConsumidorDTO objDto) {
		Consumidor obj = service.update(id, objDto);
		return ResponseEntity.ok().body(new ConsumidorDTO(obj));
	}

	@PreAuthorize("hasAnyRole('ROLE_CONSUMIDOR', 'ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ConsumidorDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}