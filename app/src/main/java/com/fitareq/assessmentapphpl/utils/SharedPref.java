package com.fitareq.assessmentapphpl.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public void saveValueToPref(Context context, String key, String value) {
        if (pref == null) {
            pref = context.getSharedPreferences(AppConst.PREF_NAME, Context.MODE_PRIVATE);
            editor = pref.edit();
        }
        editor.putString(key, value).apply();
    }

    public String getValueFromPref(Context context, String key){
        if (pref == null) {
            pref = context.getSharedPreferences(AppConst.PREF_NAME, Context.MODE_PRIVATE);
        }
        return pref.getString(key, null);
    }
}
