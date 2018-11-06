package GameObjects;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    private Vector2 position;
    private float rotation;

    public GameObject()
    {
        position =  new Vector2(0,0);
        rotation = 0f;
    }

    public GameObject(Vector2 position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
