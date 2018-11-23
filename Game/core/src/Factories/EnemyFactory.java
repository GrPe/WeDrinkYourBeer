package Factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.logging.FileHandler;

import GameObjects.Enemy;

public class EnemyFactory
{
    private static Vector2[] navLink = {new Vector2(6,8),
                                        new Vector2(6,7),
                                        new Vector2(7,7),
                                        new Vector2(7,5),
                                        new Vector2(9,5),
                                        new Vector2(9,2),
                                        new Vector2(6,2)};
    //temporary - Animation in future !!!
    private static Texture studentTexture;

    public static Enemy createStudentEnemy(Vector2 position)
    {
        if(studentTexture == null)
        {
            LoadStudentTexture();
        }
        return new Enemy(position,0,studentTexture,navLink,5,1,1);
    }
    private static void LoadStudentTexture()
    {
        studentTexture = new Texture(Gdx.files.internal("Enemies/enemy_test.png"));
    }
}
