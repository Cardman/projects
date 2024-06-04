package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class SampleGroupFrame extends GroupFrame implements AbsOpenQuit,AbsChildFrame {
    public SampleGroupFrame(String _lg, AbstractProgramInfos _list, StringMap<String> _ms) {
        super(_list);
        GuiBaseUtil.choose(_lg, this, _ms);
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    @Override
    public void changeLanguage(String _language) {
        setLanguageKey(_language);
    }

    @Override
    public void quit() {
        nativeExit();
    }

    @Override
    public void setDialogIcon(AbsCommonFrame _group) {
        setIconImage(_group.getImageIconFrame());
    }

    @Override
    public void closeWindow() {
        setVisible(false);
    }
}
