package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.util.core.DefaultUniformingString;

public final class UndoRedoAction implements AbsActionListener {
    private final TabEditor editor;
    private final int diff;

    public UndoRedoAction(TabEditor _e, int _d) {
        this.editor = _e;
        diff = _d;
    }

    @Override
    public void action() {
        int n_ = editor.getCurrentText()+diff;
        editor.setCurrentText(n_);
        editor.centerText(new DefaultUniformingString().apply(editor.getTexts().get(n_)));
        updateRedoUndo(editor);
        DocumentTextChange.updateEditorText(editor);
    }

    static void updateRedoUndo(TabEditor _ed) {
        int a_ = _ed.getCurrentText();
        _ed.getUndo().setEnabled(a_ > 0);
        _ed.getRedo().setEnabled(a_ + 1 < _ed.getTexts().size());
    }
}
