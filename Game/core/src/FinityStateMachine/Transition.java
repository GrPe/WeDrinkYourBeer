package FinityStateMachine;

public enum Transition
{
    NullTransition,

    PlayerRegularTransition,
    CreditsTransition,
    MainMenuTransition,
    PlayerFailMissionTransition,
    PlayerSuccessMission,

    TowerWaitingFor,
    TowerAttack
}
