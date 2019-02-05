package GameObjects.UI;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public final class TwoStateButton extends UIButton
{
    private Sprite secondSprite;
    private boolean active;

    public TwoStateButton(Vector2 position, Texture texture, Texture secondTexture, Sound sound) {
        super(position, texture, sound);
        this.secondSprite = new Sprite(secondTexture);
        active = true;
    }

    @Override
    public void Render(Batch batch) {
        if(active)
            super.Render(batch);
        else
            batch.draw(secondSprite, super.GetPosition().x, super.GetPosition().y,secondSprite.getOriginX(),secondSprite.getOriginY(),
                    secondSprite.getWidth(),secondSprite.getHeight(),secondSprite.getScaleX(),secondSprite.getScaleY(), super.GetRotation());
    }

    @Override
    public boolean IsClicked(Vector2 position) {
        if(!active) return false;
        return super.IsClicked(position);
    }

    public void SetActive(boolean active) {
        this.active = active;
    }
}
