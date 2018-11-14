package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import Factories.LevelEnvironmentFactory;
import GameObjects.Environment;

public class Level
{
    private final char CODE_WAY = 'o';
    private final char CODE_BUILDING = 'x';
    private final char CODE_SPAWN_POINT = 's';
    private final char CODE_TOWER = 'T';

    private ArrayList<Environment> ways;
    private ArrayList<Environment> buildings;


    public Level()
    {
        ways = new ArrayList<Environment>();
        buildings = new ArrayList<Environment>();
        try
        {
            CreateLevel();
        }
        catch (Exception ex)
        {
            //do nothing
        }

    }

    private void CreateLevel() throws Exception
    {
        FileHandle file = Gdx.files.internal("Levels/lv0.txt");
        String line;
        int y = 6;
        int x = 0;
        line = file.readString();

        for(char sign : line.toCharArray())
        {
            switch (sign) {
                case CODE_WAY:
                    ways.add(LevelEnvironmentFactory.createWay(new Vector2(x * 60, y * 60)));
                    x++;
                    break;
                case CODE_BUILDING:
                    buildings.add(LevelEnvironmentFactory.createBuilding(new Vector2(x * 60, y * 60)));
                    x++;
                    break;
                case CODE_SPAWN_POINT:
                    x++;
                    break;
                case CODE_TOWER:
                    x++;
                    break;
                case '\n':
                    y--;
                    x = 0;
                case '\r':
                    x = 0;
                    break;
            }
        }
    }

    public void render(SpriteBatch batch)
    {
        for(Environment x : buildings)
        {
            batch.draw(x.getDrawingSprite(),x.getX(),x.getY());
        }
        for(Environment x : ways)
        {
            batch.draw(x.getDrawingSprite(),x.getX(),x.getY());
        }
    }


}