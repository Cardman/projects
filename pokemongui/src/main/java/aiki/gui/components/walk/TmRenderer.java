package aiki.gui.components.walk;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

import aiki.facade.FacadeGame;
import code.gui.CustCellRender;
import code.gui.GraphicListable;
import code.maths.LgInt;

public class TmRenderer extends CustCellRender {

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
    public JLabel getListCellRendererComponent(
            GraphicListable _list, Object _item, int _arg2,
            boolean _selected, boolean _arg4) {
        JLabel label_ = (JLabel) _list.getListComponents().get(_arg2);
        selected = _selected;
        name = (String) _item;
//        short tm_ = facade.getData().getTm().getKeys(name).first();
        short tm_ = facade.getData().getTmByMove(name).first();
        price = facade.getData().getTmPrice().getVal(tm_);
        label_.setPreferredSize(new Dimension(150,sideLength));
        return label_;
    }

    @Override
    public void paintComponent(Graphics _g) {
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
