package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
import util.ArrayListDeserializer;
import util.ConnexionPSQL;
import util.LocalDateDeserializer;
import util.LocalDateTimeDeserializer;
import util.Message;
import util.Util;

@WebServlet(name="api", urlPatterns = {"/api", "/api/*"})
public class API extends HttpServlet {

	private final String HOST = "/labo2";
	private final FilmService filmService = FilmService.getInstance();
	private static final Gson gson = new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).create()
			;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pathInfo = req.getRequestURI();

		String choice = !pathInfo.equals(HOST + "/api") ? pathInfo.split("/")[3] : "selectAll";

		switch(choice) {
		case "selectAll":
			try {
				Util.formatResponse(resp, filmService.selectAll(ConnexionPSQL.getInstance()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
	
		default:
			int id = Integer.parseInt(req.getRequestURI().split("/")[3]);
			try {
				Util.formatResponse(resp, filmService.selectBYId(ConnexionPSQL.getInstance(), id));
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getRequestURI().split("/")[3]);
		int iToDelete = Integer.valueOf(req.getParameter("iToDelete"));
	
		if (iToDelete > 0) {
			try {
				filmService.deleteIntervenant(ConnexionPSQL.getInstance(), id, iToDelete);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Util.formatResponse(resp, new Message("Le film d'id " + id + " a bien été supprimé"));
			
			
		}
		else {
			try {
				filmService.delete(ConnexionPSQL.getInstance(), id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Util.formatResponse(resp, new Message("Le film d'id " + id + " a bien été supprimé"));
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getRequestURI();

		String choice = !pathInfo.equals(HOST + "/api") ? pathInfo.split("/")[3] : "insertFilm";
		FilmSerie filmToAdd = gson.fromJson(req.getReader(), FilmSerie.class);
		
		switch(choice) {
		case "insertFilm":
			
			try {
				filmService.insert(ConnexionPSQL.getInstance(), filmToAdd);
				Util.formatResponse(resp, new Message("Le film " + filmToAdd.getName() + " a bien été ajouté"));
			} catch (SQLException e) {
				Util.formatResponse(resp, e.getMessage());
			}
			break;
	
		default:
			
			System.out.println("roleId : " + req.getParameter("roleId"));
			System.out.println("intervenantId : " + req.getParameter("intervenantId"));
			System.out.println("filmId : " + filmToAdd.getId());
			int iId = Integer.valueOf(req.getParameter("intervenantId"));
			int roleId = Integer.valueOf(req.getParameter("roleId"));
			filmToAdd.setIntervenant(iId);
			filmToAdd.setIntervenantRole(roleId);
			
			try {
				filmService.insert(ConnexionPSQL.getInstance(), filmToAdd);
				Util.formatResponse(resp, new Message("L'intervenant d'id " + iId + " a bien été ajouté au film " + filmToAdd.getName()));
			} catch (SQLException e) {
				Util.formatResponse(resp, e.getMessage());
			}

		}
	}
		

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FilmSerie filmToAdd = gson.fromJson(req.getReader(), FilmSerie.class);
		int id = Integer.parseInt(req.getRequestURI().split("/")[3]);
		
		try {
			
				filmService.update(ConnexionPSQL.getInstance(), filmToAdd, id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Util.formatResponse(resp, new Message("Le film " + filmToAdd.getName() + " a bien été modifié"));
	}



	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Util.formatResponse(resp, "");
	}


}
