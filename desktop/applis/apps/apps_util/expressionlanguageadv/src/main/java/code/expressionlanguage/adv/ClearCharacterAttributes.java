package code.expressionlanguage.adv;

import code.gui.AbsTextPane;

public final class ClearCharacterAttributes implements Runnable {
    private final AbsTextPane tabEditor;

    public ClearCharacterAttributes(AbsTextPane _t) {
        this.tabEditor = _t;
    }

    @Override
    public void run() {
        String t_ = tabEditor.getText();
        tabEditor.clearCharacterAttributes(0,t_.length());
    }
}
