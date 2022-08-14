package com.mygdx.game.hurdygurdy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.input.KeyHandler;

import java.util.HashMap;

public class HurdyGurdy implements KeyHandler {
    private final HashMap<Integer, Note> notes;
    private final Music backgroundMusic;

    public HurdyGurdy() {
        notes = new HashMap<>();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Medieval_Song_-_Eric_VINCENT.mp3"));
        backgroundMusic.setLooping(true);
//        backgroundMusic.play();

        addNote(createNote("c", "sound/c.wav"), Input.Keys.Q);
        addNote(createNote("d", "sound/d.wav"), Input.Keys.W);
        addNote(createNote("e", "sound/e.wav"), Input.Keys.E);
        addNote(createNote("f", "sound/f.wav"), Input.Keys.R);
        addNote(createNote("g", "sound/g.wav"), Input.Keys.T);
        addNote(createNote("a", "sound/a.wav"), Input.Keys.Y);
        addNote(createNote("b", "sound/b.wav"), Input.Keys.U);
        addNote(createNote("c2", "sound/c2.wav"), Input.Keys.I);
    }

    public boolean isPlaying() {
        return notes.values().stream().anyMatch(Note::isPlaying);
    }

    private Note createNote(String name, String path) {
        Note note = new Note();
        note.setName(name);
        note.setLoop(Gdx.audio.newSound(Gdx.files.internal(path)));
        return note;
    }

    private void addNote(Note note, int keyCodeToPLay) {
        notes.put(keyCodeToPLay, note);
    }

    @Override
    public void keyDown(int keycode) {
        Note note = notes.get(keycode);
        if (note != null) {
            note.play();
        }
    }

    @Override
    public void keyUp(int keycode) {
        Note note = notes.get(keycode);
        if (note != null) {
            note.stop();
        }
    }
}
