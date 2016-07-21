package prisa.com.surveys;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import prisa.com.surveys.Response.GetAllSurveyResponse;
import prisa.com.surveys.model.Survey;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveysGsonAdapter implements JsonDeserializer<GetAllSurveyResponse> {

    @Override
    public GetAllSurveyResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = (JsonArray)json;
        Type listType = new TypeToken<List<Survey>>(){}.getType();
        List<Survey> surveys = new Gson().fromJson(jsonArray, listType);
        return new GetAllSurveyResponse(surveys);
    }

}
