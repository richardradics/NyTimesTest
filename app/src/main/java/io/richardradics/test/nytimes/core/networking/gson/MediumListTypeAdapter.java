package io.richardradics.test.nytimes.core.networking.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.richardradics.test.nytimes.articles.data.repository.datasource.networking.json.Medium;

//TODO refactor api specific code
public class MediumListTypeAdapter implements JsonDeserializer<List<Medium>> {

    @Override
    public List<Medium> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Medium> vals = new ArrayList<>();
        if (json.isJsonArray()) {
            for (JsonElement e : json.getAsJsonArray()) {
                vals.add(context.deserialize(e, Medium.class));
            }
            return vals;
        } else {
            return null;
        }
    }
}
