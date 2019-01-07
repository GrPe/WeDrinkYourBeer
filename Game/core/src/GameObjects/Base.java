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

    public int GetHp() {
        return hp;
    }

    public int GetMaxHp() {return maxHp;}

    public void SetHp(int hp) {
        this.maxHp = hp;
        this.hp = hp;
    }

    public void DecreaseHp(int hp)
    {
        this.hp -= hp;
    }

    public boolean IsDead()
    {
        return hp < 0;
    }

    public void IncreaseHp(int hp)
    {
        this.hp += hp;
    }
}
