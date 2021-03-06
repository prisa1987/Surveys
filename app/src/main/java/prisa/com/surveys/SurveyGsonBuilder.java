package prisa.com.surveys;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import prisa.com.surveys.response.GetAllSurveyResponse;
import prisa.com.surveys.mvp.model.Survey;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class SurveyGsonBuilder {

    static GsonBuilder gsonBuilder = new GsonBuilder();

    public static GsonBuilder getGsonBuilder() throws IOException {
        Type listType = new TypeToken<GetAllSurveyResponse>(){}.getType();
        gsonBuilder.registerTypeAdapter(listType, new SurveysGsonAdapter());
        return gsonBuilder;
    }

}
