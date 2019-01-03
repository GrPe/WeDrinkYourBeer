package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import Factories.EnemyFactory;
import Factories.EnemyType;
import GameObjects.Enemy;

public class EnemyManager
{
    private ArrayList<Enemy> enemies;
    private float timer = 1f;
    private int numberOfEnemyToSpawn = 0;

    private Vector2 spawnPosition;
    private ResourceManager resourceManager;
    private EnemyFactory enemyFactory;

    public EnemyManager(Vector2 spawnPosition, ResourceManager resourceManager)
    {
        enemies = new ArrayList<Enemy>();
        this.spawnPosition = spawnPosition;
        this.resourceManager = resourceManager;
        this.enemyFactory = new EnemyFactory(resourceManager);
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

        DestroyDeadEnemy();
    }

    private void SpawnEnemy()
    {
        if(numberOfEnemyToSpawn > 0)
        {
            timer -= Gdx.graphics.getDeltaTime();
            if(timer <= 0)
            {
                enemies.add(enemyFactory.CreateEnemy(spawnPosition,EnemyType.Student));
                timer = 1;
                numberOfEnemyToSpawn--;
            }
        }
    }

    private void DestroyDeadEnemy()
    {
        for(Enemy enemy : enemies)
        {
            if(enemy.isDead())
            {
                enemies.remove(enemy);
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

    public int isEnemyInBase()
    {
        for(Enemy enemy : enemies)
        {
            if(enemy.getIsInBase())
            {
                int retDamage = enemy.getDamage();
                enemies.remove(enemy);
                return retDamage;
            }
        }
        return 0;
    }

    public boolean isEmpty()
    {
        return enemies.isEmpty();
    }

    public int size()
    {
        return enemies.size();
    }

    public boolean isSpawningEnemies()
    {
        return numberOfEnemyToSpawn > 0;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
