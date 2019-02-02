package Data;

import java.util.ArrayList;

public final class LevelPhase
{
    private ArrayList<Phase> phases;

    LevelPhase()
    {
        phases = new ArrayList<Phase>();
    }

    public int GetSize()
    {
        return phases.size();
    }

    void Add(Phase phase)
    {
        phases.add(phase);
    }

    public Phase GetPhase(int i)
    {
        return phases.get(i);
    }
}
