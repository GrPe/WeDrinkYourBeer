package FinityStateMachine;

public enum Transition
{
    NullTransition,

    EnemyDie,

    PlayerRegularTransition,
    CreditsTransition,
    MainMenuTransition,
    PlayerFailMissionTransition,
    PlayerSuccessMission,

    TowerWaitingFor,
    TowerAttack
}
