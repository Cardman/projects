package code.expressionlanguage.adv;

import code.gui.AbsTextField;
import code.gui.AbsTextPane;
import code.gui.events.AbsActionListener;

public final class ReplaceOneAction implements AbsActionListener {
    private final TabEditor current;

    public ReplaceOneAction(TabEditor _editor) {
        current = _editor;
    }

    @Override
    public void action() {
        AbsTextPane editor_ = current.getCenter();
        AbsTextField replacer_ = current.getReplacer();
        String s_ = replacer_.getText();
        editor_.replaceSelection(s_);
        new UpdatingEditorAndSelect(current).run();
    }

}
