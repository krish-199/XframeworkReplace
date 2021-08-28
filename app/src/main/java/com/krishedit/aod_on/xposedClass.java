package com.krishedit.aod_on;


import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.content.res.XResources;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class xposedClass implements IXposedHookZygoteInit, IXposedHookInitPackageResources{

    @Override

    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {

        XResources.setSystemWideReplacement("android", "integer", "config_screenBrightnessDark", 110);
        XResources.setSystemWideReplacement("android", "integer", "config_screenBrightnessDoze", 110);
        XResources.setSystemWideReplacement("android", "integer", "config_screenBrightnessDim", 110);
        XResources.setSystemWideReplacement("android", "integer", "config_screenBrightnessSettingMinimum", 110);
        XResources.setSystemWideReplacement("android", "integer", "config_screenBrightnessSettingDefault", 172);
        XResources.setSystemWideReplacement("android", "bool", "config_skipScreenOnBrightnessRamp", true);
    }

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
        // replacements only for SystemUI
        if (!resparam.packageName.equals("com.android.systemui"))
            return;

        // different ways to specify the resources to be replaced
        resparam.res.setReplacement(0x7f120a17, "Test1"); // WLAN toggle text. You should not do this because the id is not fixed. Only for framework resources, you could use android.R.string.something
        //resparam.res.setReplacement("com.android.systemui:string/quickpanel_bluetooth_text", "WOO!");
       // resparam.res.setReplacement("com.android.systemui", "string", "quick_settings_bluetooth_detail_off_text", "Turn on bluetooth for god's sake!!");
        //resparam.res.setReplacement("com.android.systemui", "integer", "config_maxLevelOfSignalStrengthIndicator", 6);
    }

}