package aiki.gui.components.labels;
import java.awt.Dimension;

import code.gui.AbsMetaLabel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.AbsCompoFactory;

public final class TileLabel extends AbsMetaLabel {

    private AbstractImage image;

    public TileLabel(AbstractImageFactory _fact, int[][] _image, int _sideLength, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        image = ConverterGraphicBufferedImage.decodeToImage(_fact,_image);
        setPreferredSize(new Dimension(_sideLength, _sideLength));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.drawImage(image, 0, 0);
    }
}
