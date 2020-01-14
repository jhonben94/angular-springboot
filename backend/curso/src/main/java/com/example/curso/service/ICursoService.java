package com.example.curso.service;

import java.util.List;

import com.example.curso.entity.Curso;

public interface ICursoService {
	public List<Curso> findAll();
	public void guardarCurso(Curso c);
	public void modificarCurso(Curso c);
	public void eliminarCurso(Curso c);
	public Curso findByNombreCurso(String nombreCurso);
	public List<Curso> buscarPorProfesor(Long id);
	public Curso findByCursoId(Long id);
	public void deleteCurso(Curso c);


}
