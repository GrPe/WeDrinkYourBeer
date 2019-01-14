package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SpawnPoint extends GameObject
{
    public SpawnPoint(Vector2 position, Texture texture) {
        super(position, 0, texture);
        sprite.setScale(60.0f/400.0f);
        sprite.setOrigin(0,0);
    }
}
