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
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
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

    public void Update(ArrayList<Enemy> enemies)
    {
        if(stateMachine.getCurrentStateID() == StateID.TowerWaitingForEnemy)
            stateMachine.getCurrentState().Act(enemies);
        else
            stateMachine.getCurrentState().Act();
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

    public float GetRange() {
        return range;
    }

    public int GetFireSpeed() {
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
}