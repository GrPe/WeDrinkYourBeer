package FinityStateMachine.Enemy;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import GameObjects.Enemy;

public class DieState extends State
{
    private Enemy enemy;

    public DieState(Enemy enemy) {
        this.enemy = enemy;
        super.stateID = StateID.EnemyDying;
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
