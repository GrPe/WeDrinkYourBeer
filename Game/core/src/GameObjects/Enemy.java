package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Enemy extends GameObject implements Drawable
{

    //Animation<TextureRegion> walkAnimation;
    Sprite temp;

    private int hp;
    private float speed;
    private int damage;
    private Vector2[] navLink;
    private int currectTarget;

    public Enemy(Vector2 position, float rotation, Texture texture, Vector2[] navLink, int hp, float speed, int damage) {
        super(position, rotation);
        temp = new Sprite(texture);
        this.navLink = navLink;
        this.damage = damage;
        this.speed = speed;
        this.hp = hp;
        this.currectTarget = 0;
    }

    @Override
    public Sprite getDrawingSprite()
    {
        return temp;
    }

    @Override
    public float getX() {
        return super.getPosition().x;
    }

    @Override
    public float getY() {
        return super.getPosition().y;
    }

    public void dealDamage(int damage)
    {
        hp -= damage;
    }

    public void move()
    {
        float destX = navLink[currectTarget].x - super.getPosition().x;
        float destY = navLink[currectTarget].y - super.getPosition().y;

        float dist = (float)Math.sqrt(destX * destX + destY * destY);

        destX = destX/dist;
        destY = destY/dist;

        super.setPosition(new Vector2(super.getPosition().x + destX * speed * Gdx.graphics.getDeltaTime(),
                super.getPosition().y + destY * speed * Gdx.graphics.getDeltaTime()));
    }

    public int getDamage()
    {
        return damage;
    }

    public boolean isDead()
    {
        return hp < 0;
    }

}
