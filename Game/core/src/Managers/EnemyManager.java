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
            if(enemy.IsDead())
            {
                enemies.remove(enemy);
                return;
            }
        }
    }


    public void Render(SpriteBatch batch)
    {
        for(Enemy enemy : enemies)
        {
            enemy.Render(batch);
        }
    }

    public int IsEnemyInBase()
    {
        for(Enemy enemy : enemies)
        {
            if(enemy.GetIsInBase())
            {
                int retDamage = enemy.GetDamage();
                enemy.DealDamage(50000);
                enemies.remove(enemy);
                return retDamage;
            }
        }
        return 0;
    }

    public boolean IsEmpty()
    {
        return enemies.isEmpty();
    }

    public int Size()
    {
        return enemies.size();
    }

    public boolean IsSpawningEnemies()
    {
        return numberOfEnemyToSpawn > 0;
    }

    public ArrayList<Enemy> GetEnemies() {
        return enemies;
    }
}
