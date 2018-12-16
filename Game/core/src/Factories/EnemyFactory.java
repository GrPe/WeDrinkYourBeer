package Factories;

import com.badlogic.gdx.math.Vector2;

import GameObjects.Enemies.Student;
import GameObjects.Enemy;
import Managers.ResourceManager;

public class EnemyFactory
{
    private Vector2[] navLink = {
            new Vector2(4*60,1*60),
            new Vector2(5*60,1*60),
            new Vector2(5*60,3*60),
            new Vector2(7*60,3*60),
            new Vector2(7*60,5*60),
            new Vector2(3*60,5*60)};

    private  ResourceManager resourceManager;

    public EnemyFactory(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public Enemy CreateEnemy(Vector2 position, EnemyType enemyType)
    {
        Enemy enemy;
        switch(enemyType)
        {
            case Student:
                enemy = new Student(position, resourceManager.GetTexture("enemy_test.png"),navLink);
                break;
            default:
                enemy = new Student(position, resourceManager.GetTexture("enemy_test.png"),navLink);
                break;
        }

        return enemy;
    }
}
