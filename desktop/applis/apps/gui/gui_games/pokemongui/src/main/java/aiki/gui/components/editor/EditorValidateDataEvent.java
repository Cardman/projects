package aiki.gui.components.editor;

import code.gui.events.*;

public final class EditorValidateDataEvent implements AbsActionListener {
    private final WindowPkEditor window;

    public EditorValidateDataEvent(WindowPkEditor _w) {
        this.window = _w;
    }
    @Override
    public void action() {
        window.getValidateDataSet().setEnabled(false);
        window.getModal().set(true);
        window.getFrames().getThreadFactory().newStartedThread(new EditorValidateDataTask(window));
    }
}
