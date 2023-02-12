package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ClosePanelAction implements AbsActionListener {
    private final TabEditor editor;

    public ClosePanelAction(TabEditor _editor) {
        editor = _editor;
    }

    @Override
    public void action() {
        editor.getFinderPanel().setVisible(false);
        new UpdatingEditorQuick(editor).run();
        editor.getCenter().requestFocus();
    }
}
