package GameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpawnPoint extends GameObject implements Drawable
{
    private Sprite sprite;

    @Override
    public Sprite getDrawingSprite() {
        return sprite;
    }

    @Override
    public double getX() {
        return super.getPosition().x;
    }

    @Override
    public double getY() {
        return super.getPosition().y;
    }
}
