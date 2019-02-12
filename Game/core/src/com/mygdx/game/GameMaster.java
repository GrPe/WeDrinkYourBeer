package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Locale;

import FinityStateMachine.Game.Credits;
import FinityStateMachine.Game.GameOver;
import FinityStateMachine.Game.MainMenu;
import FinityStateMachine.Game.RegularPlay;
import FinityStateMachine.Game.WinScreen;
import FinityStateMachine.StateID;
import FinityStateMachine.StateMachine;
import FinityStateMachine.Transition;
import Managers.InputManager;
import Managers.ResourceManager;

public class GameMaster extends ApplicationAdapter
{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ResourceManager resourceManager;
    private StateMachine stateMachine;
    private InputManager inputManager;

    private Music music;
    private boolean musicIsPlaying = true;

    @Override
    public void create()
    {
        Gdx.app.setLogLevel(Application.LOG_NONE);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,780,500);
        batch = new SpriteBatch();

        music  = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Music.mp3"));
        MusicConfig.SetMusicEnable(true);
        music.setLooping(true);
        music.setVolume(0.8f);
        music.play();

        SetLanguage();

        inputManager = new InputManager(camera);
        Gdx.input.setInputProcessor(inputManager);

        resourceManager = new ResourceManager();
        InitStateMachine();
    }

    private void SetLanguage()
    {
        String locale = Locale.getDefault().getLanguage();
        if(locale.equals(new Locale("pl").getLanguage()))
            LanguageConfig.SetLanguage(LanguageConfig.Language.PL);
        else
            LanguageConfig.SetLanguage(LanguageConfig.Language.EN);
    }


    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0,0xff,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        stateMachine.getCurrentState().Render(batch);
        batch.end();
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        resourceManager.dispose();
        music.dispose();
    }

    private void Update()
    {
        camera.update();
        SetMusic();
        stateMachine.getCurrentState().Act();
    }

    private void SetMusic()
    {
        if(!MusicConfig.GetMusicEnable() && MusicConfig.GetMusicEnable() != musicIsPlaying)
        {
            musicIsPlaying = false;
            music.stop();
        }
        else if (MusicConfig.GetMusicEnable() != musicIsPlaying)
        {
            musicIsPlaying = true;
            music.play();
        }
    }

    private void InitStateMachine()
    {
        stateMachine = new StateMachine();

        RegularPlay regularPlay = new RegularPlay(this, resourceManager,inputManager);
        MainMenu mainMenu = new MainMenu(this,resourceManager,inputManager);
        Credits credits = new Credits(this,inputManager,resourceManager);
        GameOver gameOver = new GameOver(this,resourceManager,inputManager);
        WinScreen winScreen = new WinScreen(this,resourceManager,inputManager);

        regularPlay.AddTransition(Transition.MainMenuTransition, StateID.MainMenuState);
        regularPlay.AddTransition(Transition.PlayerFailMissionTransition,StateID.PlayerFailMissionState);
        regularPlay.AddTransition(Transition.PlayerSuccessMission, StateID.PlayerSuccessMission);

        mainMenu.AddTransition(Transition.PlayerRegularTransition,StateID.RegularPlayState);
        mainMenu.AddTransition(Transition.CreditsTransition,StateID.CreditsState);

        credits.AddTransition(Transition.MainMenuTransition, StateID.MainMenuState);

        gameOver.AddTransition(Transition.PlayerRegularTransition, StateID.RegularPlayState);
        gameOver.AddTransition(Transition.MainMenuTransition, StateID.MainMenuState);

        winScreen.AddTransition(Transition.MainMenuTransition,StateID.MainMenuState);

        stateMachine.AddState(mainMenu);
        stateMachine.AddState(regularPlay);
        stateMachine.AddState(credits);
        stateMachine.AddState(gameOver);
        stateMachine.AddState(winScreen);
    }

    public StateMachine GetStateMachine()
    {
        return stateMachine;
    }

}
