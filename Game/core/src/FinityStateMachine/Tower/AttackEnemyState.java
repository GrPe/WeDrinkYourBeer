package FinityStateMachine.Tower;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import GameObjects.Tower;

public class AttackEnemyState extends State
{
    private Tower tower;

    public AttackEnemyState(Tower tower)
    {
        this.tower = tower;
        super.stateID = StateID.TowerAttackEnemy;
    }


    @Override
    public void DoBeforeEntering() {

    }

    @Override
    public void DoBeforeLeaving() {
    }

    @Override
    public void Act() {
    }
}
