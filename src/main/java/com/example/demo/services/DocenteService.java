package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.DocenteRepository;
import com.example.demo.DAO.*;
import com.example.demo.DTO.DocenteDTO;
@Service
public class DocenteService {
	
	@Autowired
	DocenteRepository repository;
	@Autowired
    private ModelMapper modelMapper;
	
	public List<DocenteDTO> getAllDocentes(){
		
		List<DocenteDAO> allDocentes = new ArrayList<>();
		//Se llena la lista de docentes con el metodo findAll del repositorio (actua como un SELECT * de SQL)
		allDocentes = repository.findAll();
		List<DocenteDTO> respuesta = new ArrayList<>();
		
		//Se valida que la lista de docentes este llena para poder empezar a recorrerla
		if(allDocentes.size() > 0) {
			for(DocenteDAO docente : allDocentes) {
				DocenteDTO docenteDTO = new DocenteDTO(); //Se crea un objeto temporal para guardar cada docente y luego incluirlo en la lista de docentes a devolver
				modelMapper.map(docente, docenteDTO);
				respuesta.add(docenteDTO);
			}
		}
		
		return respuesta;
	}

	public String saveDocente(DocenteDTO docente) {
		
		if(docente.numeroDocumento == null) {
			return "El numero de documento no puede estar vacio.";
		}
		
		if(docente.tipoDocumento.isEmpty()) {
			return "Debe indicar un tipo de documento";
		}
		
		if(docente.nombres.isEmpty()) {
			return "El nombre del docente no puede estar vacio.";
		}
		
		if(docente.apellidos.isEmpty()) {
			return "El apellido del docente no puede estar vacio.";
		}
		
		if(docente.asignatura.isEmpty()) {
			return "El docente debe tener una asignatura.";
		}
		
		DocenteDAO docenteEncontrado = repository.findByDocum(docente.numeroDocumento, docente.tipoDocumento);
		
		if(docenteEncontrado != null) {
			if( docenteEncontrado.getNumeroDocumento() > 0) {
				return "El numero de documento ya esta registrado.";
			}
		}
		
		DocenteDAO docenteGuardar = modelMapper.map(docente, DocenteDAO.class);
		
		repository.save(docenteGuardar);
		
		return "Docente guardado con exito";
	}
	
	public String updateDocente(DocenteDTO docente) {
		
		// Validaciones
		if(docente.numeroDocumento == null) {
			return "El numero de documento no puede estar vacio.";
		}
		
		if(docente.tipoDocumento.isEmpty()) {
			return "Debe indicar un tipo de documento";
		}
		
		if(docente.nombres.isEmpty()) {
			return "El nombre del docente no puede estar vacio.";
		}
		
		if(docente.apellidos.isEmpty()) {
			return "El apellido del docente no puede estar vacio.";
		}
		
		if(docente.asignatura.isEmpty()) {
			return "El docente debe tener una asignatura.";
		}
		//-------------------------------------------------------------
		
		DocenteDAO docenteEncontrado = repository.findByDocum(docente.numeroDocumento, docente.tipoDocumento);
		if(docenteEncontrado == null) {
			return "El docente a actualizar no se encuentra registrado";
		}
		
		DocenteDAO docenteActualizado = modelMapper.map(docente, DocenteDAO.class);
		
		docenteActualizado.setId(docenteEncontrado.getId());
		
		repository.save(docenteActualizado);
		
		return "docente actualizado con exito";
	}
	
	public String deleteDocente(Integer documento, String tipoDoc) {
		DocenteDAO docenteEncontrado = repository.findByDocum(documento,tipoDoc);
		if(docenteEncontrado == null) {
			return "El docente a eliminar no se encuentra registrado";
		}
		
		repository.delete(docenteEncontrado);
		
		return "Docente eliminado correctamente";
	}
	
	
}
