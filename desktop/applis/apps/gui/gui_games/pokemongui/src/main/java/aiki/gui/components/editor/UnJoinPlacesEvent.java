package aiki.gui.components.editor;

import code.gui.events.*;

public final class UnJoinPlacesEvent implements AbsActionListener {
    private final ContentComponentModelUniqLevelLinks form;

    public UnJoinPlacesEvent(ContentComponentModelUniqLevelLinks _c) {
        form = _c;
    }

    @Override
    public void action() {
        form.unjoinPlaces();
    }
}
