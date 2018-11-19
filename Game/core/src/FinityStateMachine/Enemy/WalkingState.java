package FinityStateMachine.Enemy;

import com.badlogic.gdx.math.Vector2;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import GameObjects.Enemy;

public class WalkingState extends State {

    private Enemy enemy;

    public WalkingState(Enemy enemy)
    {
        this.enemy = enemy;
        stateID = StateID.EnemyWalking;
    }

    @Override
    public void DoBeforeEntering()
    {

    }

    @Override
    public void DoBeforeLeaving()
    {

    }

    @Override
    public void Act()
    {
        enemy.move();
    }

}
