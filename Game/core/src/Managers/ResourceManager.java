package Managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class ResourceManager
{
    private AssetManager manager;

    public ResourceManager()
    {
        manager = new AssetManager();
        LoadFont();
        manager.finishLoading();
    }

    public void LoadTextures(String path)
    {
        manager.load(path,Texture.class);
    }

    private void LoadFont()
    {
        FreetypeFontLoader.FreeTypeFontLoaderParameter parameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parameter.fontFileName = "Fonts/Joystick.ttf";
        parameter.fontParameters.size = 30;
        parameter.fontParameters.borderStraight = true;
        manager.load("Fonts/Joystick.ttf",BitmapFont.class,parameter);
    }

    public Texture GetTexture(String path)
    {
        if(manager.isLoaded(path))
        {
            return manager.get(path,Texture.class);
        }
        return null;
    }

    public BitmapFont GetFont()
    {
        return manager.get("Fonts/Joystick.ttf",BitmapFont.class);
    }

    public void FinishLoading()
    {
        manager.finishLoading();
    }

    public void Clear()
    {
        manager.clear();
    }

}
