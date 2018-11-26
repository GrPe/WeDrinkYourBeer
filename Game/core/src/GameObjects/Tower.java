package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import FinityStateMachine.StateMachine;

public class Tower extends GameObject implements Drawable
{
    StateMachine stateMachine;
    private Sprite sprite;
    float range;
    int fireSpeed;
    int damage;

    public Tower(Vector2 position, float rotation, Texture texture, float range, int fireSpeed, int damage) {
        super(position, rotation);
        this.sprite = new Sprite(texture);
        this.range = range;
        this.fireSpeed = fireSpeed;
        this.damage = damage;
        InitStateMachine();
    }

    public void Update()
    {
        stateMachine.getCurrentState().Act();
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

    private void InitStateMachine()
    {
        stateMachine = new StateMachine();
    }

    public float getRange() {
        return range;
    }

    public int getFireSpeed() {
        return fireSpeed;
    }

    public int getDamage() {
        return damage;
    }
}
