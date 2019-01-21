package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import FinityStateMachine.Game.Credits;
import FinityStateMachine.Game.MainMenu;
import FinityStateMachine.Game.RegularPlay;
import FinityStateMachine.StateID;
import FinityStateMachine.StateMachine;
import FinityStateMachine.Transition;
import Managers.CoinsManager;
import Managers.InputManager;
import Managers.ResourceManager;

public class GameMaster extends ApplicationAdapter
{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ResourceManager resourceManager;
    private StateMachine stateMachine;
    private InputManager inputManager;
    private CoinsManager coinsManager;

    @Override
    public void create()
    {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,780,500);
        batch = new SpriteBatch();

        inputManager = new InputManager(camera);
        Gdx.input.setInputProcessor(inputManager);

        coinsManager = new CoinsManager(100);

        resourceManager = new ResourceManager();
        InitStateMachine();
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
    }

    private void Update()
    {
        camera.update();
        stateMachine.getCurrentState().Act();
    }

    private void InitStateMachine()
    {
        stateMachine = new StateMachine();

        RegularPlay regularPlay = new RegularPlay(this,coinsManager, resourceManager,inputManager);
        MainMenu mainMenu = new MainMenu(this,resourceManager,inputManager);
        Credits credits = new Credits(this,inputManager,resourceManager);

        regularPlay.AddTransition(Transition.MainMenuTransition, StateID.MainMenuState);

        mainMenu.AddTransition(Transition.PlayerRegularTransition,StateID.RegularPlayState);
        mainMenu.AddTransition(Transition.CreditsTransition,StateID.CreditsState);

        credits.AddTransition(Transition.MainMenuTransition, StateID.MainMenuState);

        stateMachine.AddState(mainMenu);
        stateMachine.AddState(regularPlay);
        stateMachine.AddState(credits);
    }

    public StateMachine GetStateMachine()
    {
        return stateMachine;
    }

}
