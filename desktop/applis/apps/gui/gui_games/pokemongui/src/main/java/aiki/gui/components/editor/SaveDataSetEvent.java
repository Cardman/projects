package aiki.gui.components.editor;

import code.gui.events.*;

public final class SaveDataSetEvent implements AbsActionListener {
    private final WindowPkEditor window;

    public SaveDataSetEvent(WindowPkEditor _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.saveDataInPanel();
    }
}
