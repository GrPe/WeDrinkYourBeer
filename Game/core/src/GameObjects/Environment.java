package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public final class Environment extends GameObject implements Drawable
{
    private Sprite sprite;

    public Environment(Vector2 position, float rotation, Texture texture) {
        super(position, rotation);
        this.sprite.setTexture(texture);
    }

    @Override
    public Sprite getDrawingSprite() {
        return null;
    }

    public void setPosition(Vector2 position)
    {
        super.setPosition(position);
    }
}
