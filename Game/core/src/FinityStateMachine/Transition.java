package FinityStateMachine;

public enum Transition
{
    NullTransition,

    EnemyStartWalking,
    EnemyDie,

    PlayerRegularTransition,
    CreditsTransition,
    MainMenuTransition,
    PlayerFailMission,
    PlayerSuccessMission,

    TowerWaitingFor,
    TowerAttack,
    TowerBuild,
    TowerUpgrade
}
