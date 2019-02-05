package GameObjects.UI;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import GameObjects.GameObject;

public class UIButton extends GameObject
{
    private Sound sound;

    public UIButton(Vector2 position, Texture texture, Sound sound) {
        super(position, 0,texture);
        this.sound = sound;
    }

    public boolean IsClicked(Vector2 position)
    {
        boolean results = (position.x >= super.GetPosition().x &&
                position.x < super.GetPosition().x + sprite.getWidth() &&
                position.y >= super.GetPosition().y &&
                position.y < super.GetPosition().y + sprite.getHeight());
        if(results)
            sound.play(1.0f);
        return results;
    }
}
