package prisa.com.surveys.Response;

import java.util.List;

import prisa.com.surveys.model.Survey;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class GetAllSurveyResponse {

    public List<Survey> surveys;

    public GetAllSurveyResponse(List<Survey> surveys) {
        this.surveys = surveys;
    }

}
