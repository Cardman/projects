package code.expressionlanguage;


import code.gui.GroupFrame;

public final class MainInitFrame extends GroupFrame {
    protected MainInitFrame(String _lg) {
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
