package aiki.gui.components.walk;



import aiki.facade.FacadeGame;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.maths.LgInt;

public class TmRenderer extends CustCellRender<String> {

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
    public void getListCellRendererComponent(
            AbsPreparedLabel _currentLab, int _arg2,
            boolean _selected, boolean _arg4) {
        selected = _selected;
        name = get(_arg2);
//        short tm_ = facade.getData().getTm().getKeys(name).first();
        short tm_ = facade.getData().getTmByMove(name).first();
        price = facade.getData().getTmPrice().getVal(tm_);
        _currentLab.setPreferredSize(new MetaDimension(150,sideLength));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(facade.translateMove(name), 0, getHeight());
        _g.drawString(price.toNumberString(), 100, getHeight());
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        }
    }

    @Override
    public AbstractImageFactory getImageFactory() {
        return fact;
    }
    @Override
    public int getHeight() {
        return sideLength;
    }

    @Override
    public int getWidth() {
        return 150;
    }
}
