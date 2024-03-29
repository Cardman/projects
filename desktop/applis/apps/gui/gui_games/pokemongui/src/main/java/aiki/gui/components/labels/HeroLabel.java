package aiki.gui.components.labels;


import aiki.gui.components.AbsMetaLabelPk;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.AbsCompoFactory;

public final class HeroLabel extends AbsMetaLabelPk {

    private int[][] image;

    private boolean selected;
    private AbstractImageFactory fact;

    public HeroLabel(AbstractImageFactory _fact, int[][] _image, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        fact = _fact;
        image = _image;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.drawImage(ConverterGraphicBufferedImage.decodeToImage(fact,image), 0, 0);
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        }
    }
}
