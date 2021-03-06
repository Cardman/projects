package aiki.gui.components.walk;
import java.awt.Color;
import java.awt.Dimension;

import aiki.facade.FacadeGame;
import code.gui.*;
import code.maths.LgInt;

public class TmRenderer extends CustCellRender<String> {

    private final int sideLength;

    private final FacadeGame facade;

    private boolean selected;

    private String name;

    private LgInt price;

    public TmRenderer(FacadeGame _facade) {
        facade = _facade;
        sideLength = facade.getMap().getSideLength();
    }

    @Override
    public void getListCellRendererComponent(
            PreparedLabel _currentLab, int _arg2,
            boolean _selected, boolean _arg4) {
        selected = _selected;
        name = getList().get(_arg2);
//        short tm_ = facade.getData().getTm().getKeys(name).first();
        short tm_ = facade.getData().getTmByMove(name).first();
        price = facade.getData().getTmPrice().getVal(tm_);
        _currentLab.setPreferredSize(new Dimension(150,sideLength));
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.setColor(Color.BLACK);
        _g.drawString(facade.translateMove(name), 0, getHeight());
        _g.drawString(price.toNumberString(), 100, getHeight());
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        }
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
