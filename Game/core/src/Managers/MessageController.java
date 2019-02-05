package Managers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import GameObjects.UI.Image;

public final class MessageController
{
    //constants
    private final int MESSAGE_BOX_START_PHASE = 0;
    private final int MESSAGE_BOX_2ND_TOWER_PHASE = 4;
    private final int MESSAGE_BOX_UPGRADE_TOWER_PHASE = 16;

    //controls
    private int allowFlag;
    private int ignorePhase;

    //messages boxes
    private Image startMessageBox;
    private Image secondTowerMessageBox;
    private Image upgradeTowerMessageBox;

    //sounds
    private Sound sound;

    public MessageController(ResourceManager resourceManager)
    {
        startMessageBox = new Image(new Vector2(20,120),resourceManager.GetMessage("start"));
        secondTowerMessageBox = new Image(new Vector2(20,120), resourceManager.GetMessage("second"));
        upgradeTowerMessageBox = new Image(new Vector2(20,120),resourceManager.GetMessage("upgrade"));
        sound = resourceManager.GetSound("Sounds/message.ogg");
        allowFlag = 0x0;
        ignorePhase = -1;
    }

    public boolean DisplayMessage(int phaseId)
    {
        if(ignorePhase == phaseId)
        {
            sound.play(1.0f);
            return false;
        }
        switch(phaseId)
        {
            case MESSAGE_BOX_START_PHASE:
                allowFlag = 0x1;
                break;
            case MESSAGE_BOX_2ND_TOWER_PHASE:
                allowFlag = 0x2;
                break;
            case MESSAGE_BOX_UPGRADE_TOWER_PHASE:
                allowFlag = 0x4;
                break;
            default:
                allowFlag = 0x0;
                break;
        }

        return allowFlag != 0;
    }

    public void Render(Batch batch)
    {
        if(allowFlag == 0x1)
            startMessageBox.Render(batch);
        else if(allowFlag == 0x2)
            secondTowerMessageBox.Render(batch);
        else if(allowFlag == 0x4)
            upgradeTowerMessageBox.Render(batch);
    }

    public void Reset()
    {
        allowFlag = 0x0;
    }

    public void Reset(int ignorePhase)
    {
        Reset();
        this.ignorePhase = ignorePhase;
    }
}
