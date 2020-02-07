package com.example.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entity.Lenguaje;
import com.example.curso.service.LenguajeServiceImpl;

@RestController
@RequestMapping("/lenguaje")
public class LenguajeRESTController {

	@Autowired
	private LenguajeServiceImpl lenguajeService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarLenguajes(){
		List<Lenguaje> lenguajes = lenguajeService.findAll();
		if(lenguajes!=null && lenguajes.size()>0) {
			return new ResponseEntity<>(lenguajes, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> crearLenguaje(@RequestBody Lenguaje lenguaje) {
		try {
			lenguajeService.guardarLenguaje(lenguaje);
		} catch (Exception e) {
			return new ResponseEntity <Void>(HttpStatus.CONFLICT);	
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
