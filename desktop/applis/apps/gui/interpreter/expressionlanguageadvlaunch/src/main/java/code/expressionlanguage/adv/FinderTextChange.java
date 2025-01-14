package code.expressionlanguage.adv;

import code.gui.events.AbsAutoCompleteListener;

public final class FinderTextChange implements AbsAutoCompleteListener {
    private final TabEditor editor;

    public FinderTextChange(TabEditor _editor) {
        editor = _editor;
    }
    @Override
    public void insertUpdate(int _off, int _len) {
        update();
    }

    @Override
    public void removeUpdate(int _off, int _len) {
        update();
    }

    @Override
    public void changedUpdate(int _off, int _len) {
        update();
    }

    private void update() {
        editor.waitAndSubmit(new UpdatingEditorAndSelect(editor));
    }
}
