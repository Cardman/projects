package aiki.gui.components.editor;

import code.gui.events.*;

public final class JoinPlacesEvent implements AbsActionListener {
    private final ContentComponentModelUniqLevelLinks form;

    public JoinPlacesEvent(ContentComponentModelUniqLevelLinks _c) {
        form = _c;
    }

    @Override
    public void action() {
        form.joinPlaces();
    }
}
