package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.FilmSerie;
import dto.Intervenant;
import services.FilmService;
import services.IntervenantService;
import util.ConnexionPSQL;
import util.LocalDateDeserializer;
import util.LocalDateTimeDeserializer;
import util.Message;
import util.Util;

@WebServlet(name="apiperson", urlPatterns = {"/apiperson", "/apiperson/*"})
public class APIPerson extends HttpServlet {

	private final String HOST = "/labo2";
	private final IntervenantService intervenantService = IntervenantService.getInstance();
	private final FilmService filmService = FilmService.getInstance();
	
	private static final Gson gson = new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).create()
			;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pathInfo = req.getRequestURI();

		String choice = !pathInfo.equals(HOST + "/apiperson") ? pathInfo.split("/")[3] : "selectAll";

		switch(choice) {
		case "selectAll":
			try {
				Util.formatResponse(resp, intervenantService.selectAll(ConnexionPSQL.getInstance()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		
		default:
			int id = Integer.parseInt(req.getRequestURI().split("/")[3]);
			try {
				Util.formatResponse(resp, intervenantService.selectBYId(ConnexionPSQL.getInstance(), id));
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getRequestURI().split("/")[3]);
	
		try {
			intervenantService.delete(ConnexionPSQL.getInstance(), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Util.formatResponse(resp, new Message("L'intervenant d'id " + id + " a bien été supprimé"));

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Intervenant intervenantToAdd = gson.fromJson(req.getReader(), Intervenant.class);
		
		
		try {
			intervenantService.insert(ConnexionPSQL.getInstance(), intervenantToAdd);
			Util.formatResponse(resp, new Message("L'intervenant " + intervenantToAdd.getFirstName() + " a bien été ajouté"));
		} catch (SQLException e) {
			Util.formatResponse(resp, e.getMessage());
		}
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FilmSerie filmToAdd = gson.fromJson(req.getReader(), FilmSerie.class);
		
		System.out.println("je passe en doPut");
		
		try {
			filmService.update(ConnexionPSQL.getInstance(), filmToAdd, filmToAdd.getIntervenant());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Util.formatResponse(resp, new Message("Le film " + filmToAdd.getName() + " a bien été modifié"));
	}



	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("je passe en doOptions");
		Util.formatResponse(resp, "");
	}


}

