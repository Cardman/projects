package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ChangeCommentsEvent implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;
    private OutputDialogComments outputDialogComments;

    public ChangeCommentsEvent(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        outputDialogComments = new OutputDialogComments(windowCdmEditor);
        outputDialogComments.update();
        windowCdmEditor.afterChangingComments(outputDialogComments.getResult());
    }

    public OutputDialogComments getOutputDialogComments() {
        return outputDialogComments;
    }
}
