package aiki.gui.components.editor;

import code.gui.events.*;

public final class TileKindChoiceEvent implements AbsActionListener {
    private final ContentComponentModelLevelWithWild form;
    private final String key;

    public TileKindChoiceEvent(ContentComponentModelLevelWithWild _f, String _k) {
        this.form = _f;
        this.key = _k;
    }
    @Override
    public void action() {
        form.choose(key);
        form.events();
    }
}
