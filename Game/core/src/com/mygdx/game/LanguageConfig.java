package com.mygdx.game;

public final class LanguageConfig
{
    public enum Language {PL, EN}
    private static Language currentLanguage;

    public static Language GetLanguage() {return currentLanguage;}
    static void SetLanguage(Language language) { currentLanguage = language;}
}
