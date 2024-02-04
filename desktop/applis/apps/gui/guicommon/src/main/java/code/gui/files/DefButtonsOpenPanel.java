package code.gui.files;

import code.gui.events.AbsActionListener;

public final class DefButtonsOpenPanel extends AbsButtonsOpenPanelImpl {

    @Override
    protected AbsActionListener buildEvent(FileOpenDialogContent _content) {
        return new SubmitMouseEvent(_content);
    }
}
