package FinityStateMachine;

import com.badlogic.gdx.Gdx;

import java.util.LinkedList;

public class StateMachine {
    private LinkedList<State> states;

    private StateID currentStateID;
    public StateID getCurrentStateID()
    {
        return currentStateID;
    }

    private State currentState;
    public State getCurrentState()
    {
        return currentState;
    }

    public StateMachine()
    {
        states = new LinkedList<State>();
    }

    public void AddState(State state)
    {
        if(state == null)
        {
            Gdx.app.log("State Machine", "Null is not allowed here");
            return;
        }

        if(states.size() == 0)
        {
            states.add(state);
            currentState = state;
            currentStateID = state.getStateID();
            currentState.DoBeforeEntering();
            return;
        }

        for(State s : states)
        {
            if(state.getStateID() == s.getStateID()) {
                Gdx.app.log("State Machine", "Impossible to add state " + state.getStateID().toString() +
                        "because state already been added");
                return;
            }
        }
        states.add(state);

    }

    public void DeleteState(StateID stateID)
    {
        if(stateID == StateID.NullStateID)
        {
            Gdx.app.log("State Machine", "Cannot delete NullState");
            return;
        }

        for(State state : states)
        {
            if(state.getStateID() == stateID)
            {
                states.remove(state);
                return;
            }
        }
        Gdx.app.log("State Machine", "This state not exists in state machine");
    }

    public void PerformTransition(Transition transition)
    {
        if(transition == Transition.NullTransition)
        {
            Gdx.app.log("State Machine", "NullTransition is not allowed for a real transition");
            return;
        }

        StateID id = currentState.getOutputState(transition);
        if(id == StateID.NullStateID)
        {
            Gdx.app.log("State Machine:", currentStateID.toString() + " does not have a target state for transition");
            return;
        }

        currentStateID = id;
        for(State state : states)
        {
            if(state.getStateID() == currentStateID)
            {
                currentState.DoBeforeLeaving();

                currentState = state;

                currentState.DoBeforeEntering();
            }
        }
    }



}
