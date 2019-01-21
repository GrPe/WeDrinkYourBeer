package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import Factories.EnvironmentType;
import Factories.LevelEnvironmentFactory;
import GameObjects.Environment;
import GameObjects.SpawnPoint;

public class LevelManager
{
    private final char CODE_WAY = 'o';
    private final char CODE_BUILDING = 'x';
    private final char CODE_SPAWN_POINT = 's';
    private final char CODE_TOWER = 'T';

    private ArrayList<Environment> ways;
    private ArrayList<Environment> buildings;
    private SpawnPoint spawnPoint;
    private GameObjects.Base base;

    private ResourceManager resourceManager;
    private LevelEnvironmentFactory levelEnvironmentFactory;

    private int numberOfLevel = 1; //temp

    public LevelManager(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
        this.levelEnvironmentFactory = new LevelEnvironmentFactory(resourceManager);

        try
        {
            CreateLevel();
        }
        catch (Exception ex)
        {
            //do nothing
        }

    }

    //todo - exception handlers
    private void CreateLevel() throws Exception
    {
        ways = new ArrayList<Environment>();
        buildings = new ArrayList<Environment>();

        FileHandle file = Gdx.files.internal("Levels/lv0.txt");
        String line;
        int y = 6;
        int x = 0;
        line = file.readString();

        for(char sign : line.toCharArray())
        {
            switch (sign) {
                case CODE_WAY:
                    ways.add(levelEnvironmentFactory.CreateEnvironment(new Vector2(x * 60, y * 60),EnvironmentType.Way));
                    x++;
                    break;
                case CODE_BUILDING:
                    buildings.add(levelEnvironmentFactory.CreateEnvironment(new Vector2(x * 60, y * 60),EnvironmentType.Buildings));
                    x++;
                    break;
                case CODE_SPAWN_POINT:
                    ways.add(levelEnvironmentFactory.CreateEnvironment(new Vector2(x * 60, y * 60),EnvironmentType.Way));
                    spawnPoint = levelEnvironmentFactory.CreateSpawnPoint(new Vector2(x*60, y*60));
                    x++;
                    break;
                case CODE_TOWER:
                    base = levelEnvironmentFactory.CreateBase(new Vector2(x*60, y*60),6);
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

    public void Render(Batch batch)
    {
        for(Environment x : buildings)
        {
            x.Render(batch);
        }

        for(Environment x : ways)
        {
            x.Render(batch);
        }

        spawnPoint.Render(batch);
        base.Render(batch);
    }

    public Vector2 GetSpawnPointPosition()
    {
        return spawnPoint.GetPosition();
    }

    public GameObjects.Base GetBase()
    {
        return base;
    }

    public boolean CheckIfTowerCanBePlaced(Vector2 position)
    {
        for(Environment x : buildings)
        {
            if(x.GetPosition().equals(position)) return true;
        }
        return false;
    }

    public void Reset()
    {
        base.SetHp(base.GetMaxHp());
    }


}
