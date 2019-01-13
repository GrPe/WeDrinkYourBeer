package GameObjects.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import GameObjects.Enemy;

public class Student extends Enemy
{
    public Student(Vector2 position, Texture texture, Vector2[] navLink) {
        super(position, 0, texture, navLink, 50, 60, 1);
    }
}
