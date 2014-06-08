package arena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Atog
 */
public class AssetLoader {

    static AssetManager manager;
    static Texture background;
    static Texture playerSheet;
    static Texture marioSheet;
    static TextureRegion player;
    static TextureRegion mario;
    static Animation playerIdle;
    static Animation playerMoveLeft;
    static Animation playerMoveRight;

    public static void load() {
        background = new Texture(Gdx.files.internal("data/background.png"));
        playerSheet = new Texture(Gdx.files.internal("data/playerSheet.png"));
        marioSheet = new Texture(Gdx.files.internal("data/marioSheet.png"));
        player = new TextureRegion(playerSheet, 9, 6, 15, 56);
        mario = new TextureRegion(marioSheet, 4, 87, 15, 28);
    }
}
