package util;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
	
		return LocalDateTime.parse(arg0.getAsJsonPrimitive().getAsString());
	}

}
