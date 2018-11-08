package Factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import GameObjects.Environment;

public class LevelEnvironmentFactory
{
    private static final int buildingTextureCounter = 0;
    private static ArrayList<Texture> texturesBuildings;
    private static Texture textureWay;


    public static Environment createBuilding(Vector2 position)
    {
        if(texturesBuildings == null) LoadBuildingsTextures();
        return null;
    }

    public static Environment createWay(Vector2 position)
    {
        if(textureWay == null) LoadWaysTextures();
        Environment ret = new Environment(position,0,textureWay);
        return ret;
    }

    private static void LoadBuildingsTextures()
    {
        for(int i = 0; i < buildingTextureCounter; i++)
        {
            Texture tmp = new Texture(Gdx.files.internal("Environment/buildings" + i));
            texturesBuildings.add(tmp);
        }
    }

    private static void LoadWaysTextures()
    {
        textureWay = new Texture(Gdx.files.internal("Environment/way"));
    }
}
