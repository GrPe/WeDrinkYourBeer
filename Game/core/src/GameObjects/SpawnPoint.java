package GameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpawnPoint extends GameObject implements Drawable
{
    private Sprite sprite;

    @Override
    public Sprite getDrawingSprite() {
        return sprite;
    }
}
