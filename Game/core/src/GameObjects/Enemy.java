package GameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends GameObject implements Drawable
{

    //Animation<TextureRegion> walkAnimation;


    @Override
    public Sprite getDrawingSprite() {
        return null;
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
