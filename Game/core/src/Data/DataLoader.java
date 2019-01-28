package Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Factories.EnvironmentType;
import Factories.LevelEnvironmentFactory;
import GameObjects.Environment;
import GameObjects.SpawnPoint;
import Managers.ResourceManager;

public class DataLoader
{

    private final char CODE_WAY = 'o';
    private final char CODE_BUILDING = 'x';
    private final char CODE_SPAWN_POINT = 's';
    private final char CODE_TOWER = 'T';

    private ResourceManager resourceManager;
    private LevelEnvironmentFactory levelEnvironmentFactory;

    public DataLoader(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        this.levelEnvironmentFactory = new LevelEnvironmentFactory(resourceManager);
    }

    public LevelData LoadDataLevel(int level)
    {

        ArrayList<Environment> ways = new ArrayList<Environment>();
        ArrayList<Environment> environments = new ArrayList<Environment>();
        SpawnPoint spawnPoint = null;
        GameObjects.Base base = null;

        FileHandle file = Gdx.files.internal("Levels/lv" + Integer.toString(level) + ".txt");

        String line;
        int y = 6;
        int x = 0;
        line = file.readString();

        for(char sign : line.toCharArray())
        {
            switch (sign) {
                case CODE_WAY:
                    ways.add(levelEnvironmentFactory.CreateEnvironment(new Vector2(x * 60, y * 60), EnvironmentType.Way));
                    x++;
                    break;
                case CODE_BUILDING:
                    environments.add(levelEnvironmentFactory.CreateEnvironment(new Vector2(x * 60, y * 60),EnvironmentType.Buildings));
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


        ArrayList<Vector2> navLink = new ArrayList<Vector2>();
        FileHandle f = Gdx.files.internal("Levels/level1.json");
        JSONObject reader = new JSONObject(f.readString());
        JSONArray array = reader.getJSONArray("path");

        for(int i = 0; i < array.length(); i++)
        {
            JSONObject val = array.getJSONObject(i);
            navLink.add(new Vector2(val.getInt("x"),val.getInt("y")));
        }

        resourceManager.SetNavLink(navLink);
        return new LevelData(environments,ways,spawnPoint,base);
    }
}
