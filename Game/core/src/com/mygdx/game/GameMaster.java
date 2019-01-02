package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Managers.LevelManager;

import Managers.EnemyManager;
import Managers.ResourceManager;
import Managers.TowerManager;

public class GameMaster extends ApplicationAdapter
{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private float timer = 0;
    private boolean nextPhase = false;

    //test

    private LevelManager level;
    private Managers.EnemyManager enemyManager;
    private Managers.UIManager uiManager;
    private ResourceManager resourceManager;

    @Override
    public void create()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,780,480);

        batch = new SpriteBatch();

        resourceManager = new ResourceManager();

        //test

        //resourceManager.FinishLoading();
        level = new LevelManager(resourceManager);
        enemyManager = new EnemyManager(level.getSpawnPointPosition(),resourceManager);
        uiManager = new Managers.UIManager(resourceManager.GetFont());

        //enemyManager.NewWay(5);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0,0xff,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        level.render(batch);
        enemyManager.render(batch);
        uiManager.draw(batch);

        batch.end();
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        resourceManager.dispose();
    }

    private void Update()
    {
        camera.update();
        enemyManager.Update();

        EnemyInBase();
        UpdateUI();
        NextPhase();
    }

    private void EnemyInBase()
    {
        int damage = enemyManager.isEnemyInBase();
        if(damage > 0)
        {
            dealBaseDamage(damage);
        }
    }

    private void dealBaseDamage(int damage)
    {
        level.getBase().decreaseHp(damage);
        uiManager.SetBaseHpLabel(level.getBase().getHp(), level.getBase().getMaxHp());
    }

    private void UpdateUI()
    {
        if(enemyManager.isEmpty() && timer > 0 && !enemyManager.isSpawningEnemies())
        {
            UpdateEnemyTimer();
        }
        else
        {
            UpdateEnemyCounter();
        }
    }

    private void UpdateEnemyCounter()
    {
        uiManager.SetTimerAndCounter(enemyManager.size());
    }

    private void UpdateEnemyTimer()
    {
        uiManager.SetTimerAndCounter((int)timer);
    }

    private void NextPhase()
    {
        if(enemyManager.isEmpty() && timer <= 0 && !nextPhase)
        {
            timer = 3;
            nextPhase = true;
        }
        if(nextPhase && !enemyManager.isSpawningEnemies() && enemyManager.isEmpty())
        {
            CountDownToNextPhase();
        }

    }

    private void CountDownToNextPhase()
    {
        timer -=Gdx.graphics.getDeltaTime();
        if(timer <= 0)
        {
            nextPhase = false;
            enemyManager.NewWay(8);
        }
    }
}
