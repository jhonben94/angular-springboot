package com.example.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entity.Profesor;
import com.example.curso.mapper.Mapper;
import com.example.curso.model.MProfesor;
import com.example.curso.service.ProfesorServiceImpl;

@RestController
@RequestMapping("/profesor")
public class ProfesorRESTController {
	@Autowired
	private ProfesorServiceImpl profesorService;
	
	@GetMapping("/listar")
	@ResponseStatus(HttpStatus.OK)
	public List<MProfesor> getProfesores(){
		List<Profesor> profesores  =profesorService.findAll();
		List<MProfesor> profesoresMapping = Mapper.convertirLista(profesores);
		return profesoresMapping;
	}
	
	@PostMapping("/agregar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <Void> agregarProfesor(@RequestBody Profesor profesor){
		if(profesorService.findProfesorByEmail(profesor) == null) {
			profesorService.save(profesor);
			return new ResponseEntity <Void>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity <Void>(HttpStatus.CONFLICT);	
		}		
	} 
	
	@PutMapping("/modificar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <?> modificarProfesor(
			@PathVariable(value="id") Long id, 
			@RequestBody Profesor profesor){
		
		Profesor profModificado = null;
		profModificado = profesorService.findById(id);
		
		if(profModificado !=null) {
			profModificado.setEmail(profesor.getEmail());
			profModificado.setNombre(profesor.getNombre());
			profesorService.updateProfesor(profModificado);
			MProfesor prof = Mapper.convertirProfesor(profModificado);
			return new ResponseEntity <MProfesor>(prof,HttpStatus.CREATED);
		}else {
			return new ResponseEntity <Void>(HttpStatus.NOT_FOUND);	
		}	
	} 
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <Void> eliminarProfesor(
			@PathVariable(value="id") Long id){
			try {
				profesorService.deleteProfesor(id);
				return new ResponseEntity <Void>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity <Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	} 
	

	@DeleteMapping("/eliminar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <Void> eliminarProfesorES(){
			try {
				profesorService.deleteAllProfesor();
				return new ResponseEntity <Void>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity <Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	} 
	
	@PostMapping("/encontrar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <?> buscarProfesor( 
			@RequestBody Profesor profesor){
		Profesor profEncontrado= null;
		profEncontrado = profesorService.findProfesorByEmail(profesor);
		if(profEncontrado !=null) {
			MProfesor prof = Mapper.convertirProfesor(profEncontrado);
			return new ResponseEntity <MProfesor>(prof,HttpStatus.CREATED);
		}else {
			return new ResponseEntity <Void>(HttpStatus.NOT_FOUND);	
		}
	} 
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <?> login( 
			@RequestBody Profesor profesor ){
		Profesor profEncontrado= null;
		profEncontrado = profesorService.checkProfesorLogin(profesor);
		if(profEncontrado !=null) {
			MProfesor prof = Mapper.convertirProfesor(profEncontrado);
			return new ResponseEntity <MProfesor>(prof,HttpStatus.OK);
		}else {
			return new ResponseEntity <Void>(HttpStatus.NOT_FOUND);	
		}
	}
}
