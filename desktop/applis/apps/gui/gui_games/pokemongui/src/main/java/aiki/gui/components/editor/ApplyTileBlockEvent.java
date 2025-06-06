package aiki.gui.components.editor;

import aiki.map.levels.*;
import aiki.map.util.*;
import aiki.util.*;
import code.gui.events.*;
import code.util.*;

public final class ApplyTileBlockEvent implements AbsActionListener {
    private final FormLevelGrid grid;
    private final FormBlockTile form;
    private final Point pt;
    private final boolean remove;
    public ApplyTileBlockEvent(FormLevelGrid _g, FormBlockTile _t, Point _p, boolean _rem) {
        grid = _g;
        form = _t;
        pt = _p;
        remove = _rem;
    }

    @Override
    public void action() {
        Limits previous_ = Level.limits(grid.getEdited());
        int e_ = grid.getEdited().indexOfEntry(pt);
        if (e_ < 0) {
            if (remove) {
                return;
            }
            grid.getEdited().addEntry(pt,form.buildEntity());
        } else {
            if (remove) {
                EntryCust<Point, Block> ref_ = Level.getEntryBlockByPoint(pt, grid.getEdited());
                for (EntryCust<Point,int[][]> f: grid.getForeground().entryList()) {
                    if (Level.getEntryBlockByPoint(f.getKey(),grid.getEdited()) == ref_) {
                        return;
                    }
                }
                grid.getEdited().remove(e_);
            } else {
                grid.getEdited().setValue(e_,form.buildEntity());
            }
        }
        Limits next_ = Level.limits(grid.getEdited());
        grid.readjust(previous_,next_);
        grid.refreshImg(0, 0);
    }
}
