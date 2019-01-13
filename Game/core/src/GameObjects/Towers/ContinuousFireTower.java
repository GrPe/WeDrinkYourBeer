package GameObjects.Towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import Factories.TowerFactory;
import GameObjects.Bullet;
import GameObjects.Enemy;
import GameObjects.Tower;

public class ContinuousFireTower extends Tower
{
    private ArrayList<Bullet> bullets;
    private TowerFactory towerFactory;
    public final static int cost = 80;

    public ContinuousFireTower(Vector2 position, Texture texture, TowerFactory towerFactory)
    {
        super(position, 0, texture, 100, 0.10f, 2, cost);
        this.towerFactory = towerFactory;
        bullets = new ArrayList<Bullet>();
    }

    @Override
    public void Fire()
    {
        bullets.add(towerFactory.CreateBullet(new Vector2(super.GetPosition().x + GetSpriteWidth()/4,super.GetPosition().y + GetSpriteHeight()/4),
                super.GetRotation(),super.GetTarget().GetPosition()));
    }

    @Override
    public void Render(Batch batch) {
        super.Render(batch);
        for(Bullet bullet : bullets)
        {
            bullet.Render(batch);
        }
    }

    @Override
    public void Update(ArrayList<Enemy> enemies)
    {
        super.Update(enemies);

        if(bullets.isEmpty()) return;
        UpdateBullets();
        DestroyBullets();
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
}
