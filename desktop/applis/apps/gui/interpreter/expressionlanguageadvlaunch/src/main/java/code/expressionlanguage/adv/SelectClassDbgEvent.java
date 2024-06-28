package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class SelectClassDbgEvent implements AbsActionListener {
    private final FormFindReplaceExpression editor;

    public SelectClassDbgEvent(FormFindReplaceExpression _e) {
        this.editor = _e;
    }

    @Override
    public void action() {
        editor.usedType(editor.getFinderExpClasses().getText());
    }
}
