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
    //final
    private final int ACTIVE_SECOND_TOWER = 3;
    private final int ACTIVE_UPGRADE_TOWER = 15;

    //shared
    private GameMaster gameMaster;
    private InputManager inputManager;
    private CoinsManager coinsManager;

    //internal
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private UIManager uiManager;
    private TowerManager towerManager;
    private MessageController messageController;

    private float timer = 0;
    private boolean nextPhase = false;
    private boolean messageDisplayedInThisPhase = false;
    private boolean messageSoundWasPlayed = false;

    //inserting and upgrading tower
    private boolean isTowerSelected;
    private TowerType towerTypeToInsert;

    public RegularPlay(GameMaster gameMaster, ResourceManager resourceManager, InputManager inputManager)
    {
        super.stateID = StateID.RegularPlayState;

        this.gameMaster = gameMaster;
        this.inputManager = inputManager;
        this.coinsManager = new CoinsManager(110);

        levelManager = new LevelManager(resourceManager);
        enemyManager = new EnemyManager(levelManager.GetSpawnPointPosition(),resourceManager);
        uiManager = new UIManager(resourceManager);
        towerManager = new TowerManager(levelManager,resourceManager);
        messageController = new MessageController(resourceManager);
    }

    @Override
    public void DoBeforeEntering()
    {
        coinsManager.SetCoins(110);
        uiManager.SetBaseHpLabel(levelManager.GetBase().GetHp(),levelManager.GetBase().GetMaxHp());
        towerTypeToInsert = TowerType.None;
        isTowerSelected = false;
        messageDisplayedInThisPhase = false;
        messageSoundWasPlayed = false;
    }

    @Override
    public void DoBeforeLeaving()
    {
        levelManager.Reset();
        towerManager.Reset();
        enemyManager.Reset();
        messageController.Reset();
        timer = 7f;
    }

    @Override
    public void Act()
    {
        if(Message()) return;

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

    private boolean Message()
    {
        if (messageController.DisplayMessage(levelManager.GetCurrentPhase()) && !messageDisplayedInThisPhase)
        {
            if(!messageSoundWasPlayed)
            {
                messageController.CallASound();
                messageSoundWasPlayed = true;
            }

            if(inputManager.IsTouchedDown())
            {
                messageDisplayedInThisPhase = true;
                messageSoundWasPlayed = false;
                messageController.Reset(levelManager.GetCurrentPhase());
            }
            return true;
        }
        return false;
    }

    @Override
    public void Render(Batch batch)
    {
        levelManager.Render(batch);
        enemyManager.Render(batch);
        towerManager.Render(batch);
        uiManager.Render(batch);
        messageController.Render(batch);
    }

    private void UpdateTowerMenu()
    {
        uiManager.SetSingleFireTowerActive(coinsManager.HasEnoughCoins(SingleFireTower.cost) && !isTowerSelected);
        uiManager.SetContinuousFireTowerActive(coinsManager.HasEnoughCoins(ContinuousFireTower.cost) && levelManager.GetCurrentPhase() > ACTIVE_SECOND_TOWER && !isTowerSelected);
        uiManager.SetTowerUpgradeActive(coinsManager.HasEnoughCoins(SingleFireTowerV2.cost) && levelManager.GetCurrentPhase() > ACTIVE_UPGRADE_TOWER && !isTowerSelected);
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
            messageDisplayedInThisPhase = false;
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

            if(uiManager.IsReturnToMainMenuButtonClicked(click))
            {
                gameMaster.GetStateMachine().PerformTransition(Transition.MainMenuTransition);
            }
            else if(uiManager.IsSingleFireTowerButtonClicked(click))
            {
                coinsManager.SubtractCoins(SingleFireTower.cost);
                ChooseTower(TowerType.SingleFire);
            }
            else if(uiManager.IsContinuousFireTowerButtonClicked(click))
            {
                coinsManager.SubtractCoins(ContinuousFireTower.cost);
                ChooseTower(TowerType.ContinuousFire);
            }
            else if(uiManager.IsUpgradeTowerButtonClicked(click))
            {
                coinsManager.SubtractCoins(SingleFireTowerV2.cost);
                ChooseTower(TowerType.Upgrade);
            }
            else if(uiManager.IsTowerBarClicked(click) && isTowerSelected)
            {
                CancelTowerSelection(towerTypeToInsert);
            }
            else if(isTowerSelected)
            {
                if(towerTypeToInsert == TowerType.Upgrade)
                {
                    if(UpgradeTower(RoundTo60(click)))
                    {
                        isTowerSelected = false;
                        towerTypeToInsert = TowerType.None;
                    }
                }
                else if(InsertTower(RoundTo60(click)))
                {
                    isTowerSelected = false;
                    towerTypeToInsert = TowerType.None;
                }
            }
        }
    }

    private void CancelTowerSelection(TowerType type)
    {
        if(type == TowerType.Upgrade)
        {
            coinsManager.AddCoins(SingleFireTowerV2.cost);
        }
        else if(type == TowerType.SingleFire)
        {
            coinsManager.AddCoins(SingleFireTower.cost);
        }
        else if(type == TowerType.ContinuousFire)
        {
            coinsManager.AddCoins(ContinuousFireTower.cost);
        }

        towerTypeToInsert = TowerType.None;
        isTowerSelected = false;
    }

    private void ChooseTower(TowerType towerType)
    {
        isTowerSelected = true;
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
