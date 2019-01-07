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
    public void Render(Batch batch) {
        batch.draw(sprite, GetX(), GetY(),sprite.getOriginX(),sprite.getOriginY(),sprite.getWidth(),sprite.getHeight(),sprite.getScaleX(),sprite.getScaleY(), GetRotation());
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
