package com.example.retrofit1;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {


    /**
     * Getting the data from the server using a Get request
     */
    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameter);

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId, //we can pass an array of integers
            @Query("_sort") String sort,
            @Query("_order") String order
    ); //putting a couple of parameters in the query

    Call<List<Post>> getPosts(
            @Query("userId") Integer userId, //we can use Interger instead of int coz int is not nullable
            @Query("_sort") String sort,
            @Query("_order") String order
    ); //putting a couple of parameters in the query

    Call<List<Post>> getPosts(
            @Query("userId") int userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    ); //putting a couple of parameters in the query

    Call<List<Post>> getPosts(@Query("userId") int userId); //calling with a query parameter

    Call<List<Post>> getPosts();  //default call

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);






    /**
     * Posting data to the server using a Post method
     */
    @FormUrlEncoded  //passing using the form format. we send values directly into our method
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("text") String text
    );

    @POST("posts") //sending our body data inform of json to the posts endpoint. the general way
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded  //sending our body using filedmaps
    @POST
    Call<Post> createPost(@FieldMap Map<String, String> fields);






    /**
     * Posting data to the server using the put method
     */
    @PUT("posts/{id}") //posting data to a specified id using put method
    Call<Post> putPost(@Path("id") int id, @Body Post post);






    /**
     * Posting data to the server using the put method
     */
    @PATCH("posts/{id}") //posting data to a specified id using patch method
    Call<Post> patchPost(@Path("id") int id, @Body Post post);







    /**
     * Deleting data from a certain endpoint by sending an id using delete method
     */
    @DELETE("posts/{id}")
    Call<Post> deletePost(@Path("id") int id);
}

