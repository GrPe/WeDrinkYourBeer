package GameObjects.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import GameObjects.Drawable;
import GameObjects.GameObject;

public class UIButton extends GameObject implements Drawable
{
    private Sprite sprite;

    public UIButton(Vector2 position, Texture texture) {
        super(position, 0);
        this.sprite = new Sprite(texture);
    }

    @Override
    public void Render(Batch batch)
    {
               batch.draw(sprite,GetX(),GetY());
    }

    @Override
    public float GetX() {
        return super.GetPosition().x;
    }

    @Override
    public float GetY() {
        return super.GetPosition().y;
    }

    public boolean IsClicked(Vector2 position)
    {
        return (position.x >= GetX() &&
                position.x <= GetX() + sprite.getWidth() &&
                position.y >= GetY() &&
                position.y <= GetY() + sprite.getHeight());
    }
}
