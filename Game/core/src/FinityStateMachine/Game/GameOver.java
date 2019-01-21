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
    private CoinsManager coinsManager;

    private Image background;
    private Label score;
    private UIButton returnToMenu;
    private UIButton retry;

    public GameOver(GameMaster gameMaster, ResourceManager resourceManager, InputManager inputManager, CoinsManager coinsManager) {
        this.gameMaster = gameMaster;
        this.resourceManager = resourceManager;
        this.inputManager = inputManager;
        this.coinsManager = coinsManager;

        InitUI();
    }

    private void InitUI() {

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
        score.Render(batch);
        retry.Render(batch);
        returnToMenu.Render(batch);
    }
}
