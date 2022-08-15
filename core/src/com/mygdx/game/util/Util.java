package com.mygdx.game.util;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AnimatedDrawable;

import java.util.Arrays;
import java.util.stream.Stream;

public class Util {
    public static BitmapFont createFont() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        return font;
    }
    public static Skin createSkin() {
        Skin skin = new Skin();
        Texture circle = new Texture("circle.png");
        Texture rectangle = createTexture(1, 1, Color.WHITE);
        skin.add("whiteCircle", circle);
        skin.add("whiteRectangle", rectangle);
        skin.add("grayRect", coloredSprite(skin.get("whiteRectangle", Texture.class), Color.LIGHT_GRAY));
        skin.add("cursor", createTexture(1, 1, Color.WHITE));
        skin.add("red", new Color(0.827f, 0.137f, 0.047f, 1));
        skin.add("brown", new Color(0.396f, 0.239f, 0.149f, 1));
        skin.add("darkBrown", new Color(0.349f, 0.184f, 0.133f, 1));
        skin.add("gray", new Color(0.49f, 0.471f, 0.384f, 1));
        skin.add("redRectangle", Util.coloredSprite(rectangle, skin.getColor("red")));
        skin.add("blackRectangle", Util.coloredSprite(rectangle, Color.BLACK));
        skin.add("brownRectangle", Util.coloredSprite(rectangle, skin.getColor("brown")));
        skin.add("darkBrownRectangle", Util.coloredSprite(rectangle, skin.getColor("darkBrown")));
        skin.add("grayRectangle", Util.coloredSprite(rectangle, skin.getColor("gray")));
        skin.add("blackTransparentRectangle", Util.coloredSprite(rectangle, new Color(0, 0, 0, 0.8f)));
        skin.add("woodenBackground", new Texture("wooden_plank.png"));
        skin.add("darkWoodenBackground", new Texture("dark_wooden_plank.png"));
        skin.add("disabledWoodenBackground", new Texture("disabled_wooden_plank.png"));
        skin.add("background", new Texture("background.png"));

        Label.LabelStyle defaultLabelStyle = new Label.LabelStyle();
        defaultLabelStyle.font = createFont();
        defaultLabelStyle.fontColor = Color.BLACK;

        Label.LabelStyle whiteLabel = new Label.LabelStyle();
        whiteLabel.font = defaultLabelStyle.font;
        whiteLabel.fontColor = Color.WHITE;

        Button.ButtonStyle defaultButtonStyle = new Button.ButtonStyle();

        skin.add("white", whiteLabel);
        skin.add("default", defaultLabelStyle);
        skin.add("default", defaultButtonStyle);

        skin.add("amount", defaultLabelStyle);
        skin.add("price", defaultLabelStyle);
        skin.add("name", defaultLabelStyle);

        Button.ButtonStyle generatorButton = new Button.ButtonStyle(defaultButtonStyle);
        generatorButton.up = skin.getDrawable("woodenBackground");
        generatorButton.down = skin.getDrawable("darkWoodenBackground");
        generatorButton.disabled = skin.getDrawable("disabledWoodenBackground");
        generatorButton.pressedOffsetY = -1;

        skin.add("generator", generatorButton);

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = defaultLabelStyle.font;
        windowStyle.background = skin.getDrawable("background");

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = defaultLabelStyle.font;
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.background = skin.getDrawable("blackRectangle");
        textFieldStyle.cursor = skin.getDrawable("cursor");

        skin.add("default", textFieldStyle);
        skin.add("default", windowStyle);

        return skin;
    }
    public static Camera createCamera() {
        Camera camera = new OrthographicCamera(400, 400);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        return camera;
    }

    public static Viewport createViewport(Camera camera) {
        Viewport viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        camera.update();
        return viewport;
    }
    public static Texture createTexture(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static Sprite coloredSprite(Texture texture, Color color) {
        Sprite coloredSprite = new Sprite(texture);
        coloredSprite.setColor(color);
        return coloredSprite;
    }

    public static void fadeOut(Sound sound, long soundId, Action onStop) {
        Timer.Task task = new Timer.Task() {
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

    public static AnimatedDrawable animation(String path, int tileWidth, int tileHeight, int amount, float frameDuration) {
        TextureRegion[] frames = new TextureRegion[amount];
        Texture img = new Texture(path);

        TextureRegion[][] tmpframes = TextureRegion.split(img, tileWidth, tileHeight);
        frames = Arrays.stream(tmpframes).flatMap(Arrays::stream).toArray(TextureRegion[]::new);

        Animation<TextureRegion> animation = new Animation<>(frameDuration, frames);

        return new AnimatedDrawable(animation);
    }
}
