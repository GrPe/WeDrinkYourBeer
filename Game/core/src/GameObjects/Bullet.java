package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends GameObject implements Drawable
{
    private Sprite sprite;
    private float speed;
    private Vector2 destination;

    public Bullet(Vector2 position, float rotation, Texture texture, float speed, Vector2 destination) {
        super(position, rotation);
        this.sprite =  new Sprite(texture);
        this.speed = speed;
        this.destination = destination;
    }

    public void Update()
    {
        Move();
    }

    private void Move()
    {
        float destX = destination.x - GetX();
        float destY = destination.y - GetY();

        float dist = (float)Math.sqrt(destX * destX + destY * destY);

        destX = destX/dist;
        destY = destY/dist;

        super.setPosition(new Vector2(getX() + destX * speed * Gdx.graphics.getDeltaTime(),
                getY() + destY * speed * Gdx.graphics.getDeltaTime()));
    }

    public boolean CanBeDestroyed()
    {
        return super.GetPosition().epsilonEquals(destination,10);
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
