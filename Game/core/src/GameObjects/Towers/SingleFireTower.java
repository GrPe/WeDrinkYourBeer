package GameObjects.Towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import GameObjects.Tower;

public class SingleFireTower extends Tower {
    public SingleFireTower(Vector2 position, Texture texture) {
        super(position, 0, texture, 192, 1, 1);
    }
}
