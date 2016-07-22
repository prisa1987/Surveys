package prisa.com.surveys;

import prisa.com.surveys.response.GetAllSurveyResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Admin on 7/19/2016 AD.
 */

public interface SurveyAPI {

    @GET("/app/surveys.json")
    Call<GetAllSurveyResponse> getAllSurvey(@Query("access_token") String accessToken, @Query("limit") String limit);

}
