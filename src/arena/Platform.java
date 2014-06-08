/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arena;

/**
 *
 * @author Atog
 */
public class Platform extends Object {

    public Platform(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.bounds.x = this.position.x;
        this.bounds.y = this.position.y;
        this.objectType = 0; //solid
    }
}
