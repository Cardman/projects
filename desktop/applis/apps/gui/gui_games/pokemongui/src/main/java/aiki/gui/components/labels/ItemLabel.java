package aiki.gui.components.labels;


import aiki.facade.FacadeGame;
import aiki.gui.components.walk.IntTileRender;
import aiki.util.SortingItem;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.Ints;
import code.util.core.NumberUtil;

public final class ItemLabel extends SelectableLabel {


    private static final int FIRST_LINE = HEIGTH_CHARS;

    private static final int SECOND_LINE = FIRST_LINE + HEIGTH_CHARS;

    private static final int THIRD_LINE = SECOND_LINE + HEIGTH_CHARS;

    private static final int FOURTH_LINE = THIRD_LINE + HEIGTH_CHARS;

    private int sideLength;

    private final SortingItem item;

    private AbstractImage miniImageItem;

    public ItemLabel(SortingItem _item, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        item = _item;
    }

    public void setImagesResults(AbstractImageFactory _fact, FacadeGame _facade, IntTileRender _rend) {
        int[][] miniItem_ = _facade.getData().getMiniItem(item.getKeyName());
        sideLength = _facade.getMap().getSideLength();
        miniImageItem = _rend.render(_fact,miniItem_,sideLength,sideLength);
        int h_ = NumberUtil.max(sideLength, FOURTH_LINE);
//        if (h_ < FOURTH_LINE) {
//            h_ = FOURTH_LINE;
//        }
        Ints widths_ = new Ints();
        widths_.add(stringWidth(item.getName()));
        widths_.add(stringWidth(item.getItemClass()));
        widths_.add(stringWidth(Long.toString(item.getPrice())));
        widths_.add(stringWidth(item.getNumber().toNumberString()));
        setPreferredSize(new MetaDimension(sideLength+(int) widths_.getMaximum(1),h_));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.drawImage(miniImageItem, 0, 0);
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(item.getName(), sideLength, FIRST_LINE);
        _g.drawString(item.getItemClass(), sideLength, SECOND_LINE);
        _g.drawString(Long.toString(item.getPrice()), sideLength, THIRD_LINE);
        _g.drawString(item.getNumber().toNumberString(), sideLength, FOURTH_LINE);
        super.paintComponent(_g);
    }
}
