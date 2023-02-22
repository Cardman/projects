package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ChangeMessagesEvent implements AbsActionListener {
    private final WindowCdmEditor windowCdmEditor;
    private OutputDialogMessages outputDialogMessages;

    public ChangeMessagesEvent(WindowCdmEditor _w) {
        this.windowCdmEditor = _w;
    }

    @Override
    public void action() {
        outputDialogMessages = new OutputDialogMessages(windowCdmEditor);
    }

    public OutputDialogMessages getOutputDialogMessages() {
        return outputDialogMessages;
    }
}
