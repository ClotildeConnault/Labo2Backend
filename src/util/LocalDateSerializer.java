package util;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateSerializer implements JsonSerializer<LocalDate>{
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
	@Override
	public JsonElement serialize(LocalDate arg0, Type arg1, JsonSerializationContext arg2) {
		
		return new JsonPrimitive(formatter.format(arg0));
	}

}
