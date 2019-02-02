package Managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import Data.DataLoader;
import Data.LevelData;
import Data.LevelPhase;
import Data.Phase;
import Factories.LevelEnvironmentFactory;
import GameObjects.Environment;

public class LevelManager
{
    private DecorationManager decorationManager;
    private DataLoader dataLoader;
    private LevelData levelData;
    private LevelPhase levelPhase;
    private int currentPhase;

    public LevelManager(ResourceManager resourceManager)
    {
        this.decorationManager = new DecorationManager(new LevelEnvironmentFactory(resourceManager));
        this.dataLoader = new DataLoader(resourceManager);
        currentPhase = 0;
        CreateLevel(1);
    }

    private void CreateLevel(int level)
    {
        levelData = dataLoader.LoadDataLevel(level);
        levelPhase = dataLoader.LoadLevelPhase(level);

        decorationManager.GenerateEnvironment(levelData.environments);
    }

    public void Render(Batch batch)
    {
        for(Environment x : levelData.environments)
        {
            x.Render(batch);
        }

        decorationManager.Render(batch);

        for(Environment x : levelData.ways)
        {
            x.Render(batch);
        }

        levelData.spawnPoint.Render(batch);
        levelData.base.Render(batch);
    }

    public Vector2 GetSpawnPointPosition()
    {
        return levelData.spawnPoint.GetPosition();
    }

    public GameObjects.Base GetBase()
    {
        return levelData.base;
    }

    boolean CheckIfTowerCanBePlaced(Vector2 position)
    {
        for(Environment x : levelData.environments)
        {
            if(x.GetPosition().equals(position)) return true;
        }
        return false;
    }

    public void RemoveDecoration(Vector2 position)
    {
        decorationManager.DestroyEnvironment(position);
    }

    public void Reset()
    {
        levelData.base.SetHp(levelData.base.GetMaxHp());
        decorationManager.Reset();
        decorationManager.GenerateEnvironment(levelData.environments);
        currentPhase = 0;
    }

    public Phase GetNextPhase()
    {
        return levelPhase.GetPhase(currentPhase++);
    }

    public boolean IsEndOfLevel()
    {
        return currentPhase >= levelPhase.GetSize();
    }

    public int GetCurrentPhase() {
        return currentPhase;
    }
}
