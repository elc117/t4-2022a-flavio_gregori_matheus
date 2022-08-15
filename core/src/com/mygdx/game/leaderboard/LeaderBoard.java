package com.mygdx.game.leaderboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LeaderBoard {
    private final String BASE_URL ="https://script.google.com/macros/s/" +
            "AKfycby_66cv7hxuwGnRYx2-kZDm4YvlWy0gXiHAgjsO2jSENfWI9OO69mOqjk5goGIEr1rf5Q/exec";

    private final String gameId;

    public LeaderBoard(String gameId) {
        this.gameId = gameId;
    }

    public void postScore(String userId, String score, HttpResponseListener responseListener) {
        String url = BASE_URL +
                "?action=postScore" +
                "&gameId=" + gameId +
                "&userId=" + userId +
                "&score=" + score;
        sendGetRequest(url, responseListener);
    }

    public void postScore(String userId, String score, PostScoreResponseListener postScoreResponseListener) {
        HttpResponseListener responseListener = new HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonValue parsed = new JsonReader().parse(httpResponse.getResultAsString());
                postScoreResponseListener.handleHttpResponse(parsed.getBoolean("success"), parsed.getString("response"));
            }

            @Override
            public void failed(Throwable t) {
                postScoreResponseListener.failed(t);
            }

            @Override
            public void cancelled() {
                postScoreResponseListener.cancelled();
            }
        };
        postScore(userId, score, responseListener);
    }

    public void getScores(HttpResponseListener responseListener) {
        String url = BASE_URL + "?action=getScores" + "&gameId=" + gameId;
        sendGetRequest(url, responseListener);
    }

    public void getScores(GetScoreResponseListener getScoreResponseListener, int max) {
        HttpResponseListener responseListener = new HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                if (httpResponse.getStatus().getStatusCode() == 200) {
                    ArrayList<Score> scores = extractScores(httpResponse.getResultAsString());
                    boolean success;
                    if (scores == null) {
                        scores = new ArrayList<>();
                        success = false;
                    } else {
                        success = true;
                        scores = scores.stream().sorted(Score::compareTo).limit(max).collect(Collectors.toCollection(ArrayList::new));
                    }
                    getScoreResponseListener.handleHttpResponse(success, scores);
                }
            }

            @Override
            public void failed(Throwable t) {
                getScoreResponseListener.failed(t);
            }

            @Override
            public void cancelled() {
                getScoreResponseListener.cancelled();
            }
        };
        getScores(responseListener);
    }

    private ArrayList<Score> extractScores(String response) {
        JsonValue parsed = new JsonReader().parse(response);
        JsonValue.JsonIterator iterator = parsed.iterator("response");
        if (parsed.getBoolean("success")) {
            ArrayList<Score> scores = new ArrayList<>();
            Json json = new Json();
            while (iterator.hasNext()) {
                scores.add(json.fromJson(Score.class, iterator.next().toString()));
            }
            return scores;
        } else {
            return null;
        }
    }

    private void sendGetRequest(String url, HttpResponseListener responseListener) {
        HttpRequest httpGET = new HttpRequest(Net.HttpMethods.GET);
        httpGET.setUrl(url);
        Gdx.net.sendHttpRequest(httpGET, responseListener);
    }
}
