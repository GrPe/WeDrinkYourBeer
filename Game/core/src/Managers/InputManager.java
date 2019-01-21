package Managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InputManager implements InputProcessor
{
    private Vector3 touchPoint;
    private OrthographicCamera camera;
    private boolean isTouchedDown;

    public InputManager(OrthographicCamera camera) {
        this.touchPoint = new Vector3();
        this.isTouchedDown = false;
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button != Input.Buttons.LEFT || pointer > 0) return false;
        camera.unproject(touchPoint.set(screenX,screenY,0));
        isTouchedDown = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public Vector2 GetTouchPoint()
    {
        return new Vector2(touchPoint.x, touchPoint.y);
    }

    public boolean IsTouchedDown() {
        if(isTouchedDown)
        {
            isTouchedDown = false;
            return true;
        }
        return false;
    }
}
