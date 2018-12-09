package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Managers.LevelManager;

import Managers.EnemyManager;

public class GameMaster extends ApplicationAdapter
{
    private OrthographicCamera camera;
    private SpriteBatch batch;

    //test

    private LevelManager level;
    private Managers.EnemyManager enemyManager;
    private Managers.UIManager uiManager;

    @Override
    public void create()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,780,480);

        batch = new SpriteBatch();

        //test

        level = new LevelManager();
        enemyManager = new EnemyManager(level);
        enemyManager.NewWay(5);
        uiManager = new Managers.UIManager();
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
    }

    private void Update()
    {
        camera.update();
        enemyManager.Update();

        EnemyInBase();
    }

    //todo
    private void EnemyInBase()
    {
        enemyManager.isEnemyInBase();

        if(enemyManager.isEmpty())
        {
            enemyManager.NewWay(8);
        }
    }



}
