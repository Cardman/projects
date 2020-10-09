package aiki.gui.components.labels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.components.Paginator;
import aiki.util.SortingHealingItem;
import code.gui.CustGraphics;
import code.gui.images.ConverterGraphicBufferedImage;
import code.sml.stream.ExtractFromFiles;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public class HealingItemLabel extends SelectableLabel {

    private static final String HEALING_ITEM_LABEL = "aiki.gui.components.labels.healingitemlabel";

    private static final String SEPARATOR = Paginator.SPACE;

    private static final String SPACES = StringUtil.concat(Paginator.SPACE,Paginator.SPACE);

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

    private SortingHealingItem item;

    private BufferedImage miniImageItem;

    /**max pixels name*/
    private int fourthColumn;

    /**max pixel number*/
    private int fifthColumn;

    public HealingItemLabel(SortingHealingItem _item) {
        item = _item;
    }

    public void initMessages(String _lg) {
        messages = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, HEALING_ITEM_LABEL);
    }

    public void setImagesResults(FacadeGame _facade, int _thirdColumn, int _fourthColumn, int _fifthColumn) {
        fourthColumn = _fourthColumn;
        fifthColumn = _fifthColumn;
        int[][] miniItem_ = _facade.getData().getMiniItems().getVal(item.getKeyName());
        miniImageItem = ConverterGraphicBufferedImage.decodeToImage(miniItem_);
        sideLength = _facade.getMap().getSideLength();
        int h_ = sideLength;
//        if (h_ < FOURTH_LINE) {
//            h_ = FOURTH_LINE;
//        }
        Ints widths_ = new Ints();
        widths_.add(_thirdColumn);
        widths_.add(getFontMetrics(getFont()).stringWidth(item.getItemClass()));
        widths_.add(getThirdLineWidth());
        widths_.add(getFontMetrics(getFont()).stringWidth(item.getNumber().toNumberString()));
        setPreferredSize(new Dimension((int) widths_.getMaximum(1),h_));
    }

    public int getThirdColumnWidth() {
        return getFontMetrics(getFont()).stringWidth(StringUtil.concat(item.getName(),SPACES));
    }

    public int getFourthColumnWidth() {
        return getFontMetrics(getFont()).stringWidth(StringUtil.concat(item.getNumber().toNumberString(),SPACES));
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.drawImage(miniImageItem, 0, 0);
        _g.setColor(Color.BLACK);
        _g.drawString(item.getName(), sideLength, FIRST_LINE);
        _g.drawString(item.getItemClass(), sideLength, SECOND_LINE);
        _g.drawString(getThirdLineInfos(), sideLength, THIRD_LINE);
        _g.drawString(item.getNumber().toNumberString(), sideLength + fourthColumn, FIRST_LINE);
        _g.drawString(Integer.toString(item.getPrice()), sideLength + fourthColumn + fifthColumn, FIRST_LINE);
        super.paintComponent(_g);
    }

    private int getThirdLineWidth() {
        return getFontMetrics(getFont()).stringWidth(getThirdLineInfos());
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
