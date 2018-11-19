package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import Factories.LevelEnvironmentFactory;
import GameObjects.Enemy;
import GameObjects.Environment;

public class GameMaster extends ApplicationAdapter
{
    private OrthographicCamera camera;
    private SpriteBatch batch;

    //test

    private ArrayList<Environment> env;
    private Level lvl;

    @Override
    public void create()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,780,480);

        batch = new SpriteBatch();

        //test

        lvl = new Level();
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0,0xff,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        lvl.render(batch);
        batch.end();


    }

    @Override
    public void dispose()
    {
        batch.dispose();
    }


}
