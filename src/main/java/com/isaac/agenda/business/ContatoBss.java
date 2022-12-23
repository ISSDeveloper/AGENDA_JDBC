package com.isaac.agenda.business;

import java.util.List;

import com.isaac.agenda.dao.ContatoDao;
import com.isaac.agenda.domain.Contato;
import com.isaac.agenda.exception.BssException;

public class ContatoBss {

	private ContatoDao dao;

	public ContatoBss() {
		dao = new ContatoDao();
	}

	public Contato getEntity(Long codContato) throws BssException {

		try {
			return dao.getEntity(codContato);

		} catch (Exception e) {
			throw new BssException(e);
		}
	}
	
	public List<Contato> getList() throws BssException {
		
		try {
			return dao.getList();
			
		} catch (Exception e) {
			throw new BssException(e);
		}
	}
	
	public Contato create(Contato contato) throws BssException {
		
		try {
			contato.setCodContato(dao.getNextCodInt());
			
			dao.create(contato);
			
			return contato;
		} catch (Exception e) {
			throw new BssException(e);
		}
	}
	public void update(Contato contato) throws BssException {
		
		try {
			dao.update(contato);
			
		} catch (Exception e) {
			throw new BssException(e);
		}
	}
}
