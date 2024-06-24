package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.services.DocenteService;

@RestController
@RequestMapping("/")
public class Controller {
	
	@Autowired
	DocenteService service;
	
	@GetMapping("allDocentes")
	public Object getAllDocentes() {
		
		List<DocenteDTO> docentes = new ArrayList<>();
		
		docentes = service.getAllDocentes();
		
		if(docentes.size() > 0) {
			return docentes;
		}else {
			return "No se encontraron docentes registrados";
		}
	}
	
	@PostMapping("saveDocente")
	public String saveDocente(@RequestBody DocenteDTO docente) {
		return service.saveDocente(docente);
	}
	
	@PutMapping("updateDocente")
	public String updateDocente(@RequestBody DocenteDTO docente) {
		return service.updateDocente(docente);
	}
	
	@DeleteMapping("deleteDocente/{documento}/{tipoDocumento}")
	public String deleteDocente(@PathVariable("documento") Integer documento, @PathVariable("tipoDocumento") String tipoDocum) {
		return service.deleteDocente(documento, tipoDocum);
	}
	
}
