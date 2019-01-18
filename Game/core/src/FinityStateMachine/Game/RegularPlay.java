package FinityStateMachine.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import Factories.TowerType;
import FinityStateMachine.State;
import FinityStateMachine.StateID;
import GameObjects.Towers.ContinuousFireTower;
import GameObjects.Towers.SingleFireTower;
import Managers.*;

public class RegularPlay extends State
{
    //shared
    private Managers.ResourceManager resourceManager;
    private InputManager inputManager;

    //internal
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private UIManager uiManager;
    private TowerManager towerManager;

    private float timer = 0;
    private boolean nextPhase = false;
    private int coins;
    private TowerType towerTypeToInsert;

    public RegularPlay(ResourceManager resourceManager, InputManager inputManager)
    {
        super.stateID = StateID.RegularPlayState;

        this.resourceManager = resourceManager;
        this.inputManager = inputManager;

        levelManager = new LevelManager(resourceManager);
        enemyManager = new EnemyManager(levelManager.GetSpawnPointPosition(),resourceManager);
        uiManager = new UIManager(resourceManager);
        towerManager = new TowerManager(levelManager,resourceManager);
    }

    @Override
    public void DoBeforeEntering()
    {
        coins = 300;
        uiManager.SetBaseHpLabel(levelManager.GetBase().GetHp(),levelManager.GetBase().GetMaxHp());
        towerTypeToInsert = TowerType.None;
    }

    @Override
    public void DoBeforeLeaving()
    {

    }

    @Override
    public void Act()
    {
        enemyManager.Update();
        towerManager.Update(enemyManager.GetEnemies());
        ClickedHandler();

        coins += enemyManager.GetKilledEnemyFromLastCheck()*10;
        uiManager.SetCoinsLabel(coins);

        EnemyInBase();
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
            timer = 3;
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
            nextPhase = false;
            enemyManager.NewWay(8);
        }
    }

    private void ClickedHandler()
    {
        if(inputManager.IsTouchedDown())
        {
            Vector2 click = RoundTo60(inputManager.GetTouchPoint());

            if(uiManager.IsTowerMenuButtonClicked(click))
            {
                TowerMenuVisibility();
            }
            else if(uiManager.IsReturnToMainMenuButtonClicked(click))
            {
                towerTypeToInsert = TowerType.None;
                //todo
            }
            else if(uiManager.IsSingleFireTowerButtonClicked(click))
            {
                if(coins >= SingleFireTower.cost)
                {
                    coins -= SingleFireTower.cost;
                    ChooseTower(TowerType.SingleFire);
                }
            }
            else if(uiManager.IsContinuousFireTowerButtonClicked(click))
            {
                if(coins >= ContinuousFireTower.cost)
                {
                    coins -= ContinuousFireTower.cost;
                    ChooseTower(TowerType.ContinuousFire);
                }
            }
            else if(uiManager.IsHarvesterTowerButtonClicked(click))
            {
                //todo
                ChooseTower(TowerType.None);
            }
            else if(towerTypeToInsert != TowerType.None)
            {
                if(InsertTower(click))
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
        return towerManager.SetTower(position,towerTypeToInsert);
    }

    private Vector2 RoundTo60(Vector3 position)
    {
        //return new Vector2(position.x,position.y);
        return new Vector2(((int)position.x/60)*60,((int)position.y/60)*60);
    }
}
