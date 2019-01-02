package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import FinityStateMachine.StateID;
import FinityStateMachine.StateMachine;
import FinityStateMachine.Tower.AttackEnemyState;
import FinityStateMachine.Tower.WaitingForEnemyState;
import FinityStateMachine.Transition;

public class Tower extends GameObject implements Drawable
{
    private StateMachine stateMachine;
    private Sprite sprite;
    private float range;
    private int fireSpeed;
    private int damage;

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

        WaitingForEnemyState waitingForEnemyState = new WaitingForEnemyState(this);
        AttackEnemyState attackEnemyState = new AttackEnemyState(this);

        waitingForEnemyState.AddTransition(Transition.TowerAttack, StateID.TowerAttackEnemy);
        attackEnemyState.AddTransition(Transition.TowerWaitingFor, StateID.TowerWaitingForEnemy);

        stateMachine.AddState(waitingForEnemyState);
        stateMachine.AddState(attackEnemyState);
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
