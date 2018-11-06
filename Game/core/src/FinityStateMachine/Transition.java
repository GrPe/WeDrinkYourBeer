package FinityStateMachine;

public enum Transition
{
    NullTransition,

    EnemyStartWalking,
    EnemyDie,

    PlayerRegular,
    PlayerFailMission,
    PlayerSuccessMission,

    TowerWaitingFor,
    TowerAttack,
    TowerBuild,
    TowerUpgrade
}
