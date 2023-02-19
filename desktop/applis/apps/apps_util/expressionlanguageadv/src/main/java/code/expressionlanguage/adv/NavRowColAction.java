package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class NavRowColAction implements AbsActionListener {
    private final TabEditor current;
    private OutputDialogNavLine outputDialogNavLine;

    public NavRowColAction(TabEditor _editor) {
        current = _editor;
    }
    @Override
    public void action() {
        outputDialogNavLine = new OutputDialogNavLine(current);
        outputDialogNavLine.update();
    }

    public OutputDialogNavLine getOutputDialogNavLine() {
        return outputDialogNavLine;
    }
}
