package Managers;

public class CoinsManager
{
    private int coins;

    public CoinsManager(int coins) {
        this.coins = coins;
    }

    public int GetCoins() {
        return coins;
    }

    public void SetCoins(int coins) {
        this.coins = coins;
    }

    public void AddCoins(int val)
    {
        coins +=val;
    }

    public void SubtactCoins(int val)
    {
        coins -= val;
    }

    public boolean HasEnoughCoins(int val)
    {
        return coins >= val;
    }

}
