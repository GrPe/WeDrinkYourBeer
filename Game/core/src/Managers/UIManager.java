package Managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import GameObjects.UI.Label;
import GameObjects.UI.UIButton;

public class UIManager
{
    private Label baseHp;
    private Label timerAndCounter;
    private Label coins;
    private UIButton towerMenu;

    public UIManager(ResourceManager resourceManager)
    {
        baseHp = new Label(resourceManager.GetFont(), new Vector2(20,450),"12/12");
        timerAndCounter = new Label(resourceManager.GetFont(), new Vector2(320,450), "40");
        coins = new Label(resourceManager.GetFont(), new Vector2(550,450),"120");
        towerMenu = new UIButton(new Vector2(0,0),resourceManager.GetTexture("towerMenu.png"));
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
        towerMenu.Render(batch);
    }

    public boolean IsTowerMenuButtonClicked(Vector2 position)
    {
        return towerMenu.IsClicked(position);
    }

}
