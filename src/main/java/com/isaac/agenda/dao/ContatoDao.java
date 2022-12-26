package com.isaac.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.isaac.agenda.domain.Contato;
import com.isaac.agenda.exception.DaoException;
import com.isaac.agenda.factory.ConnectionFactory;

public class ContatoDao {

	private Connection connection;

	public ContatoDao() {
		connection = ConnectionFactory.getConnection();
	}

	private Contato getEntity(ResultSet rs) throws SQLException {

		Contato contato = new Contato();

		contato.setCodContato(rs.getLong("COD_CONTATO"));
		contato.setNome(rs.getString("NOME"));
		contato.setTelefone(rs.getLong("TELEFONE"));

		return contato;
	}

	public Contato getEntity(Long codContato) throws DaoException {

		Contato contato = null;
		
		try {

			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM CONTATO WHERE COD_CONTATO = " + codContato);

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				contato = getEntity(rs);
			}

			stmt.close();
			rs.close();
			
			return contato;

		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public List<Contato> getList() throws DaoException {

		try {

			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CONTATO ORDER BY COD_CONTATO");

			ResultSet rs = stmt.executeQuery();

			List<Contato> lista = new ArrayList<>();

			while (rs.next()) {
				lista.add(getEntity(rs));
			}
			
			rs.close();
			stmt.close();

			return lista;

		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void create(Contato contato) throws DaoException {

		try {

			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO CONTATO (COD_CONTATO, NOME, TELEFONE) VALUES (?, ?, ?)");

			stmt.setString(1, "" + contato.getCodContato());
			stmt.setString(2, contato.getNome());
			stmt.setString(3, "" + contato.getTelefone());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void update(Contato contato) throws DaoException {

		try {

			PreparedStatement stmt = connection
					.prepareStatement("UPDATE CONTATO SET NOME=?, TELEFONE=? WHERE COD_CONTATO=?");

			stmt.setString(1, contato.getNome());
			stmt.setLong(2, contato.getTelefone());
			stmt.setLong(3, contato.getCodContato());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public Long getNextCodInt() throws DaoException {

		Long codContato = 1L;
		
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT MAX(COD_CONTATO) COD_CONTATO FROM CONTATO");
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				codContato = rs.getLong("COD_CONTATO") + 1;
			}
			
			rs.close();
			stmt.close();
			
			return codContato;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void remove(Long codContato) throws DaoException {
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM CONTATO WHERE COD_CONTATO = " + codContato);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
}
