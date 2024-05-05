package aiki.gui.components.walk;


import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.labels.TileLabel;
import aiki.gui.listeners.TileListener;
import aiki.main.PkNonModalEvent;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.MiniMapCoordsTileInts;
import aiki.map.util.TileMiniMap;
import code.gui.AbsPanel;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.images.ConverterBufferedImage;
import code.util.CustList;

public class MapPanel {

    private AbsPanel container;
    private final CustList<TileLabel> tiles = new CustList<TileLabel>();

    public void init(WindowAiki _fact, FacadeGame _facade, ScenePanel _scene) {
        AbsCompoFactory compoFactory_ = _fact.getCompoFactory();
        container = compoFactory_.newGrid(_facade.getMapHeight(), _facade.getMapWidth());
        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
        MiniMapCoordsTileInts images_;
        images_ = _facade.getImages();
        int sideLength_ = _facade.getMap().getSideLength();
        for (MiniMapCoords t: images_.getKeys()) {
            int[][] img_ = images_.getVal(t);
            TileMiniMap info_ = _facade.getMap().getMiniMap().getVal(t);
            if (info_.isHeros() && info_.getPlace() == _facade.getGame().getPlayerCoords().getNumberPlace()) {
                img_ = ConverterBufferedImage.stackImages(img_, _facade.getMiniHeros());
            }
            TileLabel tile_ = new TileLabel(imageFactory_, img_, sideLength_, compoFactory_);
            String tooltip_ = _facade.getName(t.getXcoords(), t.getYcoords());
            if (!tooltip_.isEmpty()) {
                tile_.setToolTipText(tooltip_);
            }
            tile_.addMouseListener(new PkNonModalEvent(_fact.getModal()),new TileListener(_scene, t.getXcoords(), t.getYcoords()));
            container.add(tile_.getPaintableLabel());
            tiles.add(tile_);
        }
        container.setPreferredSize(new MetaDimension(_facade.getMapWidth()*sideLength_, _facade.getMapHeight()*sideLength_));
    }

    public AbsPanel getContainer() {
        return container;
    }

    public CustList<TileLabel> getTiles() {
        return tiles;
    }
}
