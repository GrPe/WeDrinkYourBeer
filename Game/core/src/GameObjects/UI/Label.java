package GameObjects.UI;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import GameObjects.GameObject;

public class Label extends GameObject
{
    private BitmapFont font;
    private String text;

    public Label(BitmapFont font, Vector2 position, String text) {
        super(position, 0,null);
        this.font = font;
        this.text = text;
    }

    @Override
    public void Render(Batch batch)
    {
        font.draw(batch,text, GetPosition().x, GetPosition().y);
    }

    public void SetText(String text)
    {
        this.text = text;
    }
}
