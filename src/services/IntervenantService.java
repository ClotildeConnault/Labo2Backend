package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.FilmSerie;
import dto.Intervenant;
import mapper.Mapper;

public class IntervenantService implements Crudable<Intervenant, Integer>{

	private static IntervenantService intervenantService;

	private IntervenantService() {

	}

	public static IntervenantService getInstance() {
		if(intervenantService == null) {
			intervenantService = new IntervenantService();
		}

		return intervenantService;
	}

	@Override
	public List<Intervenant> selectAll(Connection c) throws SQLException {


		List<Intervenant> output = new ArrayList<>();
		Statement statement = c.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT intervenant_id, intervenant_firstname, intervenant_lastname\r\n"
				+ "	FROM public.intervenant;");

		while (resultSet.next()) {
			Intervenant i = Mapper.toDtoBasicIntervenant(resultSet);
			output.add(i);
		}
		return output;
	}

	@Override
	public Intervenant selectBYId(Connection c, Integer id) throws SQLException {

		String request = "SELECT * FROM filmserie\r\n"
				+ "JOIN public.type ON type_id = filmserie_type\r\n"
				+ "WHERE filmserie_id = ?;";
		FilmSerie f = null;
		PreparedStatement preparedStatement = c.prepareStatement(request);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			f = Mapper.toDtoFilmSerie(resultSet);
			System.out.println(f);
			request = "	SELECT * FROM public.intervenant, fs_intervenant\r\n"
					+"			JOIN public.role ON role_id = fk_role"
					+ "			WHERE fk_fs_intervenant = ? AND intervenant_id = fk_intervenant_intervenant\r\n"
					+ "			;";
			preparedStatement = c.prepareStatement(request);
			preparedStatement.setInt(1, resultSet.getInt("filmserie_id"));
			ResultSet resultSetIntervenant = preparedStatement.executeQuery();
			
			while(resultSetIntervenant.next()) {
				Intervenant i = Mapper.toDtoIntervenant(resultSetIntervenant);
				f.addIntervenant(i);
			}
			
		}
		
		return null;


	}



	@Override
	public void update(Connection c, Intervenant i, Integer id) throws SQLException {
		

	}

	@Override
	public void delete(Connection c, Integer id) throws SQLException {
		String request = "DELETE FROM public.intervenant WHERE \"intervenant_id\" = ?";
		PreparedStatement preparedStatement = c.prepareStatement(request);
		preparedStatement.setInt(1, id);

		preparedStatement.executeUpdate();

	}

	@Override
	public void insert(Connection c, Intervenant v) throws SQLException {
		
		String request = "INSERT INTO public.intervenant VALUES (DEFAULT, ?, ?)";
		PreparedStatement preparedStatement = c.prepareStatement(request);
		preparedStatement.setString(1, v.getFirstName());
		preparedStatement.setString(2, v.getLastName());
		
		preparedStatement.executeUpdate();
		
	}



}

