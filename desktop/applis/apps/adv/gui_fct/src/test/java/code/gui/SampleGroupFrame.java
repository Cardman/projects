package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class SampleGroupFrame extends GroupFrame {
    public SampleGroupFrame(String _lg, AbstractProgramInfos _list, StringMap<String> _ms) {
        super(_lg, _list, _ms);
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    @Override
    public void changeLanguage(String _language) {
        setLanguageKey(getLanguageKey());
    }

    @Override
    public void quit() {
        nativeExit();
    }
}
