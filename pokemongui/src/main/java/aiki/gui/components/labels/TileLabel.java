package aiki.gui.components.labels;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import code.images.ConverterBufferedImage;

public class TileLabel extends JLabel {

    private BufferedImage image;

    public TileLabel(int[][] _image, int _sideLength) {
        image = ConverterBufferedImage.decodeToImage(_image);
        setPreferredSize(new Dimension(_sideLength, _sideLength));
    }

    @Override
    protected void paintComponent(Graphics _g) {
        _g.drawImage(image, 0, 0, null);
    }
}
