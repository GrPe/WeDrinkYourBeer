package FinityStateMachine.Player;

import FinityStateMachine.State;
import FinityStateMachine.StateID;

public class SuccessMissionState extends State
{
    public SuccessMissionState() {
        super.stateID = StateID.PlayerSuccessMission;
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
