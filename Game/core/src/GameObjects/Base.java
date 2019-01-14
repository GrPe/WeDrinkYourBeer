package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Base extends GameObject
{
    private int maxHp;
    private int hp;

    public Base(Vector2 position, Texture texture, int hp) {
        super(position, 0, texture);
        this.hp = hp;
        this.maxHp = hp;
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
