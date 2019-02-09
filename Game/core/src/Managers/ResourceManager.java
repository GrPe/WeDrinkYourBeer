package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.LanguageConfig;

import java.util.ArrayList;

public class ResourceManager implements Disposable
{
    private AssetManager manager;
    private BitmapFont bitmapFont;
    private ArrayList<Vector2> navLink;

    public ResourceManager()
    {
        manager = new AssetManager();
        navLink = new ArrayList<Vector2>();
        LoadFont();
        LoadTexture();
        LoadEnemyAtlases();
        LoadSounds();
        LoadMessages();
        manager.finishLoading();
    }

    private void LoadFont()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/Joystick.ttf"));
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

    public Texture GetEnemyTexture(String name)
    {
        if(manager.isLoaded("Enemies/" + name + ".png"))
        {
            return manager.get("Enemies/" + name + ".png",Texture.class);
        }
        return null;
    }


    public Sound GetSound(String path)
    {
        if(manager.isLoaded("Sounds/" + path))
        {
            return manager.get("Sounds/" + path,Sound.class);
        }
        return null;
    }

    public BitmapFont GetFont()
    {
        return bitmapFont;
    }

    public Texture GetMessage(String name)
    {
        if(manager.isLoaded("Messages/" + name + ".png") && manager.isLoaded("Messages/" + name + "EN.png"))
        {
            if(LanguageConfig.GetLanguage() == LanguageConfig.Language.PL)
                return manager.get("Messages/" + name + ".png");
            else
                return manager.get("Messages/" + name + "EN.png");
        }
        return null;
    }

    @Override
    public void dispose()
    {
        bitmapFont.dispose();
        manager.dispose();
    }

    private void LoadSounds()
    {
        manager.load("Sounds/message.ogg", Sound.class);
        manager.load("Sounds/putTower.ogg",Sound.class);
        manager.load("Sounds/shoot.wav",Sound.class);
        manager.load("Sounds/click.ogg",Sound.class);
    }

    private void LoadMessages()
    {
        //PL
        manager.load("Messages/start.png", Texture.class);
        manager.load("Messages/second.png", Texture.class);
        manager.load("Messages/upgrade.png", Texture.class);

        //EN
        manager.load("Messages/startEN.png", Texture.class);
        manager.load("Messages/secondEN.png", Texture.class);
        manager.load("Messages/upgradeEN.png", Texture.class);
    }

    private void LoadEnemyAtlases()
    {
        manager.load("Enemies/studentV1.png",Texture.class);
        manager.load("Enemies/studentLaw.png",Texture.class);
        manager.load("Enemies/studentAWF.png",Texture.class);
        manager.load("Enemies/studentIT.png",Texture.class);
        manager.load("Enemies/studentMD.png",Texture.class);
        manager.load("Enemies/bossV1.png",Texture.class);
        manager.load("Enemies/studentV2.png",Texture.class);
        manager.load("Enemies/studentChinese.png",Texture.class);
        manager.load("Enemies/studentSamurai.png",Texture.class);
        manager.load("Enemies/studentRussia.png",Texture.class);
        manager.load("Enemies/devil.png",Texture.class);
        manager.load("Enemies/bossV2.png",Texture.class);
        manager.load("Enemies/bossV3.png",Texture.class);
    }

    private void LoadTexture()
    {
        //texture
        manager.load("environment.png",Texture.class);
        manager.load("way.png",Texture.class);
        manager.load("base.png",Texture.class);
        manager.load("spawnPoint.png",Texture.class);
        manager.load("bullet.png",Texture.class);
        manager.load("singleTowerLv01.png",Texture.class);
        manager.load("singleTowerLv02.png",Texture.class);
        manager.load("towerContinuousLv01.png",Texture.class);
        manager.load("towerContinuousLv02.png",Texture.class);

        //icons
        manager.load("mainMenu.png",Texture.class);
        manager.load("towerMenu.png",Texture.class);
        manager.load("singleTowerMenuButton.png",Texture.class);
        manager.load("singleTowerMenuButtonOff.png",Texture.class);
        manager.load("continuousFireTowerButton.png",Texture.class);
        manager.load("continuousFireTowerButtonOff.png",Texture.class);
        manager.load("harvesterTowerButton.png",Texture.class);
        manager.load("harvesterTowerButtonOff.png",Texture.class);
        manager.load("baseHpIcon.png",Texture.class);
        manager.load("timerIcon.png",Texture.class);
        manager.load("coinsIcon.png",Texture.class);

        //topBar
        manager.load("topBarGame.png",Texture.class);

        //main menu
        manager.load("menuBackground.png",Texture.class);
        manager.load("mainMenuStartButton.png",Texture.class);
        manager.load("mainMenuCredits.png",Texture.class);
        manager.load("mainMenuExit.png",Texture.class);

        //credits
        manager.load("creditsReturn.png",Texture.class);
        manager.load("credits.png",Texture.class);

        //game over
        manager.load("retry.png",Texture.class);
        manager.load("gameOverBackground.png",Texture.class);

        //win screen
        manager.load("winwin.png",Texture.class);

        //decorations
        for(int i = 1 ;i != 6; i++)
            manager.load("environment" + Integer.toString(i) + ".png",Texture.class);
    }

    public ArrayList<Vector2> GetNavLink()
    {
        return navLink;
    }

    public void SetNavLink(ArrayList<Vector2> navLink)
    {
        this.navLink = navLink;
    }
}
