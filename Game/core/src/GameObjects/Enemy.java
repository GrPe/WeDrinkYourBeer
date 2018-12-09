package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import FinityStateMachine.Enemy.WalkingState;
import FinityStateMachine.StateID;
import FinityStateMachine.StateMachine;
import FinityStateMachine.Transition;


public class Enemy extends GameObject implements Drawable
{
    private Sprite sprite;
    private int hp;
    private float speed;
    private int damage;
    private Vector2[] navLink;
    private int currentTarget;
    private boolean isInBase;

    private StateMachine stateMachine;

    public Enemy(Vector2 position, float rotation, Texture texture, Vector2[] navLink, int hp, float speed, int damage) {
        super(position, rotation);
        sprite = new Sprite(texture);
        this.navLink = navLink;
        this.damage = damage;
        this.speed = speed;
        this.hp = hp;
        this.currentTarget = 0;
        isInBase = false;
        InitStateMachine();
    }

    @Override
    public Sprite getDrawingSprite()
    {
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

    public float getSpeed() {return speed;}

    public void dealDamage(int damage)
    {
        hp -= damage;
    }

    public int getDamage()
    {
        return damage;
    }

    public boolean isDead()
    {
        return hp < 0;
    }

    public Vector2 getNextTarget()
    {
        return navLink[currentTarget++];
    }

    public Vector2 getFinalTarget() {return navLink[navLink.length-1];}

    public void SetInBase()
    {
        isInBase = true;
    }

    public boolean getIsInBase()
    {
        return isInBase;
    }

    private void InitStateMachine()
    {
        stateMachine = new StateMachine();

        WalkingState walkingState = new WalkingState(this);
        walkingState.AddTransition(Transition.EnemyDie, StateID.EnemyDying);

        stateMachine.AddState(walkingState);
    }

    public void Update()
    {
        stateMachine.getCurrentState().Act();
    }

    //only for state machine
    public void OnDie()
    {
        stateMachine.PerformTransition(Transition.EnemyDie);
    }

}
