package FinityStateMachine.Player;

import FinityStateMachine.State;
import FinityStateMachine.StateID;

public class BuildState extends State
{
    public BuildState()
    {
        super.stateID = StateID.PlayerBuild;
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
