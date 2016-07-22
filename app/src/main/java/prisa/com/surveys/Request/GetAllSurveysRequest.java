package prisa.com.surveys.request;

import prisa.com.surveys.response.GetAllSurveyResponse;
import prisa.com.surveys.SurveyAPI;
import retrofit.Call;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public class GetAllSurveysRequest extends BaseRequest<GetAllSurveyResponse, SurveyAPI>{

    private String accessToken;

    public GetAllSurveysRequest(String accessToken) {
        super(GetAllSurveyResponse.class, SurveyAPI.class);
        this.accessToken = accessToken;
    }

    @Override
    public Call<GetAllSurveyResponse> callApi() {
        return service.getAllSurvey(accessToken, "1");
    }
}
