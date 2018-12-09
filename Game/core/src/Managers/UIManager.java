package Managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import GameObjects.UI.Label;

public class UIManager
{
    private Label baseHp;
    private Label timerAndCounter;
    //button

    public UIManager(BitmapFont font)
    {
        baseHp = new Label(font, new Vector2(20,450),"12/12");
        timerAndCounter = new Label(font, new Vector2(320,450), "40");
    }

    public void SetBaseHpLabel(int current, int max)
    {
        baseHp.setText(current + "/" + max);
    }

    public void SetTimerAndCounter(int value)
    {
        timerAndCounter.setText(Integer.toString(value));
    }

    public void draw(Batch batch)
    {
        baseHp.draw(batch);
        timerAndCounter.draw(batch);
    }

}
