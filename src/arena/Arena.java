/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

/**
 *
 * @author Atog
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;

public class Arena extends Game {

    boolean firstTimeCreate = true;
    FPSLogger fps;

    @Override
    public void create() {
        fps = new FPSLogger();
        AssetLoader.load();
        setScreen(new GameScreen(this));
     //   fps.log();
    }

    @Override
    public void render() {
        super.render();
        fps.log();
    }
}
