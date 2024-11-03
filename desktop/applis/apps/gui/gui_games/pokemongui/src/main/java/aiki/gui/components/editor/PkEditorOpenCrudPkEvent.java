package aiki.gui.components.editor;

import code.gui.events.AbsActionListener;

public final class PkEditorOpenCrudPkEvent implements AbsActionListener {
    private final WindowPkEditor window;

    public PkEditorOpenCrudPkEvent(WindowPkEditor _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.openCrudPk();
    }
}
