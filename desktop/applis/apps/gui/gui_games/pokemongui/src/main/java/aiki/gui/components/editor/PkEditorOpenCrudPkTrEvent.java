package aiki.gui.components.editor;

import code.gui.events.AbsActionListener;

public final class PkEditorOpenCrudPkTrEvent implements AbsActionListener {
    private final WindowPkEditor window;

    public PkEditorOpenCrudPkTrEvent(WindowPkEditor _w) {
        this.window = _w;
    }
    @Override
    public void action() {
        window.openCrudPkTr();
    }
}
