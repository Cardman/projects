package aiki.gui.components.editor;

import code.gui.events.*;

public final class NewDataSetEvent implements AbsActionListener {
    private final WindowPkEditor window;

    public NewDataSetEvent(WindowPkEditor _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.newData();
    }
}
