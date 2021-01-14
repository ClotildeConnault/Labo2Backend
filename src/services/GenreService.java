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

public class GenreService implements Crudable<String, Integer>{

	private static GenreService genreService;

	private GenreService() {

	}

	public static GenreService getInstance() {
		if(genreService == null) {
			genreService = new GenreService();
		}

		return genreService;
	}

	@Override
	public List<String> selectAll(Connection c) throws SQLException {


		List<String> output = new ArrayList<>();
		Statement statement = c.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM public.genre;");

		while (resultSet.next()) {
			
			output.add(resultSet.getString("genre_name"));
		}
		return output;
	}

	@Override
	public String selectBYId(Connection c, Integer id) throws SQLException {

		
		
		return null;


	}



	@Override
	public void update(Connection c, String g, Integer id) throws SQLException {
		
	}

	@Override
	public void delete(Connection c, Integer id) throws SQLException {
		String request = "DELETE FROM public.genre WHERE \"genre_id\" = ?";
		PreparedStatement preparedStatement = c.prepareStatement(request);
		preparedStatement.setInt(1, id);

		preparedStatement.executeUpdate();

	}

	@Override
	public void insert(Connection c, String g) throws SQLException {
		
		String request = "INSERT INTO public.genre VALUES (DEFAULT, ?)";
		PreparedStatement preparedStatement = c.prepareStatement(request);
		preparedStatement.setString(1, g);
		
		
		preparedStatement.executeUpdate();
		
	}

	


}

