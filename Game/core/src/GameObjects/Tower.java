package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

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

    private Enemy target;

    public Tower(Vector2 position, float rotation, Texture texture, float range, int fireSpeed, int damage) {
        super(position, rotation);
        this.sprite = new Sprite(texture);
        this.range = range;
        this.fireSpeed = fireSpeed;
        this.damage = damage;
        target = null;
        InitStateMachine();
    }

    public void Fire()
    {
        //is virtual
    }

    public void RenderFire(Batch batch)
    {
        //is virtual
    }

    public void Update(ArrayList<Enemy> enemies)
    {
        if(stateMachine.getCurrentStateID() == StateID.TowerWaitingForEnemy)
            stateMachine.getCurrentState().Act(enemies);
        else
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

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }
}