package com.example.amacle.github;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;

public class GitHubApiClient {

    private static final String BASE_URL = "https://api.github.com/";

    private OkHttpClient client;

    public GitHubApiClient() {
        client = new OkHttpClient();
    }

    public void makeApiRequest(String endpoint, Callback callback) {
        String url = BASE_URL + endpoint;
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
        
    }

    public void createRepository(String repoName, String authToken, Callback callback) {
        String url = BASE_URL + "user/repos";
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("name", repoName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
         MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requestBody.toString());
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "token " + authToken)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
