/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.awt.Font;

/**
 *
 * @author Atog
 */
public class GameScreen implements Screen {

    boolean debug = true;
    String position;
    String acceleration;
    String velocity;
    String playerState;
    SpriteBatch batcher;
    ShapeRenderer shapeBatcher;
    OrthographicCamera cam;
    InputHandler input;
    GameWorld world;
    BitmapFont debugFont;
    //input booleans
    boolean W_RELEASED;
    boolean S_RELEASED;
    boolean D_RELEASED;
    boolean A_RELEASED;
    boolean J_RELEASED;
    boolean K_RELEASED;
    boolean SPACE_RELEASED;
    boolean W_DOWN;
    boolean S_DOWN;
    boolean D_DOWN;
    boolean A_DOWN;
    boolean J_DOWN;
    boolean K_DOWN;
    boolean SPACE_DOWN;
    //end input booleans
    static int funTime = 0;
    float stateTime = 0;
    boolean stepAllowed = true;

    public GameScreen(Game game) {
        batcher = new SpriteBatch();

        shapeBatcher = new ShapeRenderer();
        cam = new OrthographicCamera(650, 450);
        cam.position.set(650 / 2, 450 / 2, 0);

        //      Gdx.input.setInputProcessor(null);
        cam.position.set(650 / 2, 450 / 2, 0);

        world = new GameWorld();
        debugFont = new BitmapFont();

        debugFont.setColor(Color.BLACK);


    } // end constructor

    @Override
    public void render(float deltaTime) {
        inputUpdate();
        update(deltaTime);
        draw();
    }

    public void draw() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        position = ("Position = " + world.player.position + "");
        acceleration = ("Acceleration = " + world.player.acceleration + "");
        velocity = ("Velocity = " + world.player.velocity + "");
        playerState = ("Player State = " + world.player.state + "");

        batcher.disableBlending();
        batcher.begin();
        batcher.draw(AssetLoader.background, 0, 0);
        batcher.end();

        batcher.enableBlending();
        batcher.begin();
        batcher.draw(world.player.currentSprite, world.player.position.x, world.player.position.y);
        debugFont.draw(batcher, position, (650 - 220), (450 - 100));
        debugFont.draw(batcher, acceleration, (650 - 220), (450 - 115));
        debugFont.draw(batcher, velocity, (650 - 220), (450 - 130));
        debugFont.draw(batcher, playerState, (650 - 220), (450 - 145));
        batcher.end();



        if (debug) {
            shapeBatcher.begin(ShapeRenderer.ShapeType.Rectangle);
            shapeBatcher.setColor(Color.BLACK);
            // shapeBatcher.rect(player.position.x, player.position.y, 20, 20);
           // renderRectangle(world.player.bounds);
            renderRectangle(world.centerPlatform);
            renderRectangle(world.bottomBoundary);
            renderRectangle(world.topLeftPlatform);
            renderRectangle(world.topRightPlatform);

            shapeBatcher.end();
        }
    }

    private void update(float deltaTime) {
        world.player.acceleration.x = 0;
        world.player.acceleration.y = 0;
        if (W_DOWN && !W_RELEASED) {
            world.player.jump();


        }

        if (S_DOWN && !S_RELEASED) {
            world.player.duck();
        }

        if (A_DOWN && !A_RELEASED) {

            world.player.moveLeft();


        }

        if (D_DOWN && !D_RELEASED) {

            world.player.moveRight();

        }


        if (D_RELEASED && !D_DOWN) {
        }

        if (SPACE_DOWN && !SPACE_RELEASED) {
            if (stepAllowed) {
                funTime++;
                toggleDebug();
                stepAllowed = false;
            }
        }


        if (SPACE_RELEASED && !SPACE_DOWN) {
        }
        //   System.out.println(world.player.acceleration.x);
        // System.out.println(world.player.position.x);
        if (funTime > 3) {
            funTime = 0;
        }
        stateTime += deltaTime;

        if (stateTime > .5) {
            stepAllowed = true;
            stateTime = 0;
        }

        //System.out.println(stateTime);
        world.update(deltaTime);

    }

    private void toggleDebug() {
        {
            if (debug) {
                debug = false;
            }
            if (!debug) {
                debug = true;
            }
        }
    }

    public void inputUpdate() {
        W_RELEASED = true;
        S_RELEASED = true;
        A_RELEASED = true;
        D_RELEASED = true;
        J_RELEASED = true;
        K_RELEASED = true;
        SPACE_RELEASED = true;

        //start of keys pressed statements
        if (Gdx.input.isKeyPressed(Keys.W)) {
            W_DOWN = true;
            W_RELEASED = false;
        }

        if (Gdx.input.isKeyPressed(Keys.S)) {
            S_DOWN = true;
            S_RELEASED = false;
        }


        if (Gdx.input.isKeyPressed(Keys.A)) {
            A_DOWN = true;
            A_RELEASED = false;
        }

        if (Gdx.input.isKeyPressed(Keys.D)) {
            D_DOWN = true;
            D_RELEASED = false;
        }

        if (Gdx.input.isKeyPressed(Keys.J)) {
            J_DOWN = true;
            J_RELEASED = false;
        }

        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            SPACE_DOWN = true;
            SPACE_RELEASED = false;
        }

        //start of key release statements

        if (!Gdx.input.isKeyPressed(Keys.W)) {
            W_RELEASED = true;
            W_DOWN = false;
        }

        if (!Gdx.input.isKeyPressed(Keys.S)) {
            S_RELEASED = true;
            S_DOWN = false;
        }


        if (!Gdx.input.isKeyPressed(Keys.A)) {
            A_RELEASED = true;
            A_DOWN = false;
        }

        if (!Gdx.input.isKeyPressed(Keys.D)) {
            D_RELEASED = true;
            D_DOWN = false;
        }

        if (!Gdx.input.isKeyPressed(Keys.J)) {
            J_RELEASED = true;
            J_DOWN = false;
        }

        if (!Gdx.input.isKeyPressed(Keys.SPACE)) {
            SPACE_RELEASED = true;
            SPACE_DOWN = false;
        }




    } //end method input update

    public void renderRectangle(Rectangle rect) {
        shapeBatcher.rect(rect.x, rect.y, rect.width, rect.height);
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
