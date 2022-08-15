package com.mygdx.game.hurdygurdy;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.util.Util;

public class Note {
    private Sound loop;
    private Long loopPlaying = null;
    private String name;

    public Sound getLoop() {
        return loop;
    }

    public void setLoop(Sound loop) {
        this.loop = loop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void play() {
        if (loop != null) {
            loopPlaying = loop.loop();
        }
    }

    public boolean isPlaying() {
        return loopPlaying != null;
    }

    public void stop() {
        if (loop != null) {
            if (loopPlaying == null) {
                loop.stop();
            } else {
                Util.fadeOut(loop, loopPlaying, () -> loopPlaying = null);
            }
        }
    }
}
