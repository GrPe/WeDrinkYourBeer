package Managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import GameObjects.UI.Label;

public class UIManager
{
    private Label baseHp;
    private Label timerAndCounter;
    private Label coins;

    public UIManager(ResourceManager resourceManager)
    {
        baseHp = new Label(font, new Vector2(20,450),"12/12");
        timerAndCounter = new Label(font, new Vector2(320,450), "40");
        coins = new Label(font, new Vector2(550,450),"120");
    }

    public void SetBaseHpLabel(int current, int max)
    {
        baseHp.SetText(current + "/" + max);
    }

    public void SetTimerAndCounter(int value)
    {
        timerAndCounter.SetText(Integer.toString(value));
    }

    public void SetCoinsLabel(int value)
    {
        coins.SetText(Integer.toString(value));
    }

    public void Render(Batch batch)
    {
        baseHp.Render(batch);
        timerAndCounter.Render(batch);
        coins.Render(batch);
    }

}
