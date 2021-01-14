package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.List;
import java.sql.Date;


import dto.FilmSerie;
import dto.Intervenant;
import mapper.Mapper;

public class FilmService implements Crudable<FilmSerie, Integer>{

	private static FilmService filmService;

	private FilmService() {

	}

	public static FilmService getInstance() {
		if(filmService == null) {
			filmService = new FilmService();
		}

		return filmService;
	}

	@Override
	public List<FilmSerie> selectAll(Connection c) throws SQLException {


		List<FilmSerie> output = new ArrayList<>();
		Statement statement = c.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM filmserie\r\n"
				+ "JOIN public.type ON type_id = filmserie_type\r\n"
				+ "GROUP BY filmserie_id, type_id\r\n"
				+ "	;");

		while (resultSet.next()) {
			FilmSerie f = Mapper.toDtoFilmSerie(resultSet);
			String request = "SELECT * FROM public.intervenant\r\n"
					+ "	JOIN fs_intervenant ON fk_intervenant_intervenant = intervenant_id\r\n"
					+ " JOIN public.role ON role_id = fk_role"
					+ "	WHERE fk_fs_intervenant = ?\r\n"
					+ "	;";
			PreparedStatement preparedStatement = c.prepareStatement(request);
			preparedStatement.setInt(1, resultSet.getInt("filmserie_id"));
			ResultSet resultSetIntervenant = preparedStatement.executeQuery();

			while(resultSetIntervenant.next()) {
				Intervenant i = Mapper.toDtoIntervenant(resultSetIntervenant);
				f.addIntervenant(i);
			}
			
			request= "SELECT * FROM public.genre\r\n"
					+ "					JOIN fs_genre ON fk_genre_genre = genre_id\r\n"
					+ "					WHERE fk_fs_genre = ?;";
			preparedStatement = c.prepareStatement(request);
			preparedStatement.setInt(1, resultSet.getInt("filmserie_id"));
			ResultSet resultSetGenres = preparedStatement.executeQuery();
			while(resultSetGenres.next()) {
				
				f.addGenre(resultSetGenres.getString("genre_name"));
			}

			output.add(f);

		}
		return output;
	}

	@Override
	public FilmSerie selectBYId(Connection c, Integer id) throws SQLException {

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
			
			request= "SELECT * FROM public.genre\r\n"
					+ "					JOIN fs_genre ON fk_genre_genre = genre_id\r\n"
					+ "					WHERE fk_fs_genre = ?;";
			preparedStatement = c.prepareStatement(request);
			preparedStatement.setInt(1, resultSet.getInt("filmserie_id"));
			ResultSet resultSetGenres = preparedStatement.executeQuery();
			while(resultSetGenres.next()) {
				
				f.addGenre(resultSetGenres.getString("genre_name"));
			}

		}

		return f;


	}

	@Override
	public void insert(Connection c, FilmSerie f) throws SQLException {
		
		if (!f.getGenres().isEmpty()) {
			String request = "INSERT INTO public.fs_genre(\r\n"
					+ "	fk_fs_genre, fk_genre_genre)\r\n"
					+ "	VALUES (?, ?, ?);";
			PreparedStatement preparedStatement = c.prepareStatement(request);
			preparedStatement.setInt(1, f.getId());
			preparedStatement.setInt(2, f.getIntervenant());
			
			preparedStatement.executeUpdate();
		}

		if (f.getIntervenant() > 0) {
			String request = "INSERT INTO public.fs_intervenant(\r\n"
					+ "	fk_fs_intervenant, fk_intervenant_intervenant, fk_role)\r\n"
					+ "	VALUES (?, ?, ?);";
			PreparedStatement preparedStatement = c.prepareStatement(request);
			preparedStatement.setInt(1, f.getId());
			preparedStatement.setInt(2, f.getIntervenant());
			preparedStatement.setInt(3, f.getIntervenantRole());
			
			preparedStatement.executeUpdate();
		}
		else {
			
			String request = "INSERT INTO public.filmserie VALUES (DEFAULT, ?, ?, CURRENT_DATE, ?, ?, ?, ?, ?, DEFAULT)";
			PreparedStatement preparedStatement = c.prepareStatement(request);
			preparedStatement.setString(1, f.getName());
			preparedStatement.setString(2, f.getPoster());
			preparedStatement.setDate(3, Date.valueOf(f.getDds()));
			preparedStatement.setInt(4, f.getTypeId());
			preparedStatement.setString(5, f.getSynopsis());
			preparedStatement.setInt(6, f.getSaisons());
			preparedStatement.setInt(7, f.getEpisodes());

			preparedStatement.executeUpdate();
		}


	}

	@Override
	public void update(Connection c, FilmSerie f, Integer id) throws SQLException {
		System.out.println("poster:" + f.getPoster());
		String request = "UPDATE public.filmserie\r\n"
				+ "	SET filmserie_name=?, filmserie_poster=?, filmserie_dds=?, filmserie_type=?, filmserie_synopsis=?, filmserie_saisons=?, filmserie_episodes=?, filmserie_ddm=CURRENT_DATE\r\n"
				+ "	WHERE filmserie_id=?;";
		PreparedStatement preparedStatement = c.prepareStatement(request);
		preparedStatement.setString(1, f.getName());
		preparedStatement.setString(2, f.getPoster());
		preparedStatement.setDate(3, Date.valueOf(f.getDds()));
		preparedStatement.setInt(4, f.getTypeId());
		preparedStatement.setString(5, f.getSynopsis());
		preparedStatement.setInt(6, f.getSaisons());
		preparedStatement.setInt(7, f.getEpisodes());
		
		preparedStatement.setInt(8, id);

		preparedStatement.executeUpdate();


	}



	@Override
	public void delete(Connection c, Integer id) throws SQLException {
		String request = "DELETE FROM public.filmserie WHERE \"filmserie_id\" = ?";
		PreparedStatement preparedStatement = c.prepareStatement(request);
		preparedStatement.setInt(1, id);

		preparedStatement.executeUpdate();

	}

	public void deleteIntervenant(Connection c, int id, int iToDelete) throws SQLException {
		String request = "DELETE FROM public.fs_intervenant WHERE fk_intervenant_intervenant = ? AND fk_fs_intervenant = ? ;";
		PreparedStatement preparedStatement;
		preparedStatement = c.prepareStatement(request);
		
		preparedStatement.setInt(1, iToDelete);
		preparedStatement.setInt(2, id);

		preparedStatement.executeUpdate();
		
	}


}
