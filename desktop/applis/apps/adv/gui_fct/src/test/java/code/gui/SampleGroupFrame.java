package code.gui;

import code.gui.initialize.AbstractProgramInfos;

public final class SampleGroupFrame extends GroupFrame implements AbsOpenQuit,AbsChildFrame {
    public SampleGroupFrame(AbstractProgramInfos _list) {
        super(_list);
        GuiBaseUtil.choose(this, _list);
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
