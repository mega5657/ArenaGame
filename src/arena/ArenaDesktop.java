package arena;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class ArenaDesktop {

    public static void main(String[] argv) {
        new LwjglApplication(new Arena(), "ArenaPrototype", 640, 480, false);
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "ArenaPrototype";
        config.width = 650;
        config.height = 450;
    }
}
