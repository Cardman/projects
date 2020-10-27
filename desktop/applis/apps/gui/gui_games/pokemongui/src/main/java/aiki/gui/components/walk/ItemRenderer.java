package aiki.gui.components.walk;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import aiki.facade.FacadeGame;
import code.gui.*;
import code.gui.images.ConverterGraphicBufferedImage;

public class ItemRenderer extends CustCellRender {

    private int sideLength;

    private FacadeGame facade;

    private boolean selected;

    private String name;

    private String displayName;

    private int price;

    private int maxWordWidth;

    private BufferedImage miniItem;

    public ItemRenderer(FacadeGame _facade) {
        facade = _facade;
        sideLength = facade.getMap().getSideLength();
    }

    @Override
    public PreparedLabel getListCellRendererComponent(
            GraphicListable _list, Object _item, int _arg2,
            boolean _selected, boolean _arg4) {
        PreparedLabel label_ = _list.getListComponents().get(_arg2);
        selected = _selected;
        name = (String) _item;
        displayName = facade.translateItem(name);
        price = facade.getData().getItem(name).getPrice();
        int[][] img_ = facade.getData().getMiniItems().getVal(name);
        miniItem = ConverterGraphicBufferedImage.decodeToImage(img_);
        maxWordWidth = 0;
        for (String i: facade.getChosenItemsForBuyOrSell().getKeys()) {
            String disp_ = facade.translateItem(i);
            int w_ = label_.getFontMetrics(label_.getFont()).stringWidth(disp_);
            if (w_ > maxWordWidth) {
                maxWordWidth = w_;
            }
        }
        label_.setPreferredSize(new Dimension(maxWordWidth+sideLength *2,sideLength));
        return label_;
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.drawImage(miniItem, 0, 0);
        _g.setColor(Color.BLACK);
        _g.drawString(displayName, sideLength, getHeight());
        _g.drawString(facade.getChosenItemsForBuyOrSell().getVal(name).toNumberString(), maxWordWidth+sideLength, getHeight());
        _g.drawString(Long.toString(price), maxWordWidth+sideLength * 2, getHeight());
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
        return maxWordWidth+sideLength *2;
    }
}
