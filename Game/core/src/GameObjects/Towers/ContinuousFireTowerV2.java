package GameObjects.Towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import Factories.TowerFactory;
import Factories.TowerType;
import GameObjects.Tower;

public class ContinuousFireTowerV2 extends Tower
{
    public final static int cost = 120;

    public ContinuousFireTowerV2(Vector2 position, Texture texture, TowerFactory towerFactory)
    {
        super(position, 0, texture, 120, 0.10f, 6, towerFactory, TowerType.ContinuousFireV2);
    }
}
