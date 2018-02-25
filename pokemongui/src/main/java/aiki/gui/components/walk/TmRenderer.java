package aiki.gui.components.walk;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import aiki.facade.FacadeGame;
import code.gui.StringCellRenderer;
import code.maths.LgInt;

public class TmRenderer extends StringCellRenderer {

    private int sideLength;

    private FacadeGame facade;

    private boolean selected;

    private String name;

    private LgInt price;

    public TmRenderer(FacadeGame _facade) {
        facade = _facade;
        sideLength = facade.getMap().getSideLength();
    }

    @Override
    public Component getListCellRendererComponent(
            String _item, int _arg2,
            boolean _selected, boolean _arg4) {
        selected = _selected;
        name = _item;
//        short tm_ = facade.getData().getTm().getKeys(name).first();
        short tm_ = facade.getData().getTmByMove(name).first();
        price = facade.getData().getTmPrice().getVal(tm_);
        setPreferredSize(new Dimension(150,sideLength));
        return this;
    }

    @Override
    protected void paintComponent(Graphics _g) {
        _g.setColor(Color.BLACK);
        _g.drawString(facade.translateMove(name), 0, getHeight());
        _g.drawString(price.toNumberString(), 100, getHeight());
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        }
    }
}
