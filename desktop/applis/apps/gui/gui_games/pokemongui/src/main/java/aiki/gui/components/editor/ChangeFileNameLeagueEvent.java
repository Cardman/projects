package aiki.gui.components.editor;

import code.gui.*;

public final class ChangeFileNameLeagueEvent implements ListSelection {
    private final ContentComponentModelLeagueLinks form;

    public ChangeFileNameLeagueEvent(ContentComponentModelLeagueLinks _f) {
        this.form = _f;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        form.updateFileName();
    }
}
