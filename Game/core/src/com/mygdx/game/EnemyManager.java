package com.mygdx.game;

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

    private Level level;

    public EnemyManager(Level level)
    {
        enemies = new ArrayList<Enemy>();
        this.level = level;
    }

    public void NewWay(int numberOfEnemy)
    {
        numberOfEnemyToSpawn = numberOfEnemy;
    }

    public void render(SpriteBatch batch)
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

        for(Enemy enemy : enemies)
        {
            enemy.Update();
            batch.draw(enemy.getDrawingSprite(),enemy.getX(),enemy.getY());
        }
    }
}
