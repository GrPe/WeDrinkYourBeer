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

    public Vector2 GetPosition() {
        return position;
    }

    public void SetPosition(Vector2 position) {
        this.position = position;
    }

    public float GetRotation() {
        return rotation;
    }

    public void SetRotation(float rotation) {
        this.rotation = rotation;
    }
}
