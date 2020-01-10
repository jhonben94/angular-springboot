package com.example.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.IProfesorDAO;
import com.example.entity.Profesor;

@Service
public class ProfesorService implements IProfesorService{
	
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
	@Transactional( readOnly= true)
	public Optional<Profesor> findProfesorById(Long id) {
		return  profesorDao.findById(id);
	}

	@Override
	public void deleteProfesor(Long id) {
		profesorDao.deleteById(id);;
		
	}

	@Override
	@Transactional
	public Profesor findById(Long id) {
		return (Profesor) profesorDao.findById(id).orElse(null);
	}

	@Override
	public Profesor findByIdSQL(Long id) {
		return profesorDao.findByIdSQL(id);
	}

	@Override
	public Profesor findProfesorByEmail(Profesor profesor) {
		return (Profesor) profesorDao.findByEmail( profesor.getEmail());
	}

}