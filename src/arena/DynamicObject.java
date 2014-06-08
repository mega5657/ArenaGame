/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

/**
 *
 * @author Atog
 */
public class DynamicObject extends Object {

    float velocity = 0f;
    float horizontalAccel = 0f;
    float verticalAccel = 0f;

    public DynamicObject(int x, int y, int height, int width) {
        super(x, y, height, width);
    }
}