package util;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ArrayListDeserializer implements JsonDeserializer<ArrayList> {

	@Override
	public ArrayList deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		
		return null;
	}

}
