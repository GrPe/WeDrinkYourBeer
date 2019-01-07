package FinityStateMachine.Tower;

import java.util.ArrayList;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import FinityStateMachine.Transition;
import GameObjects.Enemy;
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
    public < E > void Act(ArrayList<E> list)
    {
        if(tower.GetTarget() != null) return;
        for(E enemy : list)
        {
            if(((Enemy)enemy).GetPosition().epsilonEquals(tower.GetPosition(),tower.GetRange()))
            {
                tower.SetTarget((Enemy)enemy);
                tower.GetStateMachine().PerformTransition(Transition.TowerAttack);
                return;
            }
        }
    }

}
