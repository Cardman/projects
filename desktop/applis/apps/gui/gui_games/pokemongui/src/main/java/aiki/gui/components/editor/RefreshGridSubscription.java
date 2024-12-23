package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.initialize.*;

public final class RefreshGridSubscription implements SubscribedTranslation {
    private final FacadeGame facadeGame;
    private final FormLevelGrid grid;
    private final Coords coords;
    private final Place placeGrid;
    private final Level levelGrid;

    public RefreshGridSubscription(FacadeGame _f, FormLevelGrid _g, Coords _c, Place _pg, Level _lg) {
        this.facadeGame = _f;
        this.grid = _g;
        this.coords = _c;
        this.placeGrid = _pg;
        this.levelGrid = _lg;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _current) {
        Points<int[][]> frontTiles_ = Level.getLevelForegroundImage(facadeGame.getData(),coords, placeGrid,levelGrid);
        grid.setupForeground(frontTiles_);
        grid.refreshImg();
    }
}
