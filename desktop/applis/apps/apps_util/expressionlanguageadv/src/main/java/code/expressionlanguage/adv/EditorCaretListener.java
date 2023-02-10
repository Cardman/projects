package code.expressionlanguage.adv;

import code.gui.events.AbsCaretListener;

public final class EditorCaretListener implements AbsCaretListener {
    private final TabEditor tabEditor;

    public EditorCaretListener(TabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void caretUpdate(int _begin, int _end) {
        if (!tabEditor.getFinderPanel().isVisible()) {
            return;
        }
        tabEditor.getFactories().getCompoFactory().invokeLater(new UpdatingEditor(tabEditor));
    }
}
