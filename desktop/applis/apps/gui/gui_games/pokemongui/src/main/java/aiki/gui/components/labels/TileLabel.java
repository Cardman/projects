package aiki.gui.components.labels;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import code.gui.CustGraphics;
import code.gui.PaintableLabel;
import code.gui.images.ConverterGraphicBufferedImage;

public class TileLabel extends PaintableLabel {

    private BufferedImage image;

    public TileLabel(int[][] _image, int _sideLength) {
        image = ConverterGraphicBufferedImage.decodeToImage(_image);
        setPreferredSize(new Dimension(_sideLength, _sideLength));
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.drawImage(image, 0, 0);
    }
}
