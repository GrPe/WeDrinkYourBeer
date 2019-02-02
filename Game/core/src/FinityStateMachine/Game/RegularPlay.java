package FinityStateMachine.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameMaster;

import Factories.TowerType;
import FinityStateMachine.*;
import GameObjects.Towers.*;
import Managers.*;

public class RegularPlay extends State
{
    //shared
    private GameMaster gameMaster;
    private Managers.ResourceManager resourceManager;
    private InputManager inputManager;
    private CoinsManager coinsManager;

    //internal
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private UIManager uiManager;
    private TowerManager towerManager;

    private float timer = 0;
    private boolean nextPhase = false;
    private TowerType towerTypeToInsert;

    public RegularPlay(GameMaster gameMaster, CoinsManager coinsManager, ResourceManager resourceManager, InputManager inputManager)
    {
        super.stateID = StateID.RegularPlayState;

        this.gameMaster = gameMaster;
        this.resourceManager = resourceManager;
        this.inputManager = inputManager;
        this.coinsManager = coinsManager;

        levelManager = new LevelManager(resourceManager);
        enemyManager = new EnemyManager(levelManager.GetSpawnPointPosition(),resourceManager);
        uiManager = new UIManager(resourceManager);
        towerManager = new TowerManager(levelManager,resourceManager);
    }

    @Override
    public void DoBeforeEntering()
    {
        coinsManager.SetCoins(110);
        uiManager.SetBaseHpLabel(levelManager.GetBase().GetHp(),levelManager.GetBase().GetMaxHp());
        towerTypeToInsert = TowerType.None;
    }

    @Override
    public void DoBeforeLeaving()
    {
        levelManager.Reset();
        towerManager.Reset();
        enemyManager.Reset();
        uiManager.SetTowerSelectionMenuVisibility(false);
        timer = 7f;
    }

    @Override
    public void Act()
    {
        enemyManager.Update();
        towerManager.Update(enemyManager.GetEnemies());
        UpdateTowerMenu();
        ClickedHandler();

        coinsManager.AddCoins(enemyManager.GetKilledEnemyFromLastCheck()*5);
        uiManager.SetCoinsLabel(coinsManager.GetCoins());

        EnemyInBase();
        GameOver();
        UpdateUI();
        NextPhase();
    }

    @Override
    public void Render(Batch batch)
    {
        levelManager.Render(batch);
        enemyManager.Render(batch);
        towerManager.Render(batch);
        uiManager.Render(batch);
    }

    private void UpdateTowerMenu()
    {
        uiManager.SetSingleFireTowerActive(coinsManager.HasEnoughCoins(SingleFireTower.cost));
        uiManager.SetContinuousFireTowerActive(coinsManager.HasEnoughCoins(ContinuousFireTower.cost) && levelManager.GetCurrentPhase() > 3);
        uiManager.SetTowerUpgradeActive(coinsManager.HasEnoughCoins(SingleFireTowerV2.cost) && levelManager.GetCurrentPhase() > 15);
    }

    private void EnemyInBase()
    {
        int damage = enemyManager.IsEnemyInBase();
        if(damage > 0)
        {
            DealBaseDamage(damage);
        }
    }

    private void DealBaseDamage(int damage)
    {
        levelManager.GetBase().DecreaseHp(damage);
        uiManager.SetBaseHpLabel(levelManager.GetBase().GetHp(), levelManager.GetBase().GetMaxHp());
    }

    private void UpdateUI()
    {
        if(enemyManager.IsEmpty() && timer > 0 && !enemyManager.IsSpawningEnemies())
        {
            UpdateEnemyTimer();
        }
        else
        {
            UpdateEnemyCounter();
        }
    }

    private void UpdateEnemyCounter()
    {
        uiManager.SetTimerAndCounter(enemyManager.Size());
    }

    private void UpdateEnemyTimer()
    {
        uiManager.SetTimerAndCounter((int)timer);
    }

    private void NextPhase()
    {
        if(enemyManager.IsEmpty() && timer <= 0 && !nextPhase)
        {
            timer = 6;
            nextPhase = true;
        }
        if(nextPhase && !enemyManager.IsSpawningEnemies() && enemyManager.IsEmpty())
        {
            CountDownToNextPhase();
        }

    }

    private void CountDownToNextPhase()
    {
        timer -=Gdx.graphics.getDeltaTime();
        if(timer <= 0)
        {
            if(levelManager.IsEndOfLevel())
            {
                gameMaster.GetStateMachine().PerformTransition(Transition.PlayerSuccessMission);
            }
            else
            {
                nextPhase = false;
                enemyManager.NewWay(levelManager.GetNextPhase());
            }
        }
    }

    private void ClickedHandler()
    {
        if(inputManager.IsTouchedDown())
        {
            Vector2 click = inputManager.GetTouchPoint();

            if(uiManager.IsTowerMenuButtonClicked(click))
            {
                TowerMenuVisibility();
            }
            else if(uiManager.IsReturnToMainMenuButtonClicked(click))
            {
                gameMaster.GetStateMachine().PerformTransition(Transition.MainMenuTransition);
            }
            else if(uiManager.IsSingleFireTowerButtonClicked(click))
            {
                if(coinsManager.HasEnoughCoins(SingleFireTower.cost))
                {
                    coinsManager.SubtactCoins(SingleFireTower.cost);
                    ChooseTower(TowerType.SingleFire);
                }
            }
            else if(uiManager.IsContinuousFireTowerButtonClicked(click))
            {
                if(coinsManager.HasEnoughCoins(ContinuousFireTower.cost))
                {
                    coinsManager.SubtactCoins(ContinuousFireTower.cost);
                    ChooseTower(TowerType.ContinuousFire);
                }
            }
            else if(uiManager.IsUpgradeTowerButtonClicked(click))
            {
                if(coinsManager.HasEnoughCoins(SingleFireTowerV2.cost))
                {
                    coinsManager.SubtactCoins(SingleFireTowerV2.cost);
                    ChooseTower(TowerType.Upgrade);
                }
            }
            else if(towerTypeToInsert != TowerType.None)
            {
                if(towerTypeToInsert == TowerType.Upgrade)
                {
                    if(UpgradeTower(RoundTo60(click)))
                    {
                        towerTypeToInsert = TowerType.None;
                    }
                }
                else if(InsertTower(RoundTo60(click)))
                {
                    towerTypeToInsert = TowerType.None;
                }
            }
        }
    }

    private void TowerMenuVisibility()
    {
        if(uiManager.GetTowerSelectionMenuVisibility())
        {
            uiManager.SetTowerSelectionMenuVisibility(false);
        }
        else
        {
            uiManager.SetTowerSelectionMenuVisibility(true);
        }
        towerTypeToInsert = TowerType.None;
    }

    private void ChooseTower(TowerType towerType)
    {
        uiManager.SetTowerSelectionMenuVisibility(false);
        towerTypeToInsert = towerType;
    }

    private boolean InsertTower(Vector2 position)
    {
        levelManager.RemoveDecoration(position);
        return towerManager.SetTower(position,towerTypeToInsert);
    }

    private boolean UpgradeTower(Vector2 position)
    {
        return towerManager.UpgradeTower(position);
    }

    private Vector2 RoundTo60(Vector2 position)
    {
        return new Vector2(((int)position.x/60)*60,((int)position.y/60)*60);
    }

    private void GameOver()
    {
        if(levelManager.GetBase().IsDead())
        {
            gameMaster.GetStateMachine().PerformTransition(Transition.PlayerFailMissionTransition);
        }
    }
}
