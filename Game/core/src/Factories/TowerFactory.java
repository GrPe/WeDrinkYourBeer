package Factories;

import com.badlogic.gdx.math.Vector2;

import GameObjects.Bullet;
import GameObjects.Tower;
import GameObjects.Towers.ContinuousFireTower;
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
        switch(towerType)
        {
            case SingleFire:
                return  new SingleFireTower(position, resourceManager.GetTexture("singleTowerLv01.png"),this);
            case ContinuousFire:
                return new ContinuousFireTower(position,resourceManager.GetTexture("towerContinuousLv01.png"),this);
        }
        return null;
    }

    public Bullet CreateBullet(Vector2 position, float rotation, Vector2 destination)
    {
        return new Bullet(position,rotation,resourceManager.GetTexture("bullet.png"), 600, destination);
    }

}
