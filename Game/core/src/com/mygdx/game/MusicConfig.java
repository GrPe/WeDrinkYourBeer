package com.mygdx.game;

public final class MusicConfig
{
    private static boolean musicEnable = true;
    public static float VOLUMESHOOTING = 0.15f;
    public static float VOLUMEFX = 1.0f;

    public static void MuteFX()
    {
        VOLUMESHOOTING = 0.0f;
        VOLUMEFX = 0.0f;
    }

    public static void NormalizeFX()
    {
        VOLUMESHOOTING = 0.15f;
        VOLUMEFX = 1.0f;
    }


    public static void SetMusicEnable(boolean state)
    {
        musicEnable = state;
    }

    public static boolean GetMusicEnable()
    {
        return musicEnable;
    }
}
