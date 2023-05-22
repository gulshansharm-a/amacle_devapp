package com.example.amacle.github;

import static android.content.ContentValues.TAG;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GitFunction {
    private GitHubApiClient gitHubApiClient;
    public void initilize() {
        gitHubApiClient = new GitHubApiClient();
        makeGitHubApiRequest();
    }
    private void makeGitHubApiRequest() {
        String username = "gulshansharm-a"; // Replace with your GitHub username

        gitHubApiClient.makeApiRequest("users/" + username, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "API request failed: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();

                    // Process the JSON response here
                    //  createRepository("repoNewRand","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");
                    // inviteUserToRepository("gulshansharm-a","repoNewRand","rahulpradhan5","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");
                    createTodoList("gulshansharm-a","repoNewRand","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","test issue","this isue is for test purposes");
                    //getIssues("gulshansharm-a","repoNewRand","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");

                    Log.d(TAG, "Response: " + jsonResponse);
                } else {
                    Log.e(TAG, "API request failed: " + response.code());
                }
            }
        });
    }
    public void createRepository(String repoName, String authToken) {
        gitHubApiClient.createRepository(repoName, authToken, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();
                    // Process the JSON response here
                    Log.d(TAG, "Response: tocreate " + jsonResponse);
                    JSONObject jsonObject = null;
                    String message;
                } else {
                    String errorMessage = response.body().string();
                    // Handle the 422 error specific to creating a repository
                    JSONObject jsonObject = null;
                    String message;
                    Log.e(TAG, "API request failed: " + response.code());
                }
            }
        });
    }

    public void inviteUserToRepository(String repoOwner, String repoName, String username, String authToken) {
        String invitationUrl = "https://api.github.com/repos/" + repoOwner + "/" + repoName + "/collaborators/" + username;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(invitationUrl)
                .header("Authorization", "token " + authToken)
                .put(RequestBody.create(null, new byte[0]))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Collaborator added successfully
                    Log.d(TAG, "Collaborator added successfully");
                } else {
                    Log.e(TAG, "API request failed: " + response.code());
                }
            }
        });
    }
    public void createTodoList(String repoOwner, String repoName, String authToken, String todoListTitle, String todoListBody) {
        String apiUrl = "https://api.github.com/repos/" + repoOwner + "/" + repoName + "/issues";

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("title", todoListTitle);
            requestBody.put("body", todoListBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody.toString());

        Request request = new Request.Builder()
                .url(apiUrl)
                .header("Authorization", "token " + authToken)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Todo list created successfully
                    Log.d(TAG, "Todo list created successfully");
                } else {
                    Log.e(TAG, "API request failed: " + response.code());
                }
            }
        });
    }
    public void getIssues(String repoOwner, String repoName, String authToken) {
        String apiUrl = "https://api.github.com/repos/" + repoOwner + "/" + repoName + "/issues";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiUrl)
                .header("Authorization", "token " + authToken)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();
                    // Process the JSON response here

                    Log.d(TAG, "Response: of todo " + jsonResponse);
                } else {
                    Log.e(TAG, "API request failed: " + response.code());
                }
            }
        });
    }
}
