package com.mikhail.sportsnewshistoryrecords.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

import com.google.gson.Gson;
import com.mikhail.sportsnewshistoryrecords.api.keys.NytKeys;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsObjects;
import com.mikhail.sportsnewshistoryrecords.model.NytSportsResults;
import com.mikhail.sportsnewshistoryrecords.model.search.ArticleSearch;
import com.mikhail.sportsnewshistoryrecords.model.search.Response;

import java.util.List;

/**
 * Created by Mikhail on 4/27/16.
 */
public class NytAPI {

    public static final String NYT_API_URL = "http://api.nytimes.com/svc/news/v3/content/";
    public static Gson gson = new Gson();

//    http://api.nytimes.com/svc/news/v3/content/all/sports/.json?api-key=sample-key&subsection=Pro%20Football
    public interface NytRx {
        @GET("{source}/{section}/{subsection}/1.json?&api-key=" + NytKeys.newsWireKey)
        Observable<NytSportsResults> nytSportsResults(
                @Path("source") String source,
                @Path("section") String section,
                @Query("subsection") String subsection);
//        @GET("all/sports/1.json?&api-key=" + NytKeys.newsWireKey + "&subsection=")
//        Observable<NytSportsResults> nytSportsResults(
//                @Query("subsection") String subsection);
//        @GET("articlesearch.json?&sort=newest&api-key=" + NytKeys.nyTimesFullSearchQueryKey)
//        Observable<ArticleSearch> response(
//                @Query("q") String q);
//    http://api.nytimes.com/svc/news/v3/content/all/sports/.json?api-key=sample-key&subsection=soccer

//        @GET("/users/{owner}/repos")
//        Observable<List<Repo>> repos(
//                @Path("owner") String owner);
    }

//    public interface NytRx {
//        @GET("{source}/{section}/{subsection}/1.json?&api-key=" + NytKeys.newsWireKey)
//        Observable<List<NytSportsResults>> nytSportsResults(
//                @Path("source") String source,
//                @Path("section") String section,
//                @Path("subsection") String subsection);
////        @GET("/users/{owner}/repos")
////        Observable<List<Repo>> repos(
////                @Path("owner") String owner);


//    }

    public static NytRx createRx() {
        return new Retrofit.Builder()
                .baseUrl(NYT_API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(NytAPI.NytRx.class);
    }


//    @GET("{source}/sports/1.json?&api-key=" + Keys.newsWireKey)
//    Call<NewsWireResults> listNewsWireResults();

}
