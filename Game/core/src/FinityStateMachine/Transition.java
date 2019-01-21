package FinityStateMachine;

public enum Transition
{
    NullTransition,

    EnemyStartWalking,
    EnemyDie,

    PlayerRegularTransition,
    CreditsTransition,
    MainMenuTransition,
    PlayerFailMissionTransition,
    PlayerSuccessMission,

    TowerWaitingFor,
    TowerAttack,
    TowerBuild,
    TowerUpgrade
}
