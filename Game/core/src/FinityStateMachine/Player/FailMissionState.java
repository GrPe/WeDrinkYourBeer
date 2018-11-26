package FinityStateMachine.Player;

import FinityStateMachine.State;
import FinityStateMachine.StateID;

public class FailMissionState extends State
{
    public FailMissionState() {
        this.stateID = StateID.PlayerFailMission;
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
