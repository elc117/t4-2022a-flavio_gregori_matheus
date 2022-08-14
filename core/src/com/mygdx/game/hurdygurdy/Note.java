package com.mygdx.game.hurdygurdy;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.input.Action;

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
                fadeOut(loop, loopPlaying, () -> loopPlaying = null);
            }
        }
    }

    public void fadeOut(Sound sound, long soundId, Action onStop) {
        Task task = new Task() {
            float volume = 1;
            @Override
            public void run() {
                if (volume <= 0) {
                    sound.stop();
                    cancel();
                    if (onStop != null) {
                        onStop.act();
                    }
                } else {
                    sound.setVolume(soundId, volume);
                    volume -= 0.1;
                }
            }
        };
        Timer.schedule(task, 0, 0.005f);
    }
}
