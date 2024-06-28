package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ClosePanelAction implements AbsActionListener {
    private final TabEditor editor;

    public ClosePanelAction(TabEditor _editor) {
        editor = _editor;
    }

    @Override
    public void action() {
        editor.getNavModifPanel().setVisible(false);
        new UpdatingEditorQuick(editor).run();
//        editor.getCenter().setEditable(true);
        editor.getCenter().requestFocusInWindow();
    }
}
