package com.example.curso.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.curso.dao.ILenguajeDAO;
import com.example.curso.entity.Lenguaje;

@Service
public class LenguajeServiceImpl implements ILenguajeService {
	@Autowired
	private ILenguajeDAO lenguajeDao;

	@Override
	@Transactional(readOnly = true)
	public List<Lenguaje> findAll() {
		List<Lenguaje>listaL = ( List<Lenguaje>) lenguajeDao.findAll();
		return listaL;
	}

	@Override
	@Transactional
	public void guardarLenguaje(Lenguaje l) {
		lenguajeDao.save(l);
	}

	@Override
	@Transactional(readOnly = true)
	public Lenguaje buscarLenguajeId(Long id) {
		return lenguajeDao.findByIdSQL(id);
	}

}
