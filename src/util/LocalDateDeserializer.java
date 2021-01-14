package util;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		
		return LocalDate.parse(arg0.getAsJsonPrimitive().getAsString());
	}

}
