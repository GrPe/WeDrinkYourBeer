package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import Factories.EnemyFactory;
import GameObjects.Enemy;

public class EnemyManager
{
    private ArrayList<Enemy> enemies;
    private float timer = 1f;
    private int numberOfEnemyToSpawn = 0;

    private LevelManager level;

    public EnemyManager(LevelManager level)
    {
        enemies = new ArrayList<Enemy>();
        this.level = level;
    }

    public void NewWay(int numberOfEnemy)
    {
        numberOfEnemyToSpawn = numberOfEnemy;
    }

    public void Update()
    {
        SpawnEnemy();

        for(Enemy enemy : enemies)
        {
            enemy.Update();
        }
    }

    private void SpawnEnemy()
    {
        if(numberOfEnemyToSpawn > 0)
        {
            timer -= Gdx.graphics.getDeltaTime();
            if(timer <= 0)
            {
                enemies.add(EnemyFactory.createStudentEnemy(level.getSpawnPointPosition()));
                timer = 1;
                numberOfEnemyToSpawn--;
            }
        }
    }

    public void render(SpriteBatch batch)
    {
        for(Enemy enemy : enemies)
        {
            batch.draw(enemy.getDrawingSprite(),enemy.getX(),enemy.getY());
        }
    }

    public boolean isEnemyInBase()
    {
        for(Enemy enemy : enemies)
        {
            if(enemy.getIsInBase())
            {
                enemies.remove(enemy);
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty()
    {
        return enemies.isEmpty();
    }

}
