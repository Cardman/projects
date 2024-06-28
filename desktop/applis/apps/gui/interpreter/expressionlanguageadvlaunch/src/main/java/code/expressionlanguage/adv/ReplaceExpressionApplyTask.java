package code.expressionlanguage.adv;

import code.util.core.DefaultUniformingString;

public final class ReplaceExpressionApplyTask implements Runnable {
    private final TabEditor current;

    public ReplaceExpressionApplyTask(TabEditor _editor) {
        current = _editor;
    }

    @Override
    public void run() {
        current.setEnabledSyntax(false);
        current.centerText(new DefaultUniformingString().apply(current.previewText()));
        current.setEnabledSyntax(true);
        FindAction.updateEditorStyle(current);
    }

}
