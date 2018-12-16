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
        Environment environment;
        switch(environmentType)
        {
            case Buildings:
                environment = new Environment(position,resourceManager.GetTexture("buildings2.png"));
                break;
            case Way:
                environment = new Environment(position,resourceManager.GetTexture("way.png"));
                break;
            default:
                environment = new Environment(position,resourceManager.GetTexture("buildings2.png"));
                break;
        }

        return environment;
    }

    public Base CreateBase(Vector2 position, int hp)
    {
        return new Base(position, resourceManager.GetTexture("base.png"),hp);
    }

    public SpawnPoint CreateSpawnPoint(Vector2 position)
    {
        return new SpawnPoint(position, resourceManager.GetTexture("spawnPoint.png"));
    }
}
