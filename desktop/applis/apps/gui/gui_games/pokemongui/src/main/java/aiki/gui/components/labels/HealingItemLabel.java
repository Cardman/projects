package aiki.gui.components.labels;

import java.awt.Dimension;

import aiki.gui.WindowAiki;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.components.Paginator;
import aiki.util.SortingHealingItem;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class HealingItemLabel extends SelectableLabel {

    private static final String HEALING_ITEM_LABEL = "aiki.gui.components.labels.healingitemlabel";

    private static final String SEPARATOR = Paginator.SPACE;

    private static final String SPACES = Paginator.SPACE+Paginator.SPACE;

    private static final String HEAL_ONE_MOVE = "healOneMove";

    private static final String HEAL_MOVES = "healMoves";

    private static final String HP = "hp";

    private static final String HP_RATE = "hpRate";

    private static final String PP = "pp";

    private static final String STATUS = "status";

    private static final String STATISTICS = "statistics";

    private static final String KO = "ko";

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

    public void initMessages(String _lg) {
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, HEALING_ITEM_LABEL);
    }

    public void setImagesResults(AbstractImageFactory _fact, FacadeGame _facade, int _thirdColumn, int _fourthColumn, int _fifthColumn) {
        fourthColumn = _fourthColumn;
        fifthColumn = _fifthColumn;
        int[][] miniItem_ = _facade.getData().getMiniItems().getVal(item.getKeyName());
        miniImageItem = ConverterGraphicBufferedImage.decodeToImage(_fact,miniItem_);
        sideLength = _facade.getMap().getSideLength();
        int h_ = sideLength;
//        if (h_ < FOURTH_LINE) {
//            h_ = FOURTH_LINE;
//        }
        Ints widths_ = new Ints();
        widths_.add(_thirdColumn);
        widths_.add(stringWidth(item.getItemClass()));
        widths_.add(getThirdLineWidth());
        widths_.add(stringWidth(item.getNumber().toNumberString()));
        setPreferredSize(new MetaDimension((int) widths_.getMaximum(1),h_));
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
            infos_.add(StringUtil.concat(messages.getVal(HP),item.getHp().toNumberString()));
        }
        if (!item.getHpRate().isZero()) {
            infos_.add(StringUtil.concat(messages.getVal(HP_RATE),item.getHpRate().toNumberString()));
        }
        if (!item.getPp().isZero()) {
            if (item.isHealOneMove()) {
                infos_.add(messages.getVal(HEAL_ONE_MOVE));
            } else {
                infos_.add(messages.getVal(HEAL_MOVES));
            }
            infos_.add(StringUtil.concat(messages.getVal(PP),item.getPp().toNumberString()));
        }
        if (!item.getStatus().isEmpty()) {
            infos_.add(StringUtil.concat(messages.getVal(STATUS), StringUtil.join(item.getStatus(), SEPARATOR)));
        }
        if (!item.getStatistics().isEmpty()) {
            infos_.add(StringUtil.concat(messages.getVal(STATISTICS), StringUtil.join(item.getStatistics(), SEPARATOR)));
        }
        if (item.isKo()) {
            infos_.add(messages.getVal(KO));
        }
        return StringUtil.join(infos_, SEPARATOR);
    }
}
