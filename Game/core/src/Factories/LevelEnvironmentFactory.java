package Factories;

import com.badlogic.gdx.math.Vector2;

import GameObjects.Base;
import GameObjects.Environment;
import GameObjects.SpawnPoint;
import Managers.ResourceManager;

public class LevelEnvironmentFactory
{
    private ResourceManager resourceManager;

    public LevelEnvironmentFactory(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public Environment CreateEnvironment(Vector2 position, EnvironmentType environmentType)
    {
        switch(environmentType)
        {
            case Buildings:
                return new Environment(position,resourceManager.GetTexture("environment.png"));
            case Way:
                return new Environment(position,resourceManager.GetTexture("way.png"));
        }
        return null;
    }

    public Base CreateBase(Vector2 position, int hp)
    {
        return new Base(position, resourceManager.GetTexture("base.png"),hp);
    }

    public SpawnPoint CreateSpawnPoint(Vector2 position)
    {
        return new SpawnPoint(position, resourceManager.GetTexture("spawnPoint.png"));
    }

    public Environment CreateDecoration(int no, Vector2 position)
    {
        return new Environment(position,resourceManager.GetTexture("environment" + Integer.toString(no) + ".png"));
    }
}
