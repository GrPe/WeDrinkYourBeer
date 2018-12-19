package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Base extends GameObject implements Drawable
{
    private Sprite sprite;
    private int maxHp;
    private int hp;

    public Base(Vector2 position, Texture texture, int hp) {
        super(position, 0);
        this.sprite = new Sprite(texture);
        this.hp = hp;
        this.maxHp = hp;
    }

    @Override
    public Sprite getDrawingSprite() {
        return sprite;
    }

    @Override
    public float getX() {
        return super.getPosition().x;
    }

    @Override
    public float getY() {
        return super.getPosition().y;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {return maxHp;}

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void decreaseHp(int hp)
    {
        this.hp -= hp;
    }

    public boolean isDead()
    {
        return hp < 0;
    }

    public void increaseHp(int hp)
    {
        this.hp += hp;
    }
}
