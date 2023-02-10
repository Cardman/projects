package code.expressionlanguage.adv;

import code.gui.events.AbsAutoCompleteListener;

public final class FinderTextChange implements AbsAutoCompleteListener {
    private final TabEditor editor;

    public FinderTextChange(TabEditor _editor) {
        editor = _editor;
    }
    @Override
    public void insertUpdate() {
        update();
    }

    @Override
    public void removeUpdate() {
        update();
    }

    @Override
    public void changedUpdate() {
        update();
    }

    private void update() {
        editor.getFactories().getCompoFactory().invokeLater(new UpdatingEditor(editor));
    }
}
