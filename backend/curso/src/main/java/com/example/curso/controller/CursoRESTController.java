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

import com.example.curso.entity.Curso;
import com.example.curso.entity.Profesor;
import com.example.curso.mapper.Mapper;
import com.example.curso.model.MProfesor;
import com.example.curso.service.CursoServiceImpl;
import com.example.curso.service.ProfesorServiceImpl;

@RestController
@RequestMapping("/curso")
public class CursoRESTController {
	
	@Autowired
	private ProfesorServiceImpl profesorService;

	@Autowired
	private CursoServiceImpl cursoService;
	
	@GetMapping("/listar")
	@ResponseStatus(HttpStatus.OK)
	public List<Curso> getCursos(){
		List<Curso> cursos  = cursoService.findAll();
		return cursos;
	}
	
	@PostMapping("/agregar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <Void> agregarProfesor(@RequestBody Curso curso){
		if(cursoService.findByNombreCurso(curso.getNombreCurso())== null) {
			if(profesorService.findById(curso.getProfesorId()) == null) {
				return new ResponseEntity <Void>(HttpStatus.CONFLICT);
			}
			cursoService.guardarCurso(curso);
			return new ResponseEntity <Void>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity <Void>(HttpStatus.CONFLICT);	
		}		
	} 
	
	@PutMapping("/modificar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <?> modificarProfesor(
			@PathVariable(value="id") Long id, 
			@RequestBody Curso curso){
		
		Curso cModificado = null;
		cModificado = cursoService.findByCursoId(id);
		if(cModificado !=null) {
			cModificado.setNombreCurso( curso.getNombreCurso());;
			cursoService.modificarCurso(cModificado);
			return new ResponseEntity <Curso>(cModificado,HttpStatus.CREATED);
		}else {
			return new ResponseEntity <Void>(HttpStatus.NOT_FOUND);	
		}	
	} 
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <Void> eliminarProfesor(
			@PathVariable(value="id") Long id){
			try {
				Curso c = new Curso();
				c.setCursoId(id);
				cursoService.eliminarCurso(c);
				return new ResponseEntity <Void>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity <Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	} 
	
	@PostMapping("/buscar")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <?> buscarCurso( 
			@RequestBody Curso curso){
		Curso cursoEncontrado= null;
		cursoEncontrado = cursoService.findByCursoId(curso.getCursoId());
		if(cursoEncontrado !=null) {
			return new ResponseEntity <Curso>(cursoEncontrado,HttpStatus.CREATED);
		}else {
			return new ResponseEntity <Void>(HttpStatus.NOT_FOUND);	
		}
	} 
	
	@PostMapping("/buscar/profesor")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <?> buscarCursoProfesor( 
			@RequestBody Profesor profesor){
		List<Curso> cursosEncontrados= null;
		cursosEncontrados = cursoService.buscarPorProfesor(profesor.getId());
		if(cursosEncontrados !=null) {
			return new ResponseEntity <List<Curso>>(cursosEncontrados,HttpStatus.CREATED);
		}else {
			return new ResponseEntity <Void>(HttpStatus.NOT_FOUND);	
		}
	} 

}
