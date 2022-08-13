package com.mygdx.game.leaderboard;

import java.util.ArrayList;

public interface GetScoreResponseListener {
    void handleHttpResponse(boolean success, ArrayList<Score> scores);
    void failed(Throwable t);
    void cancelled();
}
