package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FilmSerie;
import services.FilmService;
import util.ConnexionPSQL;


@WebServlet(name="Films", urlPatterns={"/films","/films/detailfilm/*", "/films/delete/*", "/films/creationfilm", "/films/updatefilm/*"}, loadOnStartup=1)
public class Films extends HttpServlet {

	private final String HOST = "/labo2";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String pathInfo = request.getRequestURI();
		FilmService filmService = FilmService.getInstance();	
		request.setAttribute("message", null);

		String choice = !pathInfo.equals(HOST + "/films") ? pathInfo.split("/")[3] : "films";
		String[] parts;
		int id = -1;



		List<FilmSerie> films = new ArrayList<>();


		switch (choice) {
		case "films" :

			try {
				films = filmService.selectAll(ConnexionPSQL.getInstance());
				request.setAttribute("films", films);
			} catch (SQLException e) {
				request.setAttribute("message", e.getMessage());

			} finally {

				this.getServletContext().getRequestDispatcher("/WEB-INF/films.jsp").forward(request, response);
			}

			break;

		case "detailfilm" :  
			parts = pathInfo.split("/");

			id = Integer.parseInt(parts[4]);

			try {
				request.setAttribute("film", filmService.selectBYId(ConnexionPSQL.getInstance(), id));
			} catch (SQLException e) {
				request.setAttribute("messages", e.getMessage());
			} finally {
				this.getServletContext().getRequestDispatcher("/WEB-INF/detailfilm.jsp").forward(request, response);
			}

			break;

		case "delete" :  
			parts = pathInfo.split("/");

			id = Integer.parseInt(parts[4]) ;

			try {
				filmService.delete(ConnexionPSQL.getInstance(), id);

			} catch (SQLException e) {
				request.setAttribute("messages", e.getMessage());
			} finally {
				response.sendRedirect(HOST + "/films");

			}

			break;

		case "creationfilm" :

			try {
				request.setAttribute("films", filmService.selectAll(ConnexionPSQL.getInstance()));
			} catch (SQLException e) {
				request.setAttribute("messages", e.getMessage());
			} finally {
				this.getServletContext().getRequestDispatcher("/WEB-INF/creationfilm.jsp").forward(request, response);
			}

			break;

		case "updatefilm":
			parts = pathInfo.split("/");

			id = Integer.parseInt(parts[4]);

			try {
				request.setAttribute("film", filmService.selectBYId(ConnexionPSQL.getInstance(), id));
			} catch (SQLException e) {
				request.setAttribute("messages", e.getMessage());
			} finally {
				this.getServletContext().getRequestDispatcher("/WEB-INF/updatefilm.jsp").forward(request, response);
			}

			break;
		}


	}
	
//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	
//		FilmService filmService = FilmService.getInstance();
//		String pathInfo = request.getRequestURI();
//		String choice = !pathInfo.equals(HOST + "/films") ? pathInfo.split("/")[3] : "films";
//		String[] parts;
//		
//		
//		String name = request.getParameter("name");
//		int typeId = Integer.parseInt(request.getParameter("typeId"));
//		String typeName = typeId == 5?  "film" : "serie";
//		
//		
//		
//		
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate date = LocalDate.parse(request.getParameter("dds"), dtf);
//	
//		String synopsis = request.getParameter("synopsis");
//		FilmSerie f = null;
//		
//		
//		switch (choice) {
//			case "creationfilm":
//				
//			f = new FilmSerie(name, LocalDateTime.now() ,date, typeId, typeName, synopsis);
//			
//			try {
//				filmService.insert(ConnexionPSQL.getInstance(), f);
//			} catch (SQLException e) {
//				request.setAttribute("message", e.getMessage());
//			} finally {
//				response.sendRedirect(HOST + "/films");
//			}
//		
//			break;
//			
//			case "updatefilm":
//			int id = Integer.valueOf(request.getParameter("id"));
//			f = new FilmSerie(id, name, LocalDateTime.now() ,date, typeId, typeName, synopsis);
//			parts = pathInfo.split("/");
//			
//			id = Integer.parseInt(parts[4]);
//			
//			try {
//				filmService.update(ConnexionPSQL.getInstance(), f, id);
//				//request.setAttribute("update", f);
//			} catch (SQLException e) {
//				request.setAttribute("message", e.getMessage());
//			} finally {
//				response.sendRedirect(HOST + "/films");
//				//this.getServletContext().getRequestDispatcher("/WEB-INF/debug.jsp").forward(request, response);
//			}
//			break;
//		}}
		
		
		
		
}
	














