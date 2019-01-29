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

        currentTarget = enemy.GetNextTarget();
        finalTarget = enemy.GetFinalTarget();
        SetDirection();
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
        if(!IsInBase())
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
        float destX = currentTarget.x - enemy.GetPosition().x;
        float destY = currentTarget.y - enemy.GetPosition().y;

        float dist = (float)Math.sqrt(destX * destX + destY * destY);

        destX = destX/dist;
        destY = destY/dist;

        enemy.SetPosition(new Vector2(enemy.GetPosition().x + destX * enemy.GetSpeed() * Gdx.graphics.getDeltaTime(),
                enemy.GetPosition().y + destY * enemy.GetSpeed() * Gdx.graphics.getDeltaTime()));

        ChangePosition();
    }


    private void ChangePosition()
    {
        if(CheckPosition(1, currentTarget))
        {
            currentTarget = enemy.GetNextTarget();
            SetDirection();
        }
    }

    private void SetDirection()
    {
        if(currentTarget.x + 30 < enemy.GetPosition().x)
            enemy.SwitchDirection(Direction.Left);
        else if(currentTarget.x - 30 > enemy.GetPosition().x)
            enemy.SwitchDirection(Direction.Right);
        else if(currentTarget.y - 30 > enemy.GetPosition().y)
            enemy.SwitchDirection(Direction.Up);
        else
            enemy.SwitchDirection(Direction.Down);
    }

    private boolean CheckPosition(int epsilon, Vector2 target)
    {
        return enemy.GetPosition().epsilonEquals(target,epsilon);
    }

    private boolean IsInBase()
    {
        return CheckPosition(2,finalTarget);
    }

}
