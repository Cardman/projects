package aiki.gui.components.labels;


import aiki.facade.FacadeGame;
import aiki.gui.components.Paginator;
import aiki.gui.components.walk.IntTileRender;
import aiki.sml.MessagesRenderPaginatorHealingItem;
import aiki.util.SortingHealingItem;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class HealingItemLabel extends SelectableLabel {

//    private static final String HEALING_ITEM_LABEL = "aiki.gui.components.labels.healingitemlabel";

    private static final String SEPARATOR = Paginator.SPACE;

    private static final String SPACES = Paginator.SPACE+Paginator.SPACE;

//    private static final String HEAL_ONE_MOVE = "healOneMove";
//
//    private static final String HEAL_MOVES = "healMoves";
//
//    private static final String HP = "hp";
//
//    private static final String HP_RATE = "hpRate";
//
//    private static final String PP = "pp";
//
//    private static final String STATUS = "status";
//
//    private static final String STATISTICS = "statistics";
//
//    private static final String KO = "ko";

    private static final int FIRST_LINE = HEIGTH_CHARS;

    private static final int SECOND_LINE = FIRST_LINE + HEIGTH_CHARS;

    private static final int THIRD_LINE = SECOND_LINE + HEIGTH_CHARS;

    //private static final int FOURTH_LINE = THIRD_LINE + Paginator.HEIGTH_CHARS;

    private StringMap<String> messages = new StringMap<String>();

    private int sideLength;

    private final SortingHealingItem item;

    private AbstractImage miniImageItem;

    /**max pixels name*/
    private int fourthColumn;

    /**max pixel number*/
    private int fifthColumn;

    public HealingItemLabel(SortingHealingItem _item, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        item = _item;
    }

    public void initMessages(StringMap<String> _messages) {
        messages = _messages;
//        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, HEALING_ITEM_LABEL);
    }

    public void setImagesResults(AbstractImageFactory _fact, FacadeGame _facade, int _thirdColumn, int _fourthColumn, int _fifthColumn, IntTileRender _rend) {
        fourthColumn = _fourthColumn;
        fifthColumn = _fifthColumn;
        sideLength = _facade.getMap().getSideLength();
        int[][] miniItem_ = _facade.getData().getMiniItems().getVal(item.getKeyName()).getImage();
        miniImageItem = _rend.render(_fact,miniItem_,sideLength,sideLength);
        int h_ = sideLength;
//        if (h_ < FOURTH_LINE) {
//            h_ = FOURTH_LINE;
//        }
        Ints widths_ = new Ints();
        widths_.add(_thirdColumn);
        widths_.add(stringWidth(item.getItemClass()));
        widths_.add(getThirdLineWidth());
        widths_.add(stringWidth(item.getNumber().toNumberString()));
        setPreferredSize(new MetaDimension(sideLength+(int) widths_.getMaximum(1),h_));
    }

    public int getThirdColumnWidth() {
        return stringWidth(StringUtil.concat(item.getName(),SPACES));
    }

    public int getFourthColumnWidth() {
        return stringWidth(StringUtil.concat(item.getNumber().toNumberString(),SPACES));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.drawImage(miniImageItem, 0, 0);
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(item.getName(), sideLength, FIRST_LINE);
        _g.drawString(item.getItemClass(), sideLength, SECOND_LINE);
        _g.drawString(getThirdLineInfos(), sideLength, THIRD_LINE);
        _g.drawString(item.getNumber().toNumberString(), sideLength + fourthColumn, FIRST_LINE);
        _g.drawString(Long.toString(item.getPrice()), sideLength + fourthColumn + fifthColumn, FIRST_LINE);
        super.paintComponent(_g);
    }

    private int getThirdLineWidth() {
        return stringWidth(getThirdLineInfos());
    }

    private String getThirdLineInfos() {
        StringList infos_ = new StringList();
        if (!item.getHp().isZero()) {
            infos_.add(StringUtil.concat(messages.getVal(MessagesRenderPaginatorHealingItem.LAB_HP),item.getHp().toNumberString()));
        }
        if (!item.getHpRate().isZero()) {
            infos_.add(StringUtil.concat(messages.getVal(MessagesRenderPaginatorHealingItem.LAB_HP_RATE),item.getHpRate().toNumberString()));
        }
        if (!item.getPp().isZero()) {
            if (item.isHealOneMove()) {
                infos_.add(messages.getVal(MessagesRenderPaginatorHealingItem.LAB_HEAL_ONE_MOVE));
            } else {
                infos_.add(messages.getVal(MessagesRenderPaginatorHealingItem.LAB_HEAL_MOVES));
            }
            infos_.add(StringUtil.concat(messages.getVal(MessagesRenderPaginatorHealingItem.LAB_PP),item.getPp().toNumberString()));
        }
        if (!item.getStatus().isEmpty()) {
            infos_.add(StringUtil.concat(messages.getVal(MessagesRenderPaginatorHealingItem.LAB_STATUS), StringUtil.join(item.getStatus(), SEPARATOR)));
        }
        if (!item.getStatistics().isEmpty()) {
            infos_.add(StringUtil.concat(messages.getVal(MessagesRenderPaginatorHealingItem.LAB_STATISTICS), StringUtil.join(item.getStatistics(), SEPARATOR)));
        }
        if (item.isKo()) {
            infos_.add(messages.getVal(MessagesRenderPaginatorHealingItem.LAB_KO));
        }
        return StringUtil.join(infos_, SEPARATOR);
    }
}
