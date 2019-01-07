package Managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import Factories.TowerFactory;
import Factories.TowerType;
import GameObjects.Enemy;
import GameObjects.Tower;

public class TowerManager
{
    private ArrayList<Tower> towers;
    private LevelManager levelManager;
    private TowerFactory towerFactory;

    public TowerManager(LevelManager levelManager, ResourceManager resourceManager)
    {
        towers = new ArrayList<Tower>();
        this.levelManager = levelManager;
        towerFactory = new TowerFactory(resourceManager);
    }

    public void SetTower(Vector2 position, TowerType towerType)
    {
        //if tower exists in this place
        for(Tower t : towers)
        {
            if(t.GetPosition().equals(position)) return;
        }

        if(levelManager.CheckIfTowerCanBePlaced(position))
        {
            towers.add(towerFactory.CreateTower(position,towerType));
        }
    }

    public void Update(ArrayList<Enemy> enemies)
    {
        for(Tower x : towers)
        {
            x.Update(enemies);
        }
    }

    public void Render(Batch batch)
    {
        for(Tower tower : towers)
        {
            tower.Render(batch);
        }
    }

}
