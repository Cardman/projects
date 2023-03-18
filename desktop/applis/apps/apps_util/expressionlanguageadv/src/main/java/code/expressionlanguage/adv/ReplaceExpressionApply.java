package code.expressionlanguage.adv;

import code.gui.AbsTextPane;
import code.gui.events.AbsActionListener;

public final class ReplaceExpressionApply implements AbsActionListener {
    private final TabEditor current;

    public ReplaceExpressionApply(TabEditor _editor) {
        current = _editor;
    }

    @Override
    public void action() {
        AbsTextPane editor_ = current.getCenter();
        current.setEnabledSyntax(false);
        editor_.setText(current.getPreview().getText());
        current.setEnabledSyntax(true);
        FindAction.updateEditorStyle(current);
    }

}
