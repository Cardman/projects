package code.expressionlanguage;


import code.gui.GroupFrame;

public final class OtherFrame extends GroupFrame {
    protected OtherFrame(String _lg) {
        super(_lg);
    }

    @Override
    public void quit() {
        dispose();
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }

    @Override
    public void changeLanguage(String _language) {
    }
}
