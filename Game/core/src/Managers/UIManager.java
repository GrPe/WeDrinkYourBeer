package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

import GameObjects.UI.Label;

public class UIManager
{
    private Label baseHp;
    private Label timerAndCounter;
    //button

    public UIManager()
    {
        //load fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Joystick.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 30;
        parameter.borderStraight = true;

        baseHp = new Label(generator.generateFont(parameter), new Vector2(20,450),"12/12");
        timerAndCounter = new Label(generator.generateFont(parameter), new Vector2(320,450), "40");
        generator.dispose();
    }

    public void draw(Batch batch)
    {
        baseHp.draw(batch);
        timerAndCounter.draw(batch);
    }

}
