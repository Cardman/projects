package aiki.gui.components.labels;


import aiki.gui.components.AbsMetaLabelPk;
import aiki.gui.components.walk.IntTileRender;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;

public final class TileLabel extends AbsMetaLabelPk {

    private final AbstractImage image;

    public TileLabel(IntTileRender _tileRender, AbstractImageFactory _fact, int[][] _image, int _sideLength, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        image = _tileRender.renderNoTrans(_fact,_image,_sideLength,_sideLength);
        setPreferredSize(new MetaDimension(_sideLength, _sideLength));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.drawImage(image, 0, 0);
    }
}
