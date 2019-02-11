package FinityStateMachine.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameMaster;
import com.mygdx.game.MusicConfig;

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

    private UIButton musicState;

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
        startGame = new UIButton(new Vector2(275,270),resourceManager.GetTexture("mainMenuStartButton.png"), resourceManager.GetSound("click.ogg"));
        goToCredits = new UIButton(new Vector2(275,190),resourceManager.GetTexture("mainMenuCredits.png"),resourceManager.GetSound("click.ogg"));
        exit = new UIButton(new Vector2(275,110),resourceManager.GetTexture("mainMenuExit.png"),resourceManager.GetSound("click.ogg"));
        musicState = new UIButton(new Vector2(720,440),resourceManager.GetTexture("soundIcon.png"),resourceManager.GetSound("click.ogg"));
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
            if(musicState.IsClicked(click))
            {
                if(MusicConfig.GetMusicEnable())
                {
                    MusicConfig.SetMusicEnable(false);
                    MusicConfig.MuteFX();
                }
                else
                {
                    MusicConfig.SetMusicEnable(true);
                    MusicConfig.NormalizeFX();
                }
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
        musicState.Render(batch);
    }

    @Override
    public void Act()
    {
        ClickHandler();
    }
}
