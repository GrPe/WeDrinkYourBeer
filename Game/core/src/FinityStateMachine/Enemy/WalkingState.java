package FinityStateMachine.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import GameObjects.Enemy;

public class WalkingState extends State {

    private Enemy enemy;
    private Vector2 currentTarget;
    private Vector2 finalTarget;

    public WalkingState(Enemy enemy)
    {
        this.enemy = enemy;
        stateID = StateID.EnemyWalking;

        currentTarget = enemy.getNextTarget();
        finalTarget = enemy.getFinalTarget();
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
        if(!isInBase())
        {
            move();
        }
        else
        {
            enemy.SetInBase();
        }
    }


    private void move()
    {
        float destX = currentTarget.x - enemy.getX();
        float destY = currentTarget.y - enemy.getY();

        float dist = (float)Math.sqrt(destX * destX + destY * destY);

        destX = destX/dist;
        destY = destY/dist;

        enemy.setPosition(new Vector2(enemy.getX() + destX * enemy.getSpeed() * Gdx.graphics.getDeltaTime(),
                enemy.getY() + destY * enemy.getSpeed() * Gdx.graphics.getDeltaTime()));

        ChangePosition();
    }


    private void ChangePosition()
    {
        if(CheckPosition(1, currentTarget))
        {
            currentTarget = enemy.getNextTarget();
        }
    }

    private boolean CheckPosition(int epsilon, Vector2 target)
    {
        return enemy.getPosition().epsilonEquals(target,epsilon);
    }

    private boolean isInBase()
    {
        return CheckPosition(2,finalTarget);
    }

}
