package FinityStateMachine.Tower;

import FinityStateMachine.State;
import FinityStateMachine.StateID;
import GameObjects.Tower;

public class BuildingInProgressState extends State
{
    private Tower tower;

    public BuildingInProgressState(Tower tower) {
        this.tower = tower;
        super.stateID = StateID.TowerBuildingInProgress;
    }

    @Override
    public void DoBeforeEntering() {
    }

    @Override
    public void DoBeforeLeaving() {
    }

    @Override
    public void Act() {
    }
}
