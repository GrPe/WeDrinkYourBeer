package Factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.logging.FileHandler;

import GameObjects.Enemy;

public class EnemyFactory
{
    private static Vector2[] navLink;
    //temporary - Animation in future !!!
    private static Texture studentTexture;
    private static Texture metalTexture;

    public static Enemy createStudentEnemy(Vector2 position)
    {
        if(studentTexture == null) LoadStudentTexture();
        return new Enemy(position,0,studentTexture,navLink,5,1,1);
    }

    public static Enemy createMetalEnemy(Vector2 position)
    {
        return null;
    }

    private static void LoadNavLink(int levelNo)
    {
        FileHandle file = Gdx.files.internal("Levels/navlv" + levelNo + ".txt");
        String line;
        String[] arr;
        line = file.readString();
        arr = line.split(" ");

        int x, y;
        int size = Integer.parseInt(arr[0]);
        navLink = new Vector2[size];

        for(int i = 1; i < arr.length; i+=2)
        {
            x = Integer.parseInt(arr[i]);
            y = Integer.parseInt(arr[i+1]);
            navLink[(i-1)/2] = new Vector2(x*60,y*60);
        }
    }

    private static void LoadStudentTexture()
    {
        studentTexture = new Texture(Gdx.files.internal("Enemies/enemy_test.png"));
    }

    private static void LoadMetalTexture()
    {
        metalTexture = new Texture(Gdx.files.internal("Enemies/metal.png"));
    }

}
