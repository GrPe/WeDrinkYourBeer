package Managers;

import com.badlogic.gdx.assets.AssetManager;

public class ResourceManager
{
    private AssetManager manager;

    public ResourceManager()
    {
        manager = new AssetManager();
        LoadTextures();
        LoadFont();
        manager.finishLoading();
    }

    private void LoadTextures()
    {
        //manager.load()
    }

    private void LoadFont()
    {

    }


    public AssetManager getManager()
    {
        return manager;
    }
}
