package FinityStateMachine;

public enum Transition
{
    NullTransition,

    EnemyStartWalking,
    EnemyDie,

    PlayerRegular,
    PlayerBuildTower,
    PlayerFailMission,
    PlayerSuccessMission,

    TowerWaitingFor,
    TowerAttack,
    TowerBuild,
    TowerUpgrade
}
