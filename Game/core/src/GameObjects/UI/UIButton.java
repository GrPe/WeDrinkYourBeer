package GameObjects.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import GameObjects.GameObject;

public class UIButton extends GameObject
{
    public UIButton(Vector2 position, Texture texture) {
        super(position, 0,texture);
        sprite.setScale(60.0f/sprite.getHeight());
        sprite.setOrigin(0,0);
    }

    public boolean IsClicked(Vector2 position)
    {
        return position.epsilonEquals(super.GetPosition(),2);
    }
}
