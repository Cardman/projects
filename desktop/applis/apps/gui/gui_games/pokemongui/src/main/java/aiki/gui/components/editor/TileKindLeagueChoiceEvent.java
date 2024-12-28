package aiki.gui.components.editor;

import code.gui.events.*;

public final class TileKindLeagueChoiceEvent implements AbsActionListener {
    private final ContentComponentModelLevelLeague form;
    private final String key;

    public TileKindLeagueChoiceEvent(ContentComponentModelLevelLeague _f, String _k) {
        this.form = _f;
        this.key = _k;
    }
    @Override
    public void action() {
        form.choose(key);
        form.events();
    }
}
