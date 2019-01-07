package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public final class Environment extends GameObject implements Drawable
{
    private Sprite sprite;

    public Environment(Vector2 position, Texture texture) {
        super(position, 0);
        this.sprite = new Sprite(texture);
    }

    @Override
    public Sprite getDrawingSprite() {
        return sprite;
    }

    @Override
    public float GetX() {
        return super.GetPosition().x;
    }

    @Override
    public float GetY() {
        return super.GetPosition().y;
    }
}
