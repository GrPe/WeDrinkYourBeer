package Managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import GameObjects.UI.Image;
import GameObjects.UI.UIButton;

public final class MessageController
{
    //consts
    private final int MESSAGE_BOX_START_PHASE = 0;
    private final int MESSAGE_BOX_2ND_TOWER_PHASE = 3;
    private final int MESSAGE_BOX_UPGRADE_TOWER_PHASE = 15;
    private final int MESSAGE_BOX_FINAL = 30;

    //controls
    private int allowFlag = 0x0;

    private Image startMessageBox;
    private Image secondTowerMessageBox;
    private Image upgradeTowerMessageBox;
    private Image finalMassageBox;

    public MessageController(ResourceManager resourceManager)
    {
        startMessageBox = new Image(new Vector2(20,20),resourceManager.GetMessage("start"));
        secondTowerMessageBox = new Image(new Vector2(20,20), resourceManager.GetMessage("second"));
        upgradeTowerMessageBox = new Image(new Vector2(20,20),resourceManager.GetMessage("upgrade"));
        finalMassageBox = new Image(new Vector2(20,20),resourceManager.GetMessage("final"));
        allowFlag = 0x0;
    }

    public boolean DisplayMessage(int phaseId)
    {
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
            case MESSAGE_BOX_FINAL:
                allowFlag = 0x8;
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
        else if(allowFlag == 0x8)
            finalMassageBox.Render(batch);
    }

    public void Reset()
    {
        allowFlag = 0x0;
    }
}
