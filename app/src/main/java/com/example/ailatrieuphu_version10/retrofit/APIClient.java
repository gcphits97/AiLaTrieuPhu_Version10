package com.example.ailatrieuphu_version10.retrofit;

public class APIClient {
    private static final String baseUrl = "http://10.0.3.2:8080/";
    public static LoginJsonApi getUser() {
        return Client.getClient(baseUrl).create(LoginJsonApi.class);
    }

    public static RegisterJsonApi createUser() {
        return Client.getClient(baseUrl).create(RegisterJsonApi.class);
    }

    public static PlayerJsonApi getQuestion() {
        return Client.getClient(baseUrl).create(PlayerJsonApi.class);
    }

    public static HighScoreJsonApi getPlayerList() {
        return Client.getClient(baseUrl).create(HighScoreJsonApi.class);
    }
}
