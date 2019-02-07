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
            case StudentV2:
                return new StudentV2(position,resourceManager.GetEnemyTexture("studentV2"),resourceManager.GetNavLink());
            case ChineseStudent:
                return new ChineseStudent(position,resourceManager.GetEnemyTexture("studentChinese"),resourceManager.GetNavLink());
            case SamuraiStudent:
                return new SamuraiStudent(position,resourceManager.GetEnemyTexture("studentSamurai"),resourceManager.GetNavLink());
            case RussiaStudent:
                return new RussiaStudent(position,resourceManager.GetEnemyTexture("studentRussia"),resourceManager.GetNavLink());
            case Devil:
                return new Devil(position,resourceManager.GetEnemyTexture("devil"),resourceManager.GetNavLink());
            case BossDeath:
                return new BossDeath(position,resourceManager.GetEnemyTexture("bossV2"),resourceManager.GetNavLink());
            case BossExtraVader:
                return new BossExtraVader(position,resourceManager.GetEnemyTexture("bossV3"),resourceManager.GetNavLink());
        }
        return null;
    }
}
