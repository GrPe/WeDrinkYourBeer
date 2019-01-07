package FinityStateMachine.Tower;

import com.badlogic.gdx.Gdx;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import FinityStateMachine.Transition;
import GameObjects.Tower;

public class AttackEnemyState extends State
{
    private Tower tower;
    private float counter = 0;

    public AttackEnemyState(Tower tower)
    {
        this.tower = tower;
        super.stateID = StateID.TowerAttackEnemy;
    }

    @Override
    public void DoBeforeEntering() {
        counter = tower.GetFireSpeed();
    }

    @Override
    public void DoBeforeLeaving() {
        tower.SetTarget(null);
    }

    @Override
    public void Act()
    {
        counter -= Gdx.graphics.getDeltaTime();

        if(counter <= 0)
        {
            if(tower.GetTarget() == null || tower.GetTarget().IsDead())
            {
                tower.GetStateMachine().PerformTransition(Transition.TowerWaitingFor);
                return;
            }

            tower.Fire();
            tower.GetTarget().DealDamage(tower.GetDamage());
            counter = tower.GetFireSpeed();
        }
    }
}
