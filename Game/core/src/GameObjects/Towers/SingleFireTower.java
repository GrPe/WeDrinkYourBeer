package GameObjects.Towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import Factories.TowerFactory;
import Factories.TowerType;
import GameObjects.Tower;

public class SingleFireTower extends Tower
{
    public final static int cost = 50;

    public SingleFireTower(Vector2 position, Texture texture, TowerFactory towerFactory)
    {
        super(position, 0, texture, 135, 0.95f, 20, towerFactory, TowerType.SingleFire);
    }
}
