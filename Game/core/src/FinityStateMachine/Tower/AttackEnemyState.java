package FinityStateMachine.Tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

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
        counter = 0;
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

            if(!tower.IsTargetInRange(tower.GetTarget().GetPosition()))
            {
                tower.GetStateMachine().PerformTransition(Transition.TowerWaitingFor);
                return;
            }

            SetRotationToTarget(tower.GetTarget().GetPosition());

            tower.Fire();
            tower.GetTarget().DealDamage(tower.GetDamage());
            counter = tower.GetFireSpeed();
        }
    }

    private void SetRotationToTarget(Vector2 target)
    {
        double angle = Math.toDegrees(Math.atan2(tower.GetPosition().x - target.x,target.y-tower.GetPosition().y));
        tower.SetRotation((float)angle);
    }
}
