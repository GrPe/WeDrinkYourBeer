package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class SpawnPoint extends GameObject implements Drawable
{
    private Sprite sprite;

    public SpawnPoint(Vector2 position, float rotation, Texture texture) {
        super(position, rotation);
        this.sprite = new Sprite(texture);
    }

    @Override
    public Sprite getDrawingSprite() {
        return sprite;
    }

    @Override
    public float getX() {
        return super.getPosition().x;
    }

    @Override
    public float getY() {
        return super.getPosition().y;
    }
}
