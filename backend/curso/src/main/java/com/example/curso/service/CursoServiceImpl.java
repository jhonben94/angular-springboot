package com.example.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.curso.dao.ICursosDAO;
import com.example.curso.entity.Curso;

@Service
public class CursoServiceImpl implements ICursoService{
	
	
	@Autowired
	private ICursosDAO cursoDao;

	@Override
	@Transactional( readOnly= true)
	public List<Curso> findAll() {
		return (List<Curso>) cursoDao.findAll();
	}

	@Override
	@Transactional
	public void guardarCurso(Curso c) {
		cursoDao.save(c);
	}

	@Override
	@Transactional
	public void modificarCurso(Curso c) {
		cursoDao.save(c);
	}

	@Override
	public void eliminarCurso(Curso c) {
		cursoDao.delete(c);
	}

	@Override
	@Transactional( readOnly= true)
	public List<Curso> buscarPorProfesor(Long id) {
		return (List<Curso>) cursoDao.findByProfesorId(id);
	}

	@Override
	public Curso findByNombreCurso(String nombreCurso) {
		return cursoDao.findByNombreCurso(nombreCurso);
	}

	@Override
	public Curso findByCursoId(Long id) {
		return cursoDao.findByCursoId(id);
	}

	@Override
	public void deleteCurso(Curso c) {
		cursoDao.delete(c);
	}

}
