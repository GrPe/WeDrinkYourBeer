package FinityStateMachine.Tower;

import com.badlogic.gdx.Gdx;

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
        Gdx.app.log("test", "Enter Waiting");
    }

    @Override
    public void DoBeforeLeaving()
    {
        Gdx.app.log("test", "Exit Waiting");
    }

    @Override
    public < E > void Act(ArrayList<E> list)
    {
        if(tower.getTarget() != null) return;
        for(E enemy : list)
        {
            if(((Enemy)enemy).getPosition().epsilonEquals(tower.getPosition(),tower.getRange()))
            {
                tower.setTarget((Enemy)enemy);
                tower.getStateMachine().PerformTransition(Transition.TowerAttack);
                return;
            }
        }
    }

}
