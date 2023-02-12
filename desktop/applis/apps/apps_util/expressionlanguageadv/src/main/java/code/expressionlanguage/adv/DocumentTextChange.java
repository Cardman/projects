package code.expressionlanguage.adv;

import code.gui.events.AbsAutoCompleteListener;

public final class DocumentTextChange implements AbsAutoCompleteListener {
    private final TabEditor editor;

    public DocumentTextChange(TabEditor _editor) {
        editor = _editor;
    }
    @Override
    public void insertUpdate() {
        update(false);
    }

    @Override
    public void removeUpdate() {
        update(false);
    }

    @Override
    public void changedUpdate() {
        update(true);
    }

    private void update(boolean _ch) {
        if (_ch) {
            return;
        }
        updateEditorText(editor);
    }

    static void updateEditorText(TabEditor _ed) {
        if (!_ed.getNavModifPanel().isVisible()) {
            _ed.getFactories().getCompoFactory().invokeLater(new UpdatingEditorQuick(_ed));
            return;
        }
        _ed.getFactories().getCompoFactory().invokeLater(new UpdatingEditor(_ed));
    }
}
