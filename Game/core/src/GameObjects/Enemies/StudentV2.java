package GameObjects.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import GameObjects.Enemy;

public final class StudentV2 extends Enemy
{
    public StudentV2(Vector2 position, Texture texture, ArrayList<Vector2> navLink) {
        super(position, 0, texture, navLink, 500, 60, 1);
    }
}
