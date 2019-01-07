package GameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Drawable
{
    void Render(Batch batch);
    float GetX();
    float GetY();
}