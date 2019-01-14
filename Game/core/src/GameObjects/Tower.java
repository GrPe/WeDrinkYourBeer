package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import FinityStateMachine.StateID;
import FinityStateMachine.StateMachine;
import FinityStateMachine.Tower.AttackEnemyState;
import FinityStateMachine.Tower.WaitingForEnemyState;
import FinityStateMachine.Transition;

public class Tower extends GameObject
{
    private StateMachine stateMachine;
    private float range;
    private float fireSpeed;
    private int damage;
    private int cost;

    private Enemy target;

    public Tower(Vector2 position, float rotation, Texture texture, float range, float fireSpeed, int damage, int cost) {
        super(position, rotation, texture);
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
        this.range = range;
        this.fireSpeed = fireSpeed;
        this.damage = damage;
        this.cost = cost;
        target = null;
        InitStateMachine();
    }

    public void Fire()
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

    protected float GetSpriteWidth()
    {
        return sprite.getWidth();
    }

    protected float GetSpriteHeight()
    {
        return sprite.getHeight();
    }

    public float GetFireSpeed() {
        return fireSpeed;
    }

    public int GetDamage() {
        return damage;
    }

    public Enemy GetTarget() {
        return target;
    }

    public void SetTarget(Enemy target) {
        this.target = target;
    }

    public StateMachine GetStateMachine() {
        return stateMachine;
    }

    @Override
    public void SetRotation(float rotation) {
        super.SetRotation(rotation);
        sprite.setRotation(rotation);
    }

    public boolean IsTargetInRange(Vector2 target)
    {
        return target.epsilonEquals(GetPosition(),range);
    }

    public int GetCost() {
        return cost;
    }
}