package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;

import FinityStateMachine.Enemy.Direction;
import FinityStateMachine.Enemy.WalkingState;
import FinityStateMachine.StateID;
import FinityStateMachine.StateMachine;
import FinityStateMachine.Transition;


public class Enemy extends GameObject
{
    private static final int FRAME_COLS = 4, FRAME_ROWS = 4;
    private HashMap<Direction,Animation<TextureRegion>> walkAnimation;
    private float frameTimer;
    private Direction walkDirection;

    private int hp;
    private float speed;
    private int damage;
    private ArrayList<Vector2> navLink;
    private int currentTarget;
    private boolean isInBase;

    private StateMachine stateMachine;

    public Enemy(Vector2 position, float rotation, Texture animationSheet, ArrayList<Vector2> navLink, int hp, float speed, int damage) {
        super(position, rotation, null);
        this.navLink = navLink;
        this.damage = damage;
        this.speed = speed;
        this.hp = hp;
        this.currentTarget = 0;
        isInBase = false;
        InitAnimation(animationSheet);
        InitStateMachine();
    }

    private void InitAnimation(Texture animationSheet)
    {
        walkAnimation = new HashMap<Direction, Animation<TextureRegion>>();
        TextureRegion[][] tmp = TextureRegion.split(animationSheet,
                                                animationSheet.getWidth() / FRAME_COLS,
                                                        animationSheet.getHeight() / FRAME_ROWS);

        walkAnimation.put(Direction.Down,new Animation<TextureRegion>(0.25f,tmp[0]));
        walkAnimation.put(Direction.Left, new Animation<TextureRegion>(0.25f,tmp[1]));
        walkAnimation.put(Direction.Right, new Animation<TextureRegion>(0.25f,tmp[2]));
        walkAnimation.put(Direction.Up, new Animation<TextureRegion>(0.25f,tmp[3]));

        frameTimer = 0;
        walkDirection = Direction.Down;
    }

    public float GetSpeed() {return speed;}

    public void DealDamage(int damage)
    {
        hp -= damage;
    }

    public int GetDamage()
    {
        return damage;
    }

    public boolean IsDead()
    {
        return hp <= 0;
    }

    public Vector2 GetNextTarget()
    {
        if(navLink.size() > currentTarget)
            return navLink.get(currentTarget++);
        else
            return navLink.get(navLink.size() - 1);
    }

    public Vector2 GetFinalTarget() {return navLink.get(navLink.size()-1);}

    public void SetInBase()
    {
        isInBase = true;
    }

    public boolean GetIsInBase()
    {
        return isInBase;
    }

    @Override
    public void Render(Batch batch) {
        frameTimer += Gdx.graphics.getDeltaTime();
        TextureRegion texture = walkAnimation.get(walkDirection).getKeyFrame(frameTimer,true);
        batch.draw(texture, super.GetPosition().x + (30 - texture.getRegionWidth()/2), super.GetPosition().y + (30 - texture.getRegionHeight()/2));
    }

    private void InitStateMachine()
    {
        stateMachine = new StateMachine();

        WalkingState walkingState = new WalkingState(this);

        stateMachine.AddState(walkingState);
    }

    public void Update()
    {
        stateMachine.getCurrentState().Act();
    }

    public void SwitchDirection(Direction direction)
    {
        walkDirection = direction;
    }
}