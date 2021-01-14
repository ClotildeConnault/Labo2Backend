package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Util {
	
	public static void formatResponse(HttpServletResponse resp, Object object) throws IOException {
		PrintWriter out = resp.getWriter();
		Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
       
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Request-Headers", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS, PUT");
        resp.addHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With,X-HTTP-Method-Override, Content-Type, Accept, Authorization");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
		out.print(gson.toJson(object));
		out.flush();
	}

}
