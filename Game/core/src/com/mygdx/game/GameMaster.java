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
import GameObjects.Environment;

public class GameMaster extends ApplicationAdapter
{
    private OrthographicCamera camera;
    private SpriteBatch batch;

    //test
    private Texture texture;
    private Sprite sprite;

    private ArrayList<Environment> env;

    @Override
    public void create()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,780,480);

        batch = new SpriteBatch();

        //texture = new Texture(Gdx.files.internal("Environment/buildings0.png"));
        //sprite = new Sprite(texture);

        env = new ArrayList<Environment>();
        for(int i = 0; i < 800/60; i++)
        {
            if(i%2 == 1) continue;
            for(int j = 0; j < 480/60; j++)
            {
                if(j%2 == 1) continue;
                env.add(LevelEnvironmentFactory.createBuilding(new Vector2(i*60,j*60)));
            }
        }
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0,0xff,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for(Environment x : env)
        {
            batch.draw(x.getDrawingSprite(),x.getX(),x.getY());
        }
        batch.end();


    }

    @Override
    public void dispose()
    {
        batch.dispose();
    }


}
