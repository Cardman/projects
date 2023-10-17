package aiki.gui.components.walk;


import aiki.facade.FacadeGame;
import code.gui.AbsCustCellRenderGene;
import code.gui.ColorsGroupList;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;

public class ItemRenderer implements AbsCustCellRenderGene<String> {

    private final int sideLength;

    private final FacadeGame facade;

    private boolean selected;

    private String name;

    private String displayName;

    private int price;

    private int maxWordWidth;

    private final AbstractImageFactory fact;
    private final AbsCompoFactory compo;
    private AbstractImage miniItem;

    public ItemRenderer(AbstractImageFactory _fact, AbsCompoFactory _compoFactory, FacadeGame _facade) {
        fact = _fact;
        compo = _compoFactory;
        facade = _facade;
        sideLength = facade.getMap().getSideLength();
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, String _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        selected = _isSelected;
        name = _info;
        displayName = facade.translateItem(name);
        price = facade.getData().getItem(name).getPrice();
        int[][] img_ = facade.getData().getMiniItems().getVal(name);
        miniItem = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
        maxWordWidth = 0;
        for (String i: facade.getChosenItemsForBuyOrSell().getKeys()) {
            String disp_ = facade.translateItem(i);
            int w_ = compo.stringWidth(_lab,disp_);
            if (w_ > maxWordWidth) {
                maxWordWidth = w_;
            }
        }
        AbstractImage i_ = fact.newImageRgb(maxWordWidth+sideLength *2,sideLength);
        paintComponent(i_);
        return i_;
    }

    public AbstractImageFactory getImageFactory() {
        return fact;
    }

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

    public int getHeight() {
        return sideLength;
    }

    public int getWidth() {
        return maxWordWidth+sideLength *2;
    }
}
