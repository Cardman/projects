package aiki.gui.components.labels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import aiki.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.components.Paginator;
import aiki.util.SortingHealingItem;
import code.images.ConverterBufferedImage;
import code.sml.util.ExtractFromFiles;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class HealingItemLabel extends SelectableLabel {

    private static final String HEALING_ITEM_LABEL = "aiki.gui.components.labels.HealingItemLabel";

    private static final String SEPARATOR = Paginator.SPACE;

    private static final String SPACES = Paginator.SPACE + Paginator.SPACE;

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

    private static StringMap<String> _messages_ = new StringMap<String>();

    private static int _sideLength_;

    private SortingHealingItem item;

    private BufferedImage miniImageItem;

    /**max pixels name*/
    private int fourthColumn;

    /**max pixel number*/
    private int fifthColumn;

    public HealingItemLabel(SortingHealingItem _item) {
        item = _item;
    }

    public static void initMessages() {
        _messages_ = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), HEALING_ITEM_LABEL);
    }

    public void setImagesResults(FacadeGame _facade, int _thirdColumn, int _fourthColumn, int _fifthColumn) {
        fourthColumn = _fourthColumn;
        fifthColumn = _fifthColumn;
        String miniItem_ = _facade.getData().getMiniItems().getVal(item.getKeyName());
        miniImageItem = ConverterBufferedImage.decodeToImage(miniItem_);
        _sideLength_ = _facade.getMap().getSideLength();
        int h_ = _sideLength_;
//        if (h_ < FOURTH_LINE) {
//            h_ = FOURTH_LINE;
//        }
        Numbers<Integer> widths_ = new Numbers<Integer>();
        widths_.add(_thirdColumn);
        widths_.add(getFontMetrics(getFont()).stringWidth(item.getItemClass()));
        widths_.add(getThirdLineWidth());
        widths_.add(getFontMetrics(getFont()).stringWidth(item.getNumber().toString()));
        setPreferredSize(new Dimension(widths_.getMaximum(),h_));
    }

    public int getThirdColumnWidth() {
        return getFontMetrics(getFont()).stringWidth(item.getName()+SPACES);
    }

    public int getFourthColumnWidth() {
        return getFontMetrics(getFont()).stringWidth(item.getNumber().toString()+SPACES);
    }

//    public int getFifthColumnWidth() {
//        return getFontMetrics(getFont()).stringWidth(item.getNumber().toString());
//    }

    @Override
    public void paintComponent(Graphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.drawImage(miniImageItem, 0, 0, null);
        _g.setColor(Color.BLACK);
        _g.drawString(item.getName(), _sideLength_, FIRST_LINE);
        _g.drawString(item.getItemClass(), _sideLength_, SECOND_LINE);
        _g.drawString(getThirdLineInfos(), _sideLength_, THIRD_LINE);
        _g.drawString(item.getNumber().toString(), _sideLength_ + fourthColumn, FIRST_LINE);
        _g.drawString(Integer.toString(item.getPrice()), _sideLength_ + fourthColumn + fifthColumn, FIRST_LINE);
        super.paintComponent(_g);
    }

    private int getThirdLineWidth() {
        return getFontMetrics(getFont()).stringWidth(getThirdLineInfos());
    }

    private String getThirdLineInfos() {
        StringList infos_ = new StringList();
        if (!item.getHp().isZero()) {
            infos_.add(_messages_.getVal(HP)+item.getHp());
        }
        if (!item.getHpRate().isZero()) {
            infos_.add(_messages_.getVal(HP_RATE)+item.getHpRate());
        }
        /*if (item.getHp() != null) {
            if (item.isRelativeRateHp()) {
                infos_.add(_messages_.getVal(HP_RATE)+item.getHp());
            } else {
                infos_.add(_messages_.getVal(HP)+item.getHp());
            }
        }*/
        if (!item.getPp().isZero()) {
            if (item.isHealOneMove()) {
                infos_.add(_messages_.getVal(HEAL_ONE_MOVE));
            } else {
                infos_.add(_messages_.getVal(HEAL_MOVES));
            }
            infos_.add(_messages_.getVal(PP)+item.getPp());
        }
        /*if (item.getPp() != null) {
            if (item.isHealOneMove()) {
                infos_.add(_messages_.getVal(HEAL_ONE_MOVE));
            } else {
                infos_.add(_messages_.getVal(HEAL_MOVES));
            }
            infos_.add(_messages_.getVal(PP)+item.getPp());
        }*/
        if (!item.getStatus().isEmpty()) {
            infos_.add(_messages_.getVal(STATUS)+item.getStatus().join(SEPARATOR));
        }
        if (!item.getStatistics().isEmpty()) {
            infos_.add(_messages_.getVal(STATISTICS)+item.getStatistics().join(SEPARATOR));
        }
        if (item.isKo()) {
            infos_.add(_messages_.getVal(KO));
        }
        return infos_.join(SEPARATOR);
    }
}
