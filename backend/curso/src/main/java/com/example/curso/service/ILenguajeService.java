package com.example.curso.service;

import java.util.List;

import com.example.curso.entity.Lenguaje;

public interface ILenguajeService {
	public List<Lenguaje> findAll();
	public void guardarLenguaje(Lenguaje l);
	public Lenguaje buscarLenguajeId(Long id);
}
