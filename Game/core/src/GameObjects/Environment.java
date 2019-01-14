package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public final class Environment extends GameObject
{
    public Environment(Vector2 position, Texture texture) {
        super(position, 0, texture);
        this.sprite.setScale(60/sprite.getHeight());
        this.sprite.setOrigin(0,0);
    }
}
