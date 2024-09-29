package aiki.gui.components.walk;


import aiki.facade.FacadeGame;
import code.gui.AbsCustCellRenderGene;
import code.gui.ColorsGroupList;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.util.core.NumberUtil;

public class ItemRenderer implements AbsCustCellRenderGene<String> {

    private int sideLength;

    private final FacadeGame facade;

    private boolean selected;

    private String name;

    private String displayName;

    private int price;

//    private int maxWordWidth;
//    private int maxNbWidth;

    private final AbstractImageFactory fact;
    private final AbsCompoFactory compo;
    private AbstractImage miniItem;
    private int maxPriceLen;
    private final IntTileRender render;

    public ItemRenderer(AbstractImageFactory _fact, AbsCompoFactory _compoFactory, FacadeGame _facade, IntTileRender _tileRender) {
        fact = _fact;
        compo = _compoFactory;
        facade = _facade;
        render = _tileRender;
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, String _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        sideLength = facade.getMap().getSideLength();
        selected = _isSelected;
        name = _info;
        displayName = facade.translateItem(name);
        price = facade.getData().getItem(name).getPrice();
        int[][] img_ = facade.getData().getMiniItems().getVal(name).getImage();
        miniItem = render.render(fact,img_,sideLength,sideLength);
        maxPriceLen = DefTileRender.widthLgMax(compo,_lab);
        int h_ = NumberUtil.max(sideLength, _lab.getRealSize() + 2);
        int widthName_ = ItemsPanel.widthName(facade, compo, _lab);
        int widthFreq_ = ItemsPanel.widthFreq(facade, compo, _lab);
        AbstractImage i_ = fact.newImageRgb(getWidth(widthName_, widthFreq_),h_);
        i_.setFont(_lab);
        paintComponent(i_, widthName_, widthFreq_);
        return i_;
    }

    public void paintComponent(AbstractImage _g, int _maxWordWidth, int _maxNbWidth) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, getWidth(_maxWordWidth, _maxNbWidth) - 1, getHeight() - 1);
        _g.drawImage(miniItem, 0, 0);
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(displayName, sideLength, getHeight() - 2);
        _g.drawString(facade.getChosenItemsForBuyOrSell().getVal(name).toNumberString(), sideLength + _maxWordWidth, getHeight() - 2);
        _g.drawString(Long.toString(price), sideLength + _maxWordWidth + _maxNbWidth, getHeight() - 2);
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0,0,getWidth(_maxWordWidth, _maxNbWidth)-1,getHeight()-1);
        }
    }

//    public void setMaxWordWidth(int _m) {
//        this.maxWordWidth = _m;
//    }

//    public void setMaxNbWidth(int _m) {
//        this.maxNbWidth = _m;
//    }

    public int getHeight() {
        return sideLength;
    }

    public int getWidth(int _maxWordWidth, int _maxNbWidth) {
        return sideLength+ _maxWordWidth + _maxNbWidth +maxPriceLen;
    }
}
