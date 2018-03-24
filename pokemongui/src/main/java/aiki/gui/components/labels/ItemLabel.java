package aiki.gui.components.labels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import aiki.facade.FacadeGame;
import aiki.util.SortingItem;
import code.images.ConverterBufferedImage;
import code.util.Numbers;

public class ItemLabel extends SelectableLabel {


    private static final int FIRST_LINE = HEIGTH_CHARS;

    private static final int SECOND_LINE = FIRST_LINE + HEIGTH_CHARS;

    private static final int THIRD_LINE = SECOND_LINE + HEIGTH_CHARS;

    private static final int FOURTH_LINE = THIRD_LINE + HEIGTH_CHARS;

    private int sideLength;

    private SortingItem item;

    private BufferedImage miniImageItem;

    public ItemLabel(SortingItem _item) {
        item = _item;
    }

    public void setImagesResults(FacadeGame _facade) {
        int[][] miniItem_ = _facade.getData().getMiniItems().getVal(item.getKeyName());
        miniImageItem = ConverterBufferedImage.decodeToImage(miniItem_);
        sideLength = _facade.getMap().getSideLength();
        int h_ = sideLength;
        if (h_ < FOURTH_LINE) {
            h_ = FOURTH_LINE;
        }
        Numbers<Integer> widths_ = new Numbers<Integer>();
        widths_.add(getFontMetrics(getFont()).stringWidth(item.getName()));
        widths_.add(getFontMetrics(getFont()).stringWidth(item.getItemClass()));
        widths_.add(getFontMetrics(getFont()).stringWidth(Integer.toString(item.getPrice())));
        widths_.add(getFontMetrics(getFont()).stringWidth(item.getNumber().toNumberString()));
        setPreferredSize(new Dimension(widths_.getMaximum(),h_));
    }

    @Override
    public void paintComponent(Graphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.drawImage(miniImageItem, 0, 0, null);
        _g.setColor(Color.BLACK);
        _g.drawString(item.getName(), sideLength, FIRST_LINE);
        _g.drawString(item.getItemClass(), sideLength, SECOND_LINE);
        _g.drawString(Integer.toString(item.getPrice()), sideLength, THIRD_LINE);
        _g.drawString(item.getNumber().toNumberString(), sideLength, FOURTH_LINE);
        super.paintComponent(_g);
    }
}
