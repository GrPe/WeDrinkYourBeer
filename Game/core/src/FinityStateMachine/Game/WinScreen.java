package FinityStateMachine.Game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameMaster;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import FinityStateMachine.Transition;
import GameObjects.UI.Image;
import GameObjects.UI.UIButton;
import Managers.InputManager;
import Managers.ResourceManager;

public final class WinScreen extends State
{
    private GameMaster gameMaster;
    private ResourceManager resourceManager;
    private InputManager inputManager;

    private Image background;
    private UIButton returnToMenu;

    public WinScreen(GameMaster gameMaster, ResourceManager resourceManager, InputManager inputManager) {
        super.stateID = StateID.PlayerSuccessMission;
        this.gameMaster = gameMaster;
        this.resourceManager = resourceManager;
        this.inputManager = inputManager;

        InitUI();
    }

    private void InitUI()
    {
        background = new Image(new Vector2(-40,-40),resourceManager.GetTexture("winwin.png"),1,1);
        returnToMenu = new UIButton(new Vector2(275,50),resourceManager.GetTexture("creditsReturn.png"), resourceManager.GetSound("Sounds/click.ogg"));
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
        }
    }

    @Override
    public void Render(Batch batch)
    {
        background.Render(batch);
        returnToMenu.Render(batch);
    }
}
