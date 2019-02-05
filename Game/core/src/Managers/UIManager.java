package Managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import GameObjects.UI.Image;
import GameObjects.UI.Label;
import GameObjects.UI.TwoStateButton;
import GameObjects.UI.UIButton;

public class UIManager
{
    private Label baseHp;
    private Label timerAndCounter;
    private Label coins;

    //top bar
    private Image topBar;

    //menu
    private UIButton returnToMainMenuButton;

    //tower menu buttons
    private Image towerMenuBackground;
    private TwoStateButton singleFireTowerButton;
    private TwoStateButton continuousFireTowerButton;
    private TwoStateButton upgradeTowerButton;

    //Icons
    private Image hpIcon;
    private Image timerIcon;
    private Image coinsIcon;

    public UIManager(ResourceManager resourceManager)
    {
        //top bar
        topBar = new Image(new Vector2(-8,420),resourceManager.GetTexture("topBarGame.png"),800.0f/190.0f,83.0f/49.0f);

        //labels
        baseHp = new Label(resourceManager.GetFont(), new Vector2(75,475),"12/12");
        timerAndCounter = new Label(resourceManager.GetFont(), new Vector2(350,475), "40");
        coins = new Label(resourceManager.GetFont(), new Vector2(560,475),"120");

        returnToMainMenuButton = new UIButton(new Vector2(715,435),resourceManager.GetTexture("mainMenu.png"));

        //tower menu
        towerMenuBackground = new Image(new Vector2(-16,-13),resourceManager.GetTexture("topBarGame.png"),1.15f,1.65f);
        singleFireTowerButton = new TwoStateButton(new Vector2(5,0),resourceManager.GetTexture("singleTowerMenuButton.png"), resourceManager.GetTexture("singleTowerMenuButtonOff.png"));
        continuousFireTowerButton = new TwoStateButton(new Vector2(70,0),resourceManager.GetTexture("continuousFireTowerButton.png"), resourceManager.GetTexture("continuousFireTowerButtonOff.png"));
        upgradeTowerButton = new TwoStateButton(new Vector2(135,0),resourceManager.GetTexture("harvesterTowerButton.png"),resourceManager.GetTexture("harvesterTowerButtonOff.png"));

        //icons
        hpIcon = new Image(new Vector2(5,435),resourceManager.GetTexture("baseHpIcon.png"));
        timerIcon = new Image(new Vector2(260,435),resourceManager.GetTexture("timerIcon.png"));
        coinsIcon = new Image(new Vector2(480,435),resourceManager.GetTexture("coinsIcon.png"));
    }

    public boolean IsTowerBarClicked(Vector2 position)
    {
        return ( position.x < (upgradeTowerButton.GetPosition().x + 60 ))  && (position.y < (upgradeTowerButton.GetPosition().y + 60));
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
        topBar.Render(batch);

        baseHp.Render(batch);
        timerAndCounter.Render(batch);
        coins.Render(batch);

        returnToMainMenuButton.Render(batch);

        towerMenuBackground.Render(batch);
        singleFireTowerButton.Render(batch);
        continuousFireTowerButton.Render(batch);
        upgradeTowerButton.Render(batch);

        hpIcon.Render(batch);
        timerIcon.Render(batch);
        coinsIcon.Render(batch);
    }

    public boolean IsSingleFireTowerButtonClicked(Vector2 position)
    {
        return singleFireTowerButton.IsClicked(position);
    }

    public boolean IsContinuousFireTowerButtonClicked(Vector2 position)
    {
        return continuousFireTowerButton.IsClicked(position);
    }

    public boolean IsUpgradeTowerButtonClicked(Vector2 position)
    {
        return upgradeTowerButton.IsClicked(position);
    }

    public boolean IsReturnToMainMenuButtonClicked(Vector2 position)
    {
        return returnToMainMenuButton.IsClicked(position);
    }

    public void SetSingleFireTowerActive(boolean active)
    {
        singleFireTowerButton.SetActive(active);
    }

    public void SetContinuousFireTowerActive(boolean active)
    {
        continuousFireTowerButton.SetActive(active);
    }

    public void SetTowerUpgradeActive(boolean active)
    {
        upgradeTowerButton.SetActive(active);
    }
}
