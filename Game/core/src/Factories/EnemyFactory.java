package Factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import GameObjects.Enemy;

public class EnemyFactory
{
    private static Vector2[] navLink = {
            new Vector2(4*60,1*60),
            new Vector2(5*60,1*60),
            new Vector2(5*60,3*60),
            new Vector2(7*60,3*60),
            new Vector2(7*60,5*60),
            new Vector2(3*60,5*60)};
    //temporary - Animation in future !!!
    private static Texture studentTexture;

    public static Enemy createStudentEnemy(Vector2 position)
    {
        if(studentTexture == null)
        {
            LoadStudentTexture();
        }
        return new Enemy(position,0,studentTexture,navLink,5,60,1);
    }
    private static void LoadStudentTexture()
    {
        studentTexture = new Texture(Gdx.files.internal("Enemies/enemy_test.png"));
    }
}
