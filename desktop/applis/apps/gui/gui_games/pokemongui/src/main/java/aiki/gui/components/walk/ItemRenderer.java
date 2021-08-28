package aiki.gui.components.walk;

import java.awt.Dimension;

import aiki.facade.FacadeGame;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;

public class ItemRenderer extends CustCellRender<String> {

    private final int sideLength;

    private final FacadeGame facade;

    private boolean selected;

    private String name;

    private String displayName;

    private int price;

    private int maxWordWidth;

    private final AbstractImageFactory fact;
    private AbstractImage miniItem;

    public ItemRenderer(AbstractImageFactory _fact, FacadeGame _facade) {
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
        displayName = facade.translateItem(name);
        price = facade.getData().getItem(name).getPrice();
        int[][] img_ = facade.getData().getMiniItems().getVal(name);
        miniItem = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
        maxWordWidth = 0;
        for (String i: facade.getChosenItemsForBuyOrSell().getKeys()) {
            String disp_ = facade.translateItem(i);
            int w_ = _currentLab.stringWidth(disp_);
            if (w_ > maxWordWidth) {
                maxWordWidth = w_;
            }
        }
        _currentLab.setPreferredSize(new Dimension(maxWordWidth+sideLength *2,sideLength));
    }

    @Override
    protected AbstractImageFactory getImageFactory() {
        return fact;
    }
    @Override
    public void paintComponent(AbstractImage _g) {
        _g.drawImage(miniItem, 0, 0);
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(displayName, sideLength, getHeight());
        _g.drawString(facade.getChosenItemsForBuyOrSell().getVal(name).toNumberString(), maxWordWidth+sideLength, getHeight());
        _g.drawString(Long.toString(price), maxWordWidth+sideLength * 2, getHeight());
        if (selected) {
            _g.setColor(GuiConstants.RED);
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
