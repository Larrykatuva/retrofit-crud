package com.example.retrofit1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        //forcing the gson to serialise null
        Gson gson = new GsonBuilder().serializeNulls().create();

        //initialising the retrofit
        Retrofit retrofit = new Retrofit.Builder()                       //initialising the retrofit class
                .baseUrl("https://jsonplaceholder.typicode.com/")        //adding the base url of your api
                .addConverterFactory(GsonConverterFactory.create(gson))      //adding the converter factory you're using
                .build();                                                //finally build


        //Curling the api
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class); //creating your api instance

        //getPosts();
        //getComments();
        createPost();
        //putPost();
       // patchPost();
        //deletePost();
    }
    private void deletePost(){
        Call<Post> call = jsonPlaceHolderApi.deletePost(5);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                textViewResult.setText("CODE: " +response.code());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


    private void patchPost(){
        Post post = new Post (23, "null", "New Text");

        Call<Post> call = jsonPlaceHolderApi.patchPost(5,post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " +postResponse.getId()+ "\n";
                content += "User ID: " +postResponse.getUserId()+ "\n";
                content += "Title: " +postResponse.getTitle()+ "\n";
                content += "Text: " +postResponse.getTitle()+ "\n\n";

                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


    }


    private void putPost(){
        Post post = new Post (23, "null", "New Text");

        Call<Post> call = jsonPlaceHolderApi.putPost(5,post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " +postResponse.getId()+ "\n";
                content += "User ID: " +postResponse.getUserId()+ "\n";
                content += "Title: " +postResponse.getTitle()+ "\n";
                content += "Text: " +postResponse.getTitle()+ "\n\n";

                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }


    private void createPost(){
       Post post = new Post (23, "New Title", "New Text");

        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "23");
        fields.put("title", "New Title");
        fields.put("text", "New Text");


        Call<Post> call = jsonPlaceHolderApi.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " +postResponse.getId()+ "\n";
                content += "User ID: " +postResponse.getUserId()+ "\n";
                content += "Title: " +postResponse.getTitle()+ "\n";
                content += "Text: " +postResponse.getTitle()+ "\n\n";

                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

    private void getComments(){
        //Executing the network request using the call object
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);

        //Executing the call and getting the response back
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Comment> comments = response.body();

                for (Comment comment: comments){
                    String content = "";
                    content += "ID: " +comment.getId() + "\n";
                    content += "Post Id: " +comment.getPostId() + "\n";
                    content += "Name: " +comment.getName() + "\n";
                    content += "Email: " +comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getPosts(){
        //Setting our query map parameters
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        //Executing the network request using the call object
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);

        //Executing the call and getting the response back
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();

                for (Post post : posts){
                    String content = "";
                    content += "ID: " +post.getId()+ "\n";
                    content += "User ID: " +post.getUserId()+ "\n";
                    content += "Title: " +post.getTitle()+ "\n";
                    content += "Text: " +post.getTitle()+ "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
