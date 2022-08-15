package com.mygdx.game.leaderboard;

import java.util.Date;

public class Score implements Comparable<Score>{
    public String userId;
    public String score;
    public String timestamp;

    @Override
    public int compareTo(Score score) {
        if (score == this) return 0;
        if (this.score.equals(score.score)) return 0;
        long fstScoreAsNumber = Long.parseLong(score.score);
        long sndScoreAsNumber = Long.parseLong(this.score);
        return Long.compare(fstScoreAsNumber, sndScoreAsNumber);
    }
}
