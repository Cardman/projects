package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.util.*;
import code.gui.events.*;

public final class ApplyTileMiniMapEvent implements AbsActionListener {
    private final FormMiniMapGrid grid;
    private final FormMiniMapTile form;
    private final FacadeGame facadeGame;
    private final int first;
    private final int second;
    private final boolean remove;
    public ApplyTileMiniMapEvent(FormMiniMapGrid _g, FormMiniMapTile _t, FacadeGame _f, int _j, int _i, boolean _rem) {
        grid = _g;
        form = _t;
        facadeGame = _f;
        first = _j;
        second = _i;
        remove = _rem;
    }

    @Override
    public void action() {
        MiniMapCoords key_ = new MiniMapCoords(first, second);
        int e_ = facadeGame.getData().getMap().getMiniMap().indexOfEntry(key_);
        if (e_ < 0) {
            if (remove) {
                return;
            }
            facadeGame.getData().getMap().getMiniMap().addEntry(key_,form.buildEntity());
        } else {
            if (remove) {
                facadeGame.getData().getMap().getMiniMap().getList().remove(e_);
            } else {
                facadeGame.getData().getMap().getMiniMap().getList().set(e_, new MiniMapCoordsTile(key_,form.buildEntity()));
            }
        }
        grid.setupGrid(true);
    }
}
