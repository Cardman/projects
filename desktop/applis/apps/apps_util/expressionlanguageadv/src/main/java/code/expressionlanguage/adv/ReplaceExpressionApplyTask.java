package code.expressionlanguage.adv;

import code.gui.AbsTextPane;

public final class ReplaceExpressionApplyTask implements Runnable {
    private final TabEditor current;

    public ReplaceExpressionApplyTask(TabEditor _editor) {
        current = _editor;
    }

    @Override
    public void run() {
        AbsTextPane editor_ = current.getCenter();
        current.setEnabledSyntax(false);
        editor_.setText(current.getPreview().getText());
        current.setEnabledSyntax(true);
        FindAction.updateEditorStyle(current);
    }

}
