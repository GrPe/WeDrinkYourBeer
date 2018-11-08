package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMaster extends ApplicationAdapter
{
    private OrthographicCamera camera;
    private SpriteBatch batch;

    //test
    private Texture texture;
    private Sprite sprite;


    @Override
    public void create()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);

        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        sprite = new Sprite(texture);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0,0xff,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        //to draw
        batch.draw(sprite,800-256,480-256);
        batch.end();


    }

    @Override
    public void dispose()
    {
        batch.dispose();
    }


}
