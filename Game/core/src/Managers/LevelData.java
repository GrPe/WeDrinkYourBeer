package Managers;

import java.util.ArrayList;

import GameObjects.Base;
import GameObjects.Environment;
import GameObjects.SpawnPoint;

public final class LevelData
{
    public LevelData(ArrayList<Environment> environments, ArrayList<Environment> ways, SpawnPoint spawnPoint, Base base) {
        this.environments = environments;
        this.ways = ways;
        this.spawnPoint = spawnPoint;
        this.base = base;
    }

    public ArrayList<Environment> environments;
    public ArrayList<Environment> ways;
    public SpawnPoint spawnPoint;
    public Base base;
}
