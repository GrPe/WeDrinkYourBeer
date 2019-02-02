package GameObjects.Towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import Factories.TowerFactory;
import Factories.TowerType;
import GameObjects.Tower;

public class SingleFireTowerV2 extends Tower
{
    public final static int cost = 120;

    public SingleFireTowerV2(Vector2 position, Texture texture, TowerFactory towerFactory)
    {
        super(position, 0, texture, 160, 0.95f, 35,towerFactory, TowerType.SingleFireV2);
    }
}
