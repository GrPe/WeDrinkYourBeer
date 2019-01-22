package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import FinityStateMachine.Enemy.WalkingState;
import FinityStateMachine.StateID;
import FinityStateMachine.StateMachine;
import FinityStateMachine.Transition;


public class Enemy extends GameObject
{
    private int hp;
    private float speed;
    private int damage;
    private ArrayList<Vector2> navLink;
    private int currentTarget;
    private boolean isInBase;

    private StateMachine stateMachine;

    public Enemy(Vector2 position, float rotation, Texture texture, ArrayList<Vector2> navLink, int hp, float speed, int damage) {
        super(position, rotation, texture);
        this.navLink = navLink;
        this.damage = damage;
        this.speed = speed;
        this.hp = hp;
        this.currentTarget = 0;
        isInBase = false;
        InitStateMachine();
    }

    public float GetSpeed() {return speed;}

    public void DealDamage(int damage)
    {
        hp -= damage;
    }

    public int GetDamage()
    {
        return damage;
    }

    public boolean IsDead()
    {
        return hp <= 0;
    }

    public Vector2 GetNextTarget()
    {
        if(navLink.size() > currentTarget)
            return navLink.get(currentTarget++);
        else
            return navLink.get(navLink.size() - 1);
    }

    public Vector2 GetFinalTarget() {return navLink.get(navLink.size()-1);}

    public void SetInBase()
    {
        isInBase = true;
    }

    public boolean GetIsInBase()
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
}