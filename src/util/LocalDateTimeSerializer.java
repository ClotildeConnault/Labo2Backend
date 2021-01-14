package util;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
	@Override
	public JsonElement serialize(LocalDateTime arg0, Type arg1, JsonSerializationContext arg2) {
		
		return new JsonPrimitive(formatter.format(arg0));
	}

}
