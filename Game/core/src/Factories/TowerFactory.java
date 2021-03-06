package Factories;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import GameObjects.Bullet;
import GameObjects.Tower;
import GameObjects.Towers.*;
import Managers.ResourceManager;

public class TowerFactory
{
    private ResourceManager resourceManager;
    private Sound shootSound;

    public TowerFactory(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
        this.shootSound = resourceManager.GetSound("shoot.wav");
    }

    public Sound GetShootSound()
    {
        return shootSound;
    }

    public Tower CreateTower(Vector2 position, TowerType towerType)
    {
        switch(towerType)
        {
            case SingleFire:
                return  new SingleFireTower(position, resourceManager.GetTexture("singleTowerLv01.png"),this);
            case ContinuousFire:
                return new ContinuousFireTower(position,resourceManager.GetTexture("towerContinuousLv01.png"),this);
            case SingleFireV2:
                return  new SingleFireTowerV2(position, resourceManager.GetTexture("singleTowerLv02.png"),this);
            case ContinuousFireV2:
                return new ContinuousFireTowerV2(position,resourceManager.GetTexture("towerContinuousLv02.png"),this);
        }
        return null;
    }

    public Bullet CreateBullet(Vector2 position, float rotation, Vector2 destination)
    {
        return new Bullet(position,rotation,resourceManager.GetTexture("bullet.png"), 600, destination);
    }

}
