package Data;

public final class Phase
{
    private final int enemyId;
    private final int numberOfEnemy;

    Phase(int enemyId, int numberOfEnemy) {
        this.enemyId = enemyId;
        this.numberOfEnemy = numberOfEnemy;
    }

    public int GetEnemyId() {
        return enemyId;
    }

    public int GetNumberOfEnemy() {
        return numberOfEnemy;
    }
}
