package Factories;

import com.badlogic.gdx.math.Vector2;

import GameObjects.Enemies.Minion;
import GameObjects.Enemy;
import Managers.ResourceManager;

public class EnemyFactory
{
    private  ResourceManager resourceManager;

    public EnemyFactory(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public Enemy CreateEnemy(Vector2 position, EnemyType enemyType)
    {
        switch(enemyType)
        {
            case Minion:
                return new Minion(position, resourceManager.GetTexture("enemy_test.png"),resourceManager.GetNavLink());
        }
        return null;
    }
}
