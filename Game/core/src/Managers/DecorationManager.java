package Managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Factories.LevelEnvironmentFactory;
import GameObjects.Environment;

public class DecorationManager
{
    private List<Environment> environments;
    private LevelEnvironmentFactory levelEnvironmentFactory;
    private Random rand;

    public DecorationManager(LevelEnvironmentFactory levelEnvironmentFactory) {
        this.levelEnvironmentFactory = levelEnvironmentFactory;
        environments = new ArrayList<Environment>();
        rand = new Random();
    }

    public void GenerateEnvironment(ArrayList<Environment> list)
    {
        for(Environment environment : list)
        {
            if(CanGeneratedEnvironment())
            {
                environments.add(GetNewEnvironment(environment.GetPosition()));
            }
        }
    }

    private boolean CanGeneratedEnvironment()
    {
        return rand.nextInt(100) < 55;
    }

    private Environment GetNewEnvironment(Vector2 pos)
    {
        int random = rand.nextInt(100) + 1;
        return levelEnvironmentFactory.CreateDecoration(random%5+1,pos);
    }

    public void Render(Batch batch)
    {
        for(Environment environment : environments)
        {
            environment.Render(batch);
        }
    }

    public void DestroyEnvironment(Vector2 position)
    {
        for(Environment environment : environments)
        {
            if(environment.GetPosition().epsilonEquals(position,2))
            {
                environments.remove(environment);
                return;
            }
        }
    }

    public void Reset()
    {
        environments.clear();
    }

}
