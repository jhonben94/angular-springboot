package com.example.curso.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.curso.dao.IProfesorDAO;
import com.example.curso.entity.Profesor;

@Service
public class ProfesorServiceImpl implements IProfesorService{
	
	@Autowired
	private IProfesorDAO profesorDao;

	@Override
	@Transactional( readOnly= true)
	public List<Profesor> findAll() {
		return (List<Profesor>) profesorDao.findAll();
	}

	@Override
	@Transactional( readOnly= true)
	public Profesor checkProfesorLogin(Profesor profesor) {
		return (Profesor) profesorDao.findByEmailAndPassword(profesor.getEmail(), profesor.getPassword());
	}

	@Override
	public void deleteProfesor(Profesor profesor) {
		profesorDao.delete(profesor);
	}

	@Override
	@Transactional
	public Profesor updateProfesor(Profesor profesor) {
		return (Profesor) profesorDao.save(profesor);
	}

	@Override
	public Optional<Profesor> findProfesorById(Profesor profesor) {
		return  profesorDao.findById(profesor.getId());
	}

	@Override
	@Transactional
	public void deleteProfesor(Long id) {
		profesorDao.deleteById(id);;
		
	}
	@Override
	@Transactional
	public void deleteAllProfesor() {
		profesorDao.deleteAll();
		
	}

	@Override
	@Transactional
	public Profesor findById(Long id) {
		return (Profesor) profesorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional( readOnly= true)
	public Profesor findByIdSQL(Long id) {
		return profesorDao.findByIdSQL(id);
	}

	@Override
	@Transactional( readOnly= true)
	public Profesor findProfesorByEmail(Profesor profesor) {
		return (Profesor) profesorDao.findByEmail( profesor.getEmail());
	}

	@Override
	@Transactional
	public void save(Profesor profesor) {
		profesorDao.save(profesor);
		
	}

}
