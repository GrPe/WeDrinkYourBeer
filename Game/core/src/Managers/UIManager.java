package Managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import GameObjects.UI.Label;
import GameObjects.UI.UIButton;

public class UIManager
{
    private Label baseHp;
    private Label timerAndCounter;
    private Label coins;

    //menu
    private UIButton returnToMainMenuButton;

    //tower menu buttons
    private boolean towerMenuVisibility;
    private UIButton towerMenu;
    private UIButton singleFireTowerButton;
    private UIButton continuousFireTowerButton;
    private UIButton harvesterTowerButton;

    public UIManager(ResourceManager resourceManager)
    {
        towerMenuVisibility = false;
        baseHp = new Label(resourceManager.GetFont(), new Vector2(50,460),"12/12");
        timerAndCounter = new Label(resourceManager.GetFont(), new Vector2(350,460), "40");
        coins = new Label(resourceManager.GetFont(), new Vector2(560,440),"120");

        returnToMainMenuButton = new UIButton(new Vector2(620,460),resourceManager.GetTexture("MainMenu.png"));

        towerMenu = new UIButton(new Vector2(0,0),resourceManager.GetTexture("towerMenu.png"));
        singleFireTowerButton = new UIButton(new Vector2(0,60),resourceManager.GetTexture("singleTowerMenuButton.png"));
        continuousFireTowerButton = new UIButton(new Vector2(60,60),resourceManager.GetTexture("continuousFireTowerButton.png"));
        harvesterTowerButton = new UIButton(new Vector2(60,0),resourceManager.GetTexture("harvester.png"));
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

        returnToMainMenuButton.Render(batch);

        towerMenu.Render(batch);
        if(towerMenuVisibility)
        {
            singleFireTowerButton.Render(batch);
            continuousFireTowerButton.Render(batch);
            harvesterTowerButton.Render(batch);
        }
    }

    public boolean IsTowerMenuButtonClicked(Vector2 position)
    {
        return towerMenu.IsClicked(position);
    }

    public boolean IsSingleFireTowerButtonClicked(Vector2 position)
    {
        if(!towerMenuVisibility) return false;
        return singleFireTowerButton.IsClicked(position);
    }

    public boolean IsContinouosFireTowerButtonClicked(Vector2 position)
    {
        if(!towerMenuVisibility) return false;
        return continuousFireTowerButton.IsClicked(position);
    }

    public boolean IsHarvesterTowerButtonClicked(Vector2 position)
    {
        if(!towerMenuVisibility) return false;
        return harvesterTowerButton.IsClicked(position);
    }

    public boolean IsReturnToMainMenuButtonClicked(Vector2 position)
    {
        return returnToMainMenuButton.IsClicked(position);
    }

    public void SetVisibility(boolean state)
    {
        towerMenuVisibility = state;
    }
}
