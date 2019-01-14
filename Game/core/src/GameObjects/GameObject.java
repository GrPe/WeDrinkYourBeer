package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    private Vector2 position;
    private float rotation;

    protected Sprite sprite;

    public GameObject(Vector2 position, float rotation, Texture texture)
    {
        this.position = position;
        this.rotation = rotation;
        if(texture != null)
        {
            this.sprite = new Sprite(texture);
            sprite.setScale(60.0f/sprite.getHeight());
            sprite.setOrigin(0,0);
        }
    }

    public void Render(Batch batch)
    {
        batch.draw(sprite, position.x, position.y,sprite.getOriginX(),sprite.getOriginY(),sprite.getWidth(),sprite.getHeight(),sprite.getScaleX(),sprite.getScaleY(), rotation);
    }

    public Vector2 GetPosition() {
        return position;
    }

    public void SetPosition(Vector2 position) {
        this.position = position;
    }

    public float GetRotation() {
        return rotation;
    }

    public void SetRotation(float rotation) {
        this.rotation = rotation;
    }
}
