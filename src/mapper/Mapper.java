package mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dto.FilmSerie;
import dto.Intervenant;
import enums.Role;
import enums.Type;

public class Mapper {

		public static FilmSerie toDtoFilmSerie(ResultSet resultSet) throws SQLException {
			int id = resultSet.getInt("filmserie_id");
	        String name = resultSet.getString("filmserie_name");
	        String poster = resultSet.getString("filmserie_poster");
	        LocalDateTime ddc = resultSet.getTimestamp("filmserie_ddc").toLocalDateTime();
	        LocalDate dds = resultSet.getDate("filmserie_dds").toLocalDate();
	        int typeId = resultSet.getInt("type_id");
	        String typeName = resultSet.getString("type_name");
	        String synopsis = resultSet.getString("filmserie_synopsis");
	        int saisons = resultSet.getInt("filmserie_saisons");
	        int episodes = resultSet.getInt("filmserie_episodes");
	        List<Intervenant> intervenants = new ArrayList<>();
	        List<String> genres = new ArrayList<>();
	        		

	        return new FilmSerie(id, name, poster, ddc, dds, typeId, typeName, synopsis, saisons, episodes, intervenants, genres);
		}
		
		public static Intervenant toDtoIntervenant(ResultSet resultSet) throws SQLException {
			int id = resultSet.getInt("intervenant_id");
	        String firstName = resultSet.getString("intervenant_firstname");
	        String lastName = resultSet.getString("intervenant_lastname");
	        Role role = resultSet.getString("role_name") != null ? Role.valueOf(resultSet.getString("role_name")) : null;
	 
	        return new Intervenant(id, firstName, lastName, role);
		}
		
		public static Intervenant toDtoBasicIntervenant(ResultSet resultSet) throws SQLException {
			int id = resultSet.getInt("intervenant_id");
	        String firstName = resultSet.getString("intervenant_firstname");
	        String lastName = resultSet.getString("intervenant_lastname");
	 
	        return new Intervenant(id, firstName, lastName);
		}
}
