package aiki.gui.components.walk;
import java.awt.Dimension;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.labels.TileLabel;
import aiki.gui.listeners.TileListener;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.TileMiniMap;
import code.gui.Panel;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.images.ConverterBufferedImage;
import code.util.TreeMap;

public class MapPanel {

    private Panel container;

    public void init(WindowAiki _fact, FacadeGame _facade, ScenePanel _scene) {
        AbsCompoFactory compoFactory_ = _fact.getCompoFactory();
        container = Panel.newGrid(_facade.getMapHeight(), _facade.getMapWidth());
        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
        TreeMap<MiniMapCoords, int[][]> images_;
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
            tile_.addMouseListener(new TileListener(_scene, t.getXcoords(), t.getYcoords()));
            container.add(tile_);
        }
        container.setPreferredSize(new Dimension(_facade.getMapWidth()*sideLength_, _facade.getMapHeight()*sideLength_));
    }

    public Panel getContainer() {
        return container;
    }
}
