package FinityStateMachine.Tower;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import GameObjects.Tower;

public class WaitingForEnemyState extends State
{
    private Tower tower;

    public WaitingForEnemyState(Tower tower)
    {
        this.tower = tower;
        super.stateID = StateID.TowerWaitingForEnemy;
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
