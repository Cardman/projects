package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ClosePanelExpressionAction implements AbsActionListener {
    private final TabEditor editor;

    public ClosePanelExpressionAction(TabEditor _editor) {
        editor = _editor;
    }

    @Override
    public void action() {
        editor.getExpSpli().setVisible(false);
        editor.getMainSplitter().setDividerLocation(editor.getMainSplitter().getHeight());
        new UpdatingEditorQuick(editor).run();
//        editor.getCenter().setEditable(true);
        editor.getCenter().requestFocusInWindow();
    }
}
