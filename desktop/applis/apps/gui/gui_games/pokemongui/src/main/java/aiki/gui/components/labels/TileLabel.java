package aiki.gui.components.labels;
import java.awt.Dimension;

import code.gui.PaintableLabel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;

public class TileLabel extends PaintableLabel {

    private AbstractImage image;

    public TileLabel(AbstractImageFactory _fact, int[][] _image, int _sideLength) {
        image = ConverterGraphicBufferedImage.decodeToImage(_fact,_image);
        setPreferredSize(new Dimension(_sideLength, _sideLength));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.drawImage(image, 0, 0);
    }
}
