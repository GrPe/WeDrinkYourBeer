package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Enemy extends GameObject implements Drawable
{

    //Animation<TextureRegion> walkAnimation;
    Sprite temp;

    int hp;
    float speed;
    int damage;
    ArrayList<Vector2> navLink;
    int currectTarget;

    public Enemy(Vector2 position, float rotation, Texture texture, ArrayList<Vector2> navLink, int hp, float speed, int damage) {
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
