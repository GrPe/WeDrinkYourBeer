package FinityStateMachine.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameMaster;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import FinityStateMachine.Transition;
import GameObjects.UI.Image;
import GameObjects.UI.UIButton;
import Managers.*;

public class MainMenu extends State
{
    private ResourceManager resourceManager;
    private InputManager inputManager;
    private GameMaster gameMaster;

    private Image background;
    private UIButton startGame;
    private UIButton goToCredits;
    private UIButton exit;

    public MainMenu(GameMaster gameMaster, ResourceManager resourceManager, InputManager inputManager)
    {
        super.stateID = StateID.MainMenuState;

        this.gameMaster = gameMaster;
        this.resourceManager = resourceManager;
        this.inputManager = inputManager;

        InitUI();
    }

    private void InitUI()
    {
        background = new Image(new Vector2(-40,-40),resourceManager.GetTexture("menuBackground.png"),1,1);
        startGame = new UIButton(new Vector2(275,270),resourceManager.GetTexture("mainMenuStartButton.png"), resourceManager.GetSound("Sounds/click.ogg"));
        goToCredits = new UIButton(new Vector2(275,190),resourceManager.GetTexture("mainMenuCredits.png"),resourceManager.GetSound("Sounds/click.ogg"));
        exit = new UIButton(new Vector2(275,110),resourceManager.GetTexture("mainMenuExit.png"),resourceManager.GetSound("Sounds/click.ogg"));
    }

    private void ClickHandler()
    {
        if(inputManager.IsTouchedDown())
        {
            Vector2 click = inputManager.GetTouchPoint();
            if(startGame.IsClicked(click))
            {
                gameMaster.GetStateMachine().PerformTransition(Transition.PlayerRegularTransition);
            }
            if(goToCredits.IsClicked(click))
            {
                gameMaster.GetStateMachine().PerformTransition(Transition.CreditsTransition);
            }
            if(exit.IsClicked(click))
            {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void Render(Batch batch)
    {
        background.Render(batch);
        startGame.Render(batch);
        goToCredits.Render(batch);
        exit.Render(batch);
    }

    @Override
    public void Act()
    {
        ClickHandler();
    }
}
