/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import java.math.BigDecimal;

/**
 *
 * @author Atog
 */
//beginner documentation https://github.com/libgdx/libgdx/wiki/Box2d
public class GameWorld {

    Player player;
    Rectangle centerPlatform;
    Rectangle topLeftPlatform;
    Rectangle topRightPlatform;
    Rectangle bottomBoundary;
    Rectangle leftBoundary;
    Rectangle rightBoundary;
    Rectangle topBoundary;
    CollisionTester collisionCheck;
    Vector2 dimension;
    Vector2 gravity;
    double friction = .075;
    boolean movingRight = false;
    boolean movingLeft = false;

    public GameWorld() {

        gravity = new Vector2(0, -10);
        player = new Player(255, 150, 0, 0);
        dimension = new Vector2(650, 450);
        centerPlatform = new Rectangle(250, 100, 75, 20);
        topLeftPlatform = new Rectangle(100, 250, 75, 20);
        topRightPlatform = new Rectangle(450, 300, 90, 20);
        bottomBoundary = new Rectangle(0, 0, 650 * 3, 2);
        leftBoundary = new Rectangle();
        rightBoundary = new Rectangle();
        topBoundary = new Rectangle();


    }

    public void update(float deltaTime) {
        updatePlayer(deltaTime);


    }

    private void checkBottomBoundary() {
        //   if (player.position.y > 0 || player.position.y == 0) {
        if (CollisionTester.overlapRectangles(player.bounds, bottomBoundary)) {
            player.position.y = 0;
            player.velocity.y = 0;
        }
    }

    private void checkCollision(Rectangle rect) {
        if (CollisionTester.overlapRectangles(player.bounds, rect)) {
            player.velocity.y = 0;
            player.position.y = rect.y + rect.height;
            if (player.position.y > rect.y || player.position.y == rect.y) {
                //   player.velocity.y = 0;
                //  player.position.y = rect.y + rect.height;
            }

        }
    }

    private void updatePlayer(float deltaTime) {

        player.velocity.x += gravity.x * deltaTime / 2;
        player.velocity.y += gravity.y * deltaTime / 2;

        player.velocity.x += player.acceleration.x * deltaTime / 2;
        player.velocity.y += player.acceleration.y * deltaTime / 2;

        player.position.x += player.velocity.x;
        player.position.y += player.velocity.y;

        player.velocity.x += gravity.x * deltaTime / 2;
        player.velocity.y += gravity.y * deltaTime / 2;

        player.velocity.x += player.acceleration.x * deltaTime / 2;
        player.velocity.y += player.acceleration.y * deltaTime / 2;

        capVelocity();
        checkCollision(bottomBoundary);

        //  player.velocity.add(player.acceleration.x * deltaTime, player.acceleration.y * deltaTime);
        checkCollision(centerPlatform);
        checkCollision(topRightPlatform);
        checkCollision(topLeftPlatform);
        calculateFriction();
        player.update(deltaTime);



    }

    private void draw() {
    }

    private void capVelocity() {
        if (player.velocity.x > 5) {
            player.velocity.x = 5;
        }

        if (player.velocity.x < -5) {
            player.velocity.x = -5;
        }

        if (player.velocity.y > 15) {
            player.velocity.y = 15;
        }
        if (player.velocity.y < -15) {
            player.velocity.y = -15;
        }
    }

    private void checkBottomCollision() {
        if (CollisionTester.overlapRectangles(player.bounds, bottomBoundary)) {
            player.position.y = bottomBoundary.y;
        }


    }

    private void calculateFriction() {
        movingRight = false;
        movingLeft = false;
        if (player.acceleration.x == 0 && player.velocity.x != 0) {
            if (player.velocity.x > 0) {
                player.velocity.x -= friction;
                movingRight = true;
            } // end of player.velocity.x > 0

            if (movingRight && player.velocity.x < 0) {
                player.velocity.x = 0;
            }// end of if movingRight

            if (player.velocity.x < 0) {
                player.velocity.x += friction;
                movingLeft = true;
            }

            if (movingLeft && player.velocity.x > 0) {
                player.velocity.x = 0;
            }
        } //end of player.acceleration.x == 0;



        // }// end of first if statement
    }//end of computeTraction

    private float round(float floaty, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(floaty));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();


    }
}