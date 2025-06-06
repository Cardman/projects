package aiki.gui.components.editor;

import code.gui.events.*;

public final class LoadDataSetEvent implements AbsActionListener {
    private final WindowPkEditor window;

    public LoadDataSetEvent(WindowPkEditor _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.loadDataInPanel();
    }
}
