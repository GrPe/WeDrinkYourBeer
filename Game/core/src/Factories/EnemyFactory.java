package Factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import GameObjects.Enemy;

public class EnemyFactory
{
    private Vector2[] navLink;
    //temporary - Animation in future !!!
    private Texture studentTexture;
    private Texture metalTexture;

    public Enemy createStudentEnemy(Vector2 position)
    {
        if(studentTexture == null) LoadStudentTexture();
        return new Enemy(position,0,studentTexture,navLink,5,1,1);
    }

    public Enemy createMetalEnemy(Vector2 position)
    {
        return null;
    }

    private void LoadNavLink(int levelNo)
    {
        //json
    }

    private void LoadStudentTexture()
    {
        studentTexture = new Texture(Gdx.files.internal("Enemy/student.png"));
    }

    private void LoadMetalTexture()
    {
        metalTexture = new Texture(Gdx.files.internal("Enemy/metal.png"));
    }

}
