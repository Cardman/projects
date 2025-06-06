package aiki.gui.components.editor;

import aiki.util.*;
import code.gui.*;

public final class ChangeItemLinkTileEvent implements ListSelection {
    private final AbsContentComponentModelLevelLinks form;
    private final GeneComponentModelImgSelect select;
    private final NullablePoint point;
    private final boolean left;

    public ChangeItemLinkTileEvent(AbsContentComponentModelLevelLinks _g, GeneComponentModelImgSelect _s, NullablePoint _p, boolean _l) {
        form = _g;
        this.select = _s;
        this.point = _p;
        left = _l;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        form.applySelectItem(point,select, left);
    }
}
