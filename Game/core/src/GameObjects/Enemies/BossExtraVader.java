package GameObjects.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import GameObjects.Enemy;

public final class BossExtraVader extends Enemy
{
    public BossExtraVader(Vector2 position, Texture texture, ArrayList<Vector2> navLink) {
        super(position, 0, texture, navLink, 6200, 60, 2);
    }
}
