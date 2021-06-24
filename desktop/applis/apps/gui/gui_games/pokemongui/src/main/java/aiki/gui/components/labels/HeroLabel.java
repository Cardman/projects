package aiki.gui.components.labels;
import java.awt.Color;

import code.gui.PaintableLabel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;

public class HeroLabel extends PaintableLabel {

    private int[][] image;

    private boolean selected;
    private AbstractImageFactory fact;

    public HeroLabel(AbstractImageFactory _fact, int[][] _image) {
        fact = _fact;
        image = _image;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.drawImage(ConverterGraphicBufferedImage.decodeToImage(fact,image), 0, 0);
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        }
    }
}
