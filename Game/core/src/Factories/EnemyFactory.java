package Factories;

import com.badlogic.gdx.math.Vector2;

import GameObjects.Enemies.*;
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
                return new Student(position, resourceManager.GetEnemyTexture("studentV1"),resourceManager.GetNavLink());
            case LawStudent:
                return new LawStudent(position, resourceManager.GetEnemyTexture("studentLaw"),resourceManager.GetNavLink());
            case AWFStudent:
                return new AWFStudent(position, resourceManager.GetEnemyTexture("studentAWF"),resourceManager.GetNavLink());
            case ITStudent:
                return new ITStudent(position, resourceManager.GetEnemyTexture("studentIT"),resourceManager.GetNavLink());
            case MDStudent:
                return new MDStudent(position, resourceManager.GetEnemyTexture("studentMD"),resourceManager.GetNavLink());
            case BossDrunkMaster:
                return new BossDrunkMaster(position, resourceManager.GetEnemyTexture("bossV1"),resourceManager.GetNavLink());
        }
        return null;
    }
}
