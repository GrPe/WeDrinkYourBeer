package FinityStateMachine.Player;

import FinityStateMachine.State;
import FinityStateMachine.StateID;

public class RegularState extends State
{
    public RegularState() {
        super.stateID = StateID.PlayerRegular;
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
