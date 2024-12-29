package aiki.gui.components.editor;

import aiki.map.enums.*;
import code.gui.events.*;

public final class SelectDirJoinEvent implements AbsActionListener {
    private final ContentComponentModelUniqLevelLinks form;
    private final Direction direction;
    private final boolean left;
    public SelectDirJoinEvent(ContentComponentModelUniqLevelLinks _c, Direction _d, boolean _l) {
        form = _c;
        direction = _d;
        left = _l;
    }

    @Override
    public void action() {
        form.selectDir(direction,left);
    }
}
