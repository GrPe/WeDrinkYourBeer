package GameObjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import Factories.TowerFactory;
import Factories.TowerType;
import FinityStateMachine.StateID;
import FinityStateMachine.StateMachine;
import FinityStateMachine.Tower.AttackEnemyState;
import FinityStateMachine.Tower.WaitingForEnemyState;
import FinityStateMachine.Transition;

public class Tower extends GameObject
{
    private ArrayList<Bullet> bullets;
    private TowerFactory towerFactory;
    private StateMachine stateMachine;
    private float range;
    private float fireSpeed;
    private int damage;
    private TowerType type;

    private Enemy target;

    private Sound shootSound;

    public Tower(Vector2 position, float rotation, Texture texture, float range, float fireSpeed, int damage, TowerFactory towerFactory, TowerType type) {
        super(position, rotation, texture);
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
        this.range = range;
        this.fireSpeed = fireSpeed;
        this.damage = damage;
        this.type = type;
        target = null;
        bullets = new ArrayList<Bullet>();
        this.towerFactory = towerFactory;
        this.shootSound = towerFactory.GetShootSound();
        InitStateMachine();
    }

    public void Fire()
    {
        shootSound.play(0.4f);
        bullets.add(towerFactory.CreateBullet(new Vector2(super.GetPosition().x + GetSpriteWidth()/3,super.GetPosition().y + GetSpriteHeight()/3),
                super.GetRotation(),
                new Vector2(target.GetPosition().x + 15, target.GetPosition().y +30)));
    }

    @Override
    public void Render(Batch batch) {
        for(Bullet bullet : bullets)
        {
            bullet.Render(batch);
        }
        super.Render(batch);
    }

    public void Update(ArrayList<Enemy> enemies)
    {
        if(stateMachine.getCurrentStateID() == StateID.TowerWaitingForEnemy)
            stateMachine.getCurrentState().Act(enemies);
        else
            stateMachine.getCurrentState().Act();

        if(bullets.isEmpty()) return;
        UpdateBullets();
        DestroyBullets();
    }

    private void InitStateMachine()
    {
        stateMachine = new StateMachine();

        WaitingForEnemyState waitingForEnemyState = new WaitingForEnemyState(this);
        AttackEnemyState attackEnemyState = new AttackEnemyState(this);

        waitingForEnemyState.AddTransition(Transition.TowerAttack, StateID.TowerAttackEnemy);
        attackEnemyState.AddTransition(Transition.TowerWaitingFor, StateID.TowerWaitingForEnemy);

        stateMachine.AddState(waitingForEnemyState);
        stateMachine.AddState(attackEnemyState);
    }

    protected float GetSpriteWidth()
    {
        return sprite.getWidth();
    }

    protected float GetSpriteHeight()
    {
        return sprite.getHeight();
    }

    public float GetFireSpeed() {
        return fireSpeed;
    }

    public int GetDamage() {
        return damage;
    }

    public Enemy GetTarget() {
        return target;
    }

    public void SetTarget(Enemy target) {
        this.target = target;
    }

    public StateMachine GetStateMachine() {
        return stateMachine;
    }

    @Override
    public void SetRotation(float rotation) {
        super.SetRotation(rotation);
        sprite.setRotation(rotation);
    }

    public boolean IsTargetInRange(Vector2 target)
    {
        return target.epsilonEquals(GetPosition(),range);
    }


    private void UpdateBullets()
    {
        for(Bullet bullet : bullets)
        {
            bullet.Update();
        }
    }

    private void DestroyBullets()
    {
        for(Bullet bullet : bullets)
        {
            if(bullet.CanBeDestroyed())
            {
                bullets.remove(bullet);
                return;
            }
        }
    }

    public TowerType GetType() {
        return type;
    }
}