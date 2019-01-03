package FinityStateMachine;


import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class State
{
    HashMap<Transition,StateID> map = new HashMap<Transition, StateID>();

    protected StateID stateID;
    public StateID getStateID()
    {
        return stateID;
    }

    public void AddTransition(Transition transition, StateID id)
    {
        if(transition == Transition.NullTransition)
        {
            Gdx.app.log("State Machine","NullTransition is not allowed here");
            return;
        }

        if(stateID == StateID.NullStateID)
        {
            Gdx.app.log("State Machine", "NullStateID is not allowed here");
            return;
        }

        if(map.containsKey(transition))
        {
            Gdx.app.log("State Machine", "Transition is already been added");
            return;
        }

        map.put(transition,id);
    }

    public void DeleteTransition(Transition transition)
    {
        if(transition == Transition.NullTransition)
        {
            Gdx.app.log("State Machine", "Cannot delete NullTransition");
            return;
        }

        if(!map.containsKey(transition))
        {
            Gdx.app.log("State Machine", "This Transition not exists in this State");
            return;
        }

        map.remove(transition);
    }

    public StateID getOutputState(Transition transition)
    {
        if(map.containsKey(transition))
        {
            return map.get(transition);
        }
        return StateID.NullStateID;
    }

    public void DoBeforeEntering()
    {
        //is virtual
    }

    public void DoBeforeLeaving()
    {
        //is virtual
    }

    public void Act()
    {
        //is virtual
    }

    public < E > void Act(ArrayList<E> list)
    {
        //is virtual
    }

}
