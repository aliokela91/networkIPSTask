package com.networkIPS.task.services;


import com.networkIPS.task.data.models.responses.GetArticlesReposeModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("svc/mostpopular/v2/viewed/1.json")
    Observable<GetArticlesReposeModel> getArticles(
            @Query("api-key") String key
    );
}
