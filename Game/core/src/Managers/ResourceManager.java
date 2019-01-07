package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Disposable;

public class ResourceManager implements Disposable
{
    private AssetManager manager;
    private BitmapFont bitmapFont;

    public ResourceManager()
    {
        manager = new AssetManager();
        LoadFont();
        LoadTexture();
        manager.finishLoading();
    }

    private void LoadFont()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Joystick.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderStraight = true;
        bitmapFont = generator.generateFont(parameter);
        generator.dispose();
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
        return bitmapFont;
    }

    @Override
    public void dispose()
    {
        bitmapFont.dispose();
        manager.dispose();
    }

    private void LoadTexture()
    {
        manager.load("enemy_test.png",Texture.class);
        manager.load("buildings2.png",Texture.class);
        manager.load("way.png",Texture.class);
        manager.load("base.png",Texture.class);
        manager.load("spawnPoint.png",Texture.class);
        manager.load("tmp_tower.png",Texture.class);
        manager.load("bullet.png",Texture.class);
    }


}
