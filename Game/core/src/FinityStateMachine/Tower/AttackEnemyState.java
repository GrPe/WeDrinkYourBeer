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
        counter = tower.getFireSpeed();
    }

    @Override
    public void DoBeforeLeaving() {
        tower.setTarget(null);
    }

    @Override
    public void Act()
    {
        counter -= Gdx.graphics.getDeltaTime();

        if(counter <= 0)
        {
            if(tower.getTarget() == null || tower.getTarget().isDead())
            {
                tower.getStateMachine().PerformTransition(Transition.TowerWaitingFor);
                return;
            }

            tower.Fire();
            tower.getTarget().dealDamage(tower.getDamage());
            counter = tower.getFireSpeed();
        }
    }
}
