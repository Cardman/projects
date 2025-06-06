package code.expressionlanguage.adv;

import code.gui.events.AbsAutoCompleteListener;

public final class DocumentTextChange implements AbsAutoCompleteListener {
    private final TabEditor editor;

    public DocumentTextChange(TabEditor _editor) {
        editor = _editor;
    }
    @Override
    public void insertUpdate(int _off, int _len) {
        update(false);
    }

    @Override
    public void removeUpdate(int _off, int _len) {
        update(false);
    }

    @Override
    public void changedUpdate(int _off, int _len) {
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
            _ed.waitAndSubmit(new UpdatingEditorQuick(_ed));
            return;
        }
        if (_ed.isEnabledSyntax()) {
            _ed.waitAndSubmit(new UpdatingEditor(_ed));
        }
    }
}
