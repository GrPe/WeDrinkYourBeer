package FinityStateMachine.Game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameMaster;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import FinityStateMachine.Transition;
import GameObjects.UI.Image;
import GameObjects.UI.UIButton;
import Managers.InputManager;
import Managers.ResourceManager;

public class Credits extends State
{
    private GameMaster gameMaster;
    private InputManager inputManager;
    private ResourceManager resourceManager;

    private UIButton returnToMainMenu;
    private Image background;

    public Credits(GameMaster gameMaster, InputManager inputManager, ResourceManager resourceManager) {
        super.stateID = StateID.CreditsState;

        this.gameMaster = gameMaster;
        this.inputManager = inputManager;
        this.resourceManager = resourceManager;

        InitUI();
    }

    private void InitUI()
    {
        //background = new Image(new Vector2(0,0), resourceManager.GetTexture("creditsBackground.png"));
        returnToMainMenu = new UIButton(new Vector2(275,50),resourceManager.GetTexture("creditsReturn.png"));
    }

    @Override
    public void Act()
    {
        ClickHandler();
    }


    private void ClickHandler()
    {
        if(inputManager.IsTouchedDown())
        {
            Vector2 click = ToVector2(inputManager.GetTouchPoint());
            if(returnToMainMenu.IsClicked(click))
            {
                gameMaster.GetStateMachine().PerformTransition(Transition.MainMenuTransition);
            }
        }
    }

    private Vector2 ToVector2(Vector3 vec)
    {
        return new Vector2(vec.x,vec.y);
    }


    @Override
    public void Render(Batch batch)
    {
        returnToMainMenu.Render(batch);
    }
}