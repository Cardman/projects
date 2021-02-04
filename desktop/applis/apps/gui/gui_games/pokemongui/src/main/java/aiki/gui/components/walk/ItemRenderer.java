package aiki.gui.components.walk;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import aiki.facade.FacadeGame;
import code.gui.*;
import code.gui.images.ConverterGraphicBufferedImage;

public class ItemRenderer extends CustCellRender<String> {

    private final int sideLength;

    private final FacadeGame facade;

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
    public void getListCellRendererComponent(
            PreparedLabel _currentLab, int _arg2,
            boolean _selected, boolean _arg4) {
        selected = _selected;
        name = getList().get(_arg2);
        displayName = facade.translateItem(name);
        price = facade.getData().getItem(name).getPrice();
        int[][] img_ = facade.getData().getMiniItems().getVal(name);
        miniItem = ConverterGraphicBufferedImage.decodeToImage(img_);
        maxWordWidth = 0;
        for (String i: facade.getChosenItemsForBuyOrSell().getKeys()) {
            String disp_ = facade.translateItem(i);
            int w_ = _currentLab.getFontMetrics(_currentLab.getFont()).stringWidth(disp_);
            if (w_ > maxWordWidth) {
                maxWordWidth = w_;
            }
        }
        _currentLab.setPreferredSize(new Dimension(maxWordWidth+sideLength *2,sideLength));
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
