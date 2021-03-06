package GameObjects.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import GameObjects.GameObject;

public class Image extends GameObject
{
    public Image(Vector2 position, Texture texture) {
        super(position,0,texture);
    }

    public Image(Vector2 position, Texture texture, float scaleX, float scaleY) {
        super(position, 0, texture);
        this.sprite.setScale(scaleX,scaleY);
        this.sprite.setOrigin(0,0);
    }
}
