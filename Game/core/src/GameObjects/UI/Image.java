package GameObjects.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import GameObjects.GameObject;
import GameObjects.Drawable;

public class Image extends GameObject implements Drawable
{
    private Sprite sprite;

    public Image(Vector2 position, Texture texture) {
        this(position,texture,60.0f/texture.getHeight(),60.0f/texture.getHeight());
    }

    public Image(Vector2 position, Texture texture, float scaleX, float scaleY) {
        super(position, 0);
        this.sprite = new Sprite(texture);
        this.sprite.setScale(scaleX,scaleY);
        this.sprite.setOrigin(0,0);
    }

    @Override
    public void Render(Batch batch)
    {
        batch.draw(sprite, GetX(), GetY(),sprite.getOriginX(),sprite.getOriginY(),sprite.getWidth(),sprite.getHeight()
                ,sprite.getScaleX(),sprite.getScaleY(), GetRotation());
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
