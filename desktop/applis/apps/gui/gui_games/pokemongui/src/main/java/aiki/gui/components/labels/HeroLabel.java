package aiki.gui.components.labels;


import aiki.gui.components.AbsMetaLabelPk;
import aiki.gui.components.walk.IntTileRender;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;

public final class HeroLabel extends AbsMetaLabelPk {

    private final int[][] image;

    private boolean selected;
    private final AbstractImageFactory fact;
    private final IntTileRender render;

    public HeroLabel(AbstractImageFactory _fact, int[][] _image, AbsCompoFactory _compoFactory, IntTileRender _tileRender) {
        super(_compoFactory);
        fact = _fact;
        image = _image;
        render = _tileRender;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.drawImage(render.render(fact,image,1,1), 0, 0);
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        }
    }
}
