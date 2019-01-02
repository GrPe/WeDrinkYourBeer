package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import Factories.TowerType;
import Managers.LevelManager;

import Managers.EnemyManager;
import Managers.ResourceManager;
import Managers.TowerManager;

public class GameMaster extends ApplicationAdapter implements InputProcessor
{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private float timer = 0;
    private boolean nextPhase = false;

    //test

    private LevelManager levelManager;
    private Managers.EnemyManager enemyManager;
    private Managers.UIManager uiManager;
    private Managers.TowerManager towerManager;
    private ResourceManager resourceManager;

    @Override
    public void create()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,780,480);

        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);

        resourceManager = new ResourceManager();

        //test

        levelManager = new LevelManager(resourceManager);
        enemyManager = new EnemyManager(levelManager.getSpawnPointPosition(),resourceManager);
        uiManager = new Managers.UIManager(resourceManager.GetFont());
        towerManager = new TowerManager(levelManager,resourceManager);

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

        levelManager.render(batch);
        enemyManager.render(batch);
        towerManager.render(batch);
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
        towerManager.Update();
        InsertTower();

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
        levelManager.getBase().decreaseHp(damage);
        uiManager.SetBaseHpLabel(levelManager.getBase().getHp(), levelManager.getBase().getMaxHp());
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

    private Vector3 touchPoint = new Vector3();
    private boolean isTouched = false;
    private void InsertTower()
    {
        if(isTouched)
        {
            towerManager.SetTower(RoundTo60(touchPoint),TowerType.SingleFire);
            isTouched = false;
        }
    }

    private Vector2 RoundTo60(Vector3 position)
    {
        return new Vector2(((int)position.x/60)*60,((int)position.y/60)*60);
    }

    //Input Processor implementation

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button != Input.Buttons.LEFT || pointer > 0) return false;
        camera.unproject(touchPoint.set(screenX,screenY,0));
        isTouched = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
