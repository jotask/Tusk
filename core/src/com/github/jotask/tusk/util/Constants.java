package com.github.jotask.tusk.util;

import com.github.jotask.tusk.states.STATE;

public class Constants {

    public static final String TITLE = "Tusk";
    public static final String VERSION = "v0.15[PRE-ALPHA]";

    private static final int divider = 1;
    public static final int APP_WIDTH = 800 / divider;
    public static final int APP_HEIGHT = 600 / divider;
    public static final int BACKGROUND_FPS = 30;
    public static final int FOREGROUND_FPS = 30;

    public static final int SCALE = 50; // 50

    public static final STATE DEFAULT_STATE = STATE.MENU;

    public static final int PPM = 16; // RECOMMENDED 30 16 for me

    public static final boolean DEFAULT_GAME_INFO = true;
    public static final boolean DEFAULT_DEBUG = true;

}