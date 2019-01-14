package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends GameObject
{
    private float speed;
    private Vector2 destination;

    public Bullet(Vector2 position, float rotation, Texture texture, float speed, Vector2 destination) {
        super(position, rotation, texture);
        super.sprite.setScale(0.5f);
        super.sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
        this.speed = speed;
        this.destination = destination;
    }

    public void Update()
    {
        Move();
    }

    private void Move()
    {
        float destX = destination.x - super.GetPosition().x;
        float destY = destination.y - super.GetPosition().y;

        float dist = (float)Math.sqrt(destX * destX + destY * destY);

        destX = destX/dist;
        destY = destY/dist;

        super.SetPosition(new Vector2(super.GetPosition().x + destX * speed * Gdx.graphics.getDeltaTime(),
                super.GetPosition().y + destY * speed * Gdx.graphics.getDeltaTime()));
    }

    public boolean CanBeDestroyed()
    {
        return super.GetPosition().epsilonEquals(destination,10);
    }
}
