package aiki.gui.components.walk;



import aiki.facade.FacadeGame;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.maths.LgInt;

public class TmRenderer implements AbsCustCellRenderGene<String> {

    private final int sideLength;

    private final FacadeGame facade;

    private boolean selected;

    private String name;

    private LgInt price;
    private final AbstractImageFactory fact;

    public TmRenderer(AbstractImageFactory _fact, FacadeGame _facade) {
        fact = _fact;
        facade = _facade;
        sideLength = facade.getMap().getSideLength();
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, String _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        selected = _isSelected;
        name = _info;
//        short tm_ = facade.getData().getTm().getKeys(name).first();
        short tm_ = facade.getData().getTmByMove(name).first();
        price = facade.getData().getTmPrice().getVal(tm_);
        AbstractImage i_ = fact.newImageRgb(150,sideLength);
        paintComponent(i_);
        return i_;
    }

    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, 150 - 1, getHeight() - 1);
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(facade.translateMove(name), 0, getHeight());
        _g.drawString(price.toNumberString(), 100, getHeight());
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0,0, 150 -1,getHeight()-1);
        }
    }

    public AbstractImageFactory getImageFactory() {
        return fact;
    }

    public int getHeight() {
        return sideLength;
    }

}
