package Factories;

import com.badlogic.gdx.math.Vector2;

import GameObjects.Tower;
import GameObjects.Towers.SingleFireTower;
import Managers.ResourceManager;

public class TowerFactory
{
    private ResourceManager resourceManager;

    public TowerFactory(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public Tower CreateTower(Vector2 position, TowerType towerType)
    {
        Tower tower;
        switch(towerType)
        {
            case SingleFire:
                tower = new SingleFireTower(position, resourceManager.GetTexture("tmp_tower.png"));
                break;
            default:
                tower = new SingleFireTower(position, resourceManager.GetTexture("tmp_tower.png"));
                break;
        }
        return tower;
    }

}
