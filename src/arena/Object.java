/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Atog
 */
public class Object {

    Vector2 position;
    Rectangle bounds;
    int objectType;
    //0 = solid, 1 = not solid? 2 = background 3 = enemy 

    public Object(int x, int y, int height, int width) {
        position = new Vector2(x, y);
        bounds = new Rectangle(position.x, position.y, height, width);
    }

    public void update() {
        
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
