package com.example.samplemenu;

class Constants {

    // values have to be globally unique
    static final String INTENT_ACTION_DISCONNECT = "1000" /*BuildConfig.APPLICATION_ID*/ + ".Disconnect";
    static final String NOTIFICATION_CHANNEL = "1000" /*BuildConfig.APPLICATION_ID*/ + ".Channel";
    static final String INTENT_CLASS_MAIN_ACTIVITY = "1000" /*BuildConfig.APPLICATION_ID*/+ ".MainActivity";

    // values have to be unique within each app
    static final int NOTIFY_MANAGER_START_FOREGROUND_SERVICE = 1001;

    private Constants() {}
}
