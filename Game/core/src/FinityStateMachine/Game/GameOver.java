package FinityStateMachine.Game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameMaster;

import FinityStateMachine.State;
import FinityStateMachine.Transition;
import GameObjects.UI.Image;
import GameObjects.UI.Label;
import GameObjects.UI.UIButton;
import Managers.CoinsManager;
import Managers.InputManager;
import Managers.ResourceManager;

public class GameOver extends State {
    private GameMaster gameMaster;
    private ResourceManager resourceManager;
    private InputManager inputManager;

    private Image background;
    private UIButton returnToMenu;
    private UIButton retry;

    public GameOver(GameMaster gameMaster, ResourceManager resourceManager, InputManager inputManager) {
        this.gameMaster = gameMaster;
        this.resourceManager = resourceManager;
        this.inputManager = inputManager;

        InitUI();
    }

    private void InitUI()
    {
        background = new Image(new Vector2(-40,-40),resourceManager.GetTexture("gameOverBackground.png"),1,1);
        retry = new UIButton(new Vector2(100,50),resourceManager.GetTexture("retry.png"));
        returnToMenu = new UIButton(new Vector2(450,50),resourceManager.GetTexture("creditsReturn.png"));
    }

    @Override
    public void DoBeforeEntering() {
    }

    @Override
    public void DoBeforeLeaving() {
    }

    @Override
    public void Act() {
        ClickHandler();
    }

    private void ClickHandler()
    {
        if (inputManager.IsTouchedDown())
        {
            Vector2 touch = inputManager.GetTouchPoint();
            if(returnToMenu.IsClicked(touch))
            {
                gameMaster.GetStateMachine().PerformTransition(Transition.MainMenuTransition);
            }
            if(retry.IsClicked(touch))
            {
                gameMaster.GetStateMachine().PerformTransition(Transition.PlayerRegularTransition);
            }
        }
    }

    @Override
    public void Render(Batch batch)
    {
        background.Render(batch);
        retry.Render(batch);
        returnToMenu.Render(batch);
    }
}
