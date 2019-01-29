package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import Data.Phase;
import Factories.EnemyFactory;
import Factories.EnemyType;
import GameObjects.Enemy;

public class EnemyManager
{
    private ArrayList<Enemy> enemies;
    private float timer = 1f;
    private int numberOfEnemyToSpawn = 0;
    private EnemyType enemyToSpawn = EnemyType.Student;
    private int killedEnemyFromLastCheck = 0;

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

    public void NewWay(Phase phase)
    {
        int id = phase.GetEnemyId();

        //i don't now why, but switch not work in this case ????
        if(id == 1) enemyToSpawn =EnemyType.Student;
        else if(id == 2) enemyToSpawn = EnemyType.LawStudent;
        else if(id == 3) enemyToSpawn = EnemyType.AWFStudent;
        else if(id == 4) enemyToSpawn = EnemyType.ITStudent;
        else enemyToSpawn = EnemyType.Student;

        numberOfEnemyToSpawn = phase.GetNumberOfEnemy();
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
                enemies.add(enemyFactory.CreateEnemy(spawnPosition,enemyToSpawn));
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
                killedEnemyFromLastCheck++;
                enemies.remove(enemy);
                return;
            }
        }
    }

    public int GetKilledEnemyFromLastCheck()
    {
        int temp = killedEnemyFromLastCheck;
        killedEnemyFromLastCheck = 0;
        return temp;
    }

    public void Render(Batch batch)
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

    public void Reset()
    {
        enemies.clear();
        timer = 1f;
        numberOfEnemyToSpawn = 0;
        killedEnemyFromLastCheck = 0;
    }
}
