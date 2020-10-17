package com.example.HeadTorsoLegs.utilities;

public class AsyncStr {
    private String str = "";
    private ChangeListener listener;

    public String getStr() {
        return this.str;
    }

    public void setStr(String str) {
        this.str = str;
        if (listener != null) listener.onChange();
    }

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }
}
