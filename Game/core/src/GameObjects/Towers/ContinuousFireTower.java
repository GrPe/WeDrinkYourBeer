package GameObjects.Towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import Factories.TowerFactory;
import Factories.TowerType;
import GameObjects.Tower;

public class ContinuousFireTower extends Tower
{
    public final static int cost = 90;

    public ContinuousFireTower(Vector2 position, Texture texture, TowerFactory towerFactory)
    {
        super(position, 0, texture, 115, 0.11f, 3, towerFactory, TowerType.ContinuousFire);
    }
}
