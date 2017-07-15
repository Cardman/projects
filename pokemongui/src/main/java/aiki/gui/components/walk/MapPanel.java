package aiki.gui.components.walk;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import code.images.ConverterBufferedImage;
import code.util.TreeMap;
import aiki.facade.FacadeGame;
import aiki.gui.components.labels.TileLabel;
import aiki.gui.listeners.TileListener;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.TileMiniMap;

public class MapPanel extends JPanel {

    public void init(FacadeGame _facade, ScenePanel _scene) {
        setLayout(new GridLayout(_facade.getMapHeight(), _facade.getMapWidth(), 0, 0));
        removeAll();
        TreeMap<MiniMapCoords, String> images_;
        images_ = _facade.getImages();
        int sideLength_ = _facade.getMap().getSideLength();
        for (MiniMapCoords t: images_.getKeys()) {
            String img_ = images_.getVal(t);
            TileMiniMap info_ = _facade.getMap().getMiniMap().getVal(t);
            if (info_.isHeros() && info_.getPlace() == _facade.getGame().getPlayerCoords().getNumberPlace()) {
                img_ = ConverterBufferedImage.stackImages(img_, _facade.getMiniHeros());
            }
            TileLabel tile_ = new TileLabel(img_, sideLength_);
            String tooltip_ = _facade.getName(t.getXcoords(), t.getYcoords());
            if (!tooltip_.isEmpty()) {
                tile_.setToolTipText(tooltip_);
            }
            tile_.addMouseListener(new TileListener(_scene, t.getXcoords(), t.getYcoords()));
            add(tile_);
        }
        setPreferredSize(new Dimension(_facade.getMapWidth()*sideLength_, _facade.getMapHeight()*sideLength_));
    }
}
