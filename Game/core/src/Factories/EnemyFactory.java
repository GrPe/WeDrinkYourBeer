package Factories;

import com.badlogic.gdx.math.Vector2;

import GameObjects.Enemies.LawStudent;
import GameObjects.Enemies.Student;
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
            case Student:
                return new Student(position, resourceManager.GetTexture("enemy_test.png"),resourceManager.GetNavLink());
            case LawStudent:
                return new LawStudent(position, resourceManager.GetTexture("enemy_test1.png"),resourceManager.GetNavLink());
        }
        return null;
    }
}
