package FinityStateMachine.Enemy;

import FinityStateMachine.State;
import FinityStateMachine.StateID;

public class WalkingState extends State {

    Enemy enemy;

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

    }

}
