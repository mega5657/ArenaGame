/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Atog
 */
public class Player extends DynamicObject {

    public int state = 2;
    public final int PLAYER_IDLE = 1;
    public final int PLAYER_FALLING = 2;
    public final int PLAYER_HIT = 3;
    public final int PLAYER_HIT_FALLING = 5;
    public final int PLAYER_RUNNING = 6;
    public final int PLAYER_FAST_RUNNING = 7;
    public final int PLAYER_JUMP = 8;
    public final int PLAYER_JUMP_HIT = 9;
    public final int PLAYER_DUCK = 10;
    public int direction = 0;
    public final int PLAYER_RIGHT = 0;
    public final int PLAYER_LEFT = 1;
    boolean slowingDown = false;
    private int THISintisathing= 2341234;
    Vector2 acceleration;
    Vector2 velocity;
    TextureRegion currentSprite;
    TextureRegion slice1;
    TextureRegion slice2;
    TextureRegion slice3;
    TextureRegion standing1;
    TextureRegion mario;
    Animation marioRun;
    Animation marioIdle;
    Animation currentAnim;
    Animation marioJump;
    Animation marioFall;
    Animation marioDuckus;
    float stateTime;
    int CURRENT_MOVE_SPEED = 6;
    boolean freeMove = true;

    public Player(int x, int y, int height, int width) {
        super(x, y, height, width);
        this.bounds.x = x;
        this.bounds.y = y;
        this.bounds.width = 12;
        this.bounds.height = 25;
        acceleration = new Vector2();
        velocity = new Vector2(0, 0);
        loadAnims();
        currentSprite = new TextureRegion();
        currentSprite = mario;
        currentAnim = marioIdle;
        currentSprite.flip(true, false);

    }

    public void update(Float deltaTime) {
        bounds.x = position.x + 2;
        bounds.y = position.y;
        determineState();
        currentSprite = currentAnim.getKeyFrame(stateTime, 0);
        stateTime += deltaTime;
        if (stateTime > 10)
        {stateTime = 0;}

    }

    private void determineState() {

        if (velocity.y == 0) {
            if (velocity.x == 0) {
                currentAnim = marioIdle;
                state = PLAYER_IDLE;
            }
            if (velocity.x != 0) {
                currentAnim = marioRun;
                state = PLAYER_RUNNING;
            }
        }

        if (velocity.y != 0) {
            if (velocity.y > 1) {
                currentAnim = marioJump;
            }
            if (velocity.y < -1) {
                currentAnim = marioFall;
            }
        }


    }

    public void loadAnims() {

        standing1 = new TextureRegion(AssetLoader.playerSheet, 12, 23, 16, 56);
        slice1 = new TextureRegion(AssetLoader.playerSheet, 32, 23, 48, 64);
        slice2 = new TextureRegion(AssetLoader.playerSheet, 82, 20, 47, 59);
        slice3 = new TextureRegion(AssetLoader.playerSheet, 114, 25, 50, 54);
        mario = new TextureRegion(AssetLoader.marioSheet, 4, 60, 15, 28);
        marioRun = new Animation(.1f, new TextureRegion(AssetLoader.marioSheet, 21, 60, 15, 28), new TextureRegion(AssetLoader.marioSheet, 38, 60, 15, 28), new TextureRegion(AssetLoader.marioSheet, 55, 60, 15, 28),
                new TextureRegion(AssetLoader.marioSheet, 72, 60, 15, 28), new TextureRegion(AssetLoader.marioSheet, 89, 60, 15, 28));
        marioIdle = new Animation(1, new TextureRegion(AssetLoader.marioSheet, 4, 60, 15, 28));
        marioJump = new Animation(1, new TextureRegion(AssetLoader.marioSheet, 144, 59, 17, 31));
        marioFall = new Animation(1, new TextureRegion(AssetLoader.marioSheet, 168, 60, 17, 31));
        marioDuckus = new Animation(1, new TextureRegion(AssetLoader.marioSheet, 205, 76, 16, 15));
    }

    public void jump() {

        if (freeMove) {
            if (acceleration.y == 0 && velocity.y == 0) {
                velocity.y = 0;
                this.acceleration.y = 400;
            }
        }
    }

    public void moveLeft() {
        if (freeMove) {
            this.acceleration.x = -CURRENT_MOVE_SPEED;
        }
    }

    public void moveRight() {
        if (freeMove) {
            this.acceleration.x = CURRENT_MOVE_SPEED;
        }
    }

    public void duck() {
        state = PLAYER_DUCK;
    }

    public void setState(int state) {
        this.state = state;
    }
}
