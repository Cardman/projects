package aiki.gui.components.editor;

import code.gui.events.*;

public final class TileKindBuildingChoiceEvent implements AbsActionListener {
    private final ContentComponentModelCity form;
    private final String key;

    public TileKindBuildingChoiceEvent(ContentComponentModelCity _f, String _k) {
        this.form = _f;
        this.key = _k;
    }
    @Override
    public void action() {
        form.choose(key);
        form.events();
    }
}
