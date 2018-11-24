package Factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import GameObjects.Environment;
import GameObjects.SpawnPoint;

public class LevelEnvironmentFactory
{
    private static final int buildingTextureCounter = 3;
    private static ArrayList<Texture> texturesBuildings;
    private static Texture textureWay;
    private static Texture textureSpawnPoint;


    public static Environment createBuilding(Vector2 position)
    {
        if(texturesBuildings == null) LoadBuildingsTextures();
        return new Environment(position,texturesBuildings.get(2));
    }

    public static Environment createWay(Vector2 position)
    {
        if(textureWay == null) LoadWaysTextures();
        return new Environment(position,textureWay);
    }

    public static SpawnPoint createSpawnPoint(Vector2 position)
    {
        if(textureSpawnPoint == null) LoadSpawnPointTexture();
        return new SpawnPoint(position,textureSpawnPoint);
    }

    private static void LoadBuildingsTextures()
    {
        texturesBuildings = new ArrayList<Texture>();
        for(int i = 0; i < buildingTextureCounter; i++)
        {
            Texture tmp = new Texture(Gdx.files.internal("Environment/buildings" + i + ".png"));
            texturesBuildings.add(tmp);
        }
    }

    private static void LoadWaysTextures()
    {
        textureWay = new Texture(Gdx.files.internal("Environment/way.png"));
    }

    private static void LoadSpawnPointTexture()
    {
        textureSpawnPoint = new Texture(Gdx.files.internal("Environment/spawnPoint.png"));
    }
}
