package FinityStateMachine.Tower;

import com.badlogic.gdx.Gdx;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import FinityStateMachine.Transition;
import GameObjects.Tower;

public class AttackEnemyState extends State
{
    private Tower tower;
    float counter = 0;

    public AttackEnemyState(Tower tower)
    {
        this.tower = tower;
        super.stateID = StateID.TowerAttackEnemy;
    }


    @Override
    public void DoBeforeEntering() {
        Gdx.app.log("test", "Enter Attack");
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
        if(tower.getTarget().isDead()) tower.getStateMachine().PerformTransition(Transition.TowerWaitingFor);

        if(counter <= 0)
        {
            tower.getTarget().dealDamage(tower.getDamage());
            counter = tower.getFireSpeed();
        }
    }
}
