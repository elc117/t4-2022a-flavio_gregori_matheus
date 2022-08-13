package com.mygdx.game.leaderboard;

public interface PostScoreResponseListener {
    void handleHttpResponse(boolean success, String message);
    void failed(Throwable t);
    void cancelled();
}
