package aiki.gui.components.editor;

import code.gui.*;

public class ChangeItemTileLeagueEvent implements ListSelection {
    private final ContentComponentModelLevelLeague form;
    public ChangeItemTileLeagueEvent(ContentComponentModelLevelLeague _g) {
        form = _g;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        form.applySelectItem();
    }
}
