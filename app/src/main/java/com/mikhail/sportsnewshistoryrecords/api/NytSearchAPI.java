package com.mikhail.sportsnewshistoryrecords.api;

import com.mikhail.sportsnewshistoryrecords.api.keys.NytKeys;
import com.mikhail.sportsnewshistoryrecords.model.search.ArticleSearch;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API call NYT Search
 */
public class NytSearchAPI {

    public static final String NYT_API_URL = "http://api.nytimes.com/svc/search/v2/";


    public interface NytAPIRetrofitSimple {
        @GET("articlesearch.json?&api-key=" + NytKeys.nyTimesFullSearchQueryKey)
        Call<ArticleSearch> response(
                @Query("q") String q);
    }

    public static NytAPIRetrofitSimple create() {
        return new Retrofit.Builder()
                .baseUrl(NYT_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NytSearchAPI.NytAPIRetrofitSimple.class);
    }

}
