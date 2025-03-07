package aiki.gui.components.fight;


import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class TargetLabel {

    private static final String PER_CENT = " %";

    private boolean playerTeam;

    private AbstractImage image;

    private String fighterName;

    private long level;

    private int finalWidth;

    private int finalHeight;

    private boolean ko;

    private LgInt percentHp;

    private LgInt percentExp;

    private final PointBattle point = new PointBattle();

    private String ball = DataBase.EMPTY_STRING;

    private CustList<AbstractImage> statistics = new CustList<AbstractImage>();

    public static int getWidthStatistic(FrontBattle _parent, FacadeGame _facade) {
        long minValueStatis_ = _facade.getData().getMinBoost();
        long maxValueStatis_ = _facade.getData().getMaxBoost();
        int sideLength_ = _facade.getData().getMap().getSideLength();
        int maxWidthValue_ = IndexConstants.SIZE_EMPTY;
        for (long i = minValueStatis_; i < maxValueStatis_; i++) {
            String var_ = Long.toString(i);
            maxWidthValue_ = NumberUtil.max(maxWidthValue_, _parent.stringWidth(var_));
        }
        for (Statistic s: Statistic.getStatisticsWithBoost()) {
            int[][] type_ = _facade.getData().getAnimStatis().getVal(s.getStatName()).getImage();
            AbstractImage t_ = _parent.getBattle().getWindow().getTileRender().render(_parent.getBattle().getWindow().getImageFactory(), type_,sideLength_,sideLength_);
            maxWidthValue_ = NumberUtil.max(maxWidthValue_, t_.getWidth());
        }
        return maxWidthValue_;
    }

    public static int getHeightStatistic(FrontBattle _parent, FacadeGame _facade) {
        int maxWidthValue_ = _parent.heightFont();
        int sideLength_ = _facade.getData().getMap().getSideLength();
        int add_ = IndexConstants.SIZE_EMPTY;
        for (Statistic s: Statistic.getStatisticsWithBoost()) {
            int[][] type_ = _facade.getData().getAnimStatis().getVal(s.getStatName()).getImage();
            AbstractImage t_ = _parent.getBattle().getWindow().getTileRender().render(_parent.getBattle().getWindow().getImageFactory(), type_,sideLength_,sideLength_);
            add_ = t_.getHeight();
        }
        return maxWidthValue_ + add_;
    }

    public void apply(FrontBattle _parent, FacadeGame _facade) {
        int sideLength_ = _facade.getData().getMap().getSideLength();
        int width_ = _facade.getMaxWidthPk();
        String fighterTranslatedName_ = DataBase.EMPTY_STRING;
        int deltaWidth_ = 0;
        if (!fighterName.isEmpty()) {
            fighterTranslatedName_ = _facade.translatePokemon(fighterName);
            int imgWidth_ = 0;
            if (!ball.isEmpty()) {
                AbstractImage img_;
                int[][] b_ = _facade.getData().getMiniItem(ball);
                img_ = _parent.getBattle().getWindow().getTileRender().render(_parent.getBattle().getWindow().getImageFactory(), b_,sideLength_,sideLength_);
                imgWidth_ = img_.getWidth();
            }
            int w_ = _parent.stringWidth(fighterTranslatedName_);
            deltaWidth_ = NumberUtil.max(w_,deltaWidth_);
            width_ = NumberUtil.max(w_ + imgWidth_,width_);
            w_ = _parent.stringWidth(Long.toString(level));
            deltaWidth_ = NumberUtil.max(w_,deltaWidth_);
            width_ = NumberUtil.max(w_ + imgWidth_,width_);
            w_ = _parent.stringWidth(StringUtil.concat(percentExp.toNumberString(),PER_CENT));
            deltaWidth_ = NumberUtil.max(w_,deltaWidth_);
            width_ = NumberUtil.max(w_ + imgWidth_,width_);
            w_ = _parent.stringWidth(StringUtil.concat(percentHp.toNumberString(),PER_CENT));
            deltaWidth_ = NumberUtil.max(w_,deltaWidth_);
            width_ = NumberUtil.max(w_ + imgWidth_,width_);
            w_ = deltaWidth_ + getWidthStatistic(_parent, _facade);
            width_ = NumberUtil.max(w_ + imgWidth_,width_);
        }
        int height_ = _facade.getMaxHeightPk();
        int heightString_ = _parent.heightFont();
        int h_ = heightString_;
        //h_ += getHeightStatistic(_facade);
//        if (!ball.isEmpty()) {
//            BufferedImage img_;
//            String b_ = _facade.getData().getMiniItems().getVal(ball);
//            img_ = ConverterBufferedImage.decodeToImage(b_);
//            height_ += img_.getHeight();
//        }
        int heightIni_ = height_;
        int headerHeight_ = IndexConstants.SIZE_EMPTY;
        headerHeight_ += _parent.heightFont();
        headerHeight_ += _parent.heightFont();
        headerHeight_ += _parent.heightFont();
        headerHeight_ += _parent.heightFont();
        height_ += NumberUtil.max(headerHeight_, getHeightStatistic(_parent, _facade));
//        height_ += getFontMetrics(getFont()).getHeight();
//        height_ += getFontMetrics(getFont()).getHeight();
//        height_ += getFontMetrics(getFont()).getHeight();
//        height_ += getFontMetrics(getFont()).getHeight();
        //height_ += getHeightStatistic(_facade);
        int delta_ = height_ - heightIni_;
        if (!ball.isEmpty()) {
            AbstractImage img_;
            int[][] b_ = _facade.getData().getMiniItem(ball);
            img_ = _parent.getBattle().getWindow().getTileRender().render(_parent.getBattle().getWindow().getImageFactory(), b_,sideLength_,sideLength_);
//            if (delta_ < img_.getHeight()) {
//                delta_ = img_.getHeight();
//                height_ = heightIni_ + img_.getHeight();
//            }
            delta_ = NumberUtil.max(delta_,img_.getHeight());
            height_ = heightIni_ + delta_;
        }
        if (fighterName.isEmpty()) {
            width_ = NumberUtil.max(finalWidth,1);
            height_ = NumberUtil.max(finalHeight, 1);
//            if (width_ == IndexConstants.SIZE_EMPTY) {
//                width_ = IndexConstants.ONE_ELEMENT;
//            }
//            if (height_ == IndexConstants.SIZE_EMPTY) {
//                height_ = IndexConstants.ONE_ELEMENT;
//            }
        }
        image = _parent.getBattle().getWindow().getImageFactory().newImageArgb(width_, height_);
        image.setFont(_parent.getMetaFont());
        if (!fighterName.isEmpty()) {
            int hMax_ = 0;
            int widthStatis_ = 0;
            for (AbstractImage i: statistics) {
                hMax_ = NumberUtil.max(hMax_,i.getHeight());
                widthStatis_ += i.getWidth();
            }
            image.setColor(GuiConstants.WHITE);
            image.fillRect(deltaWidth_, 0, widthStatis_, hMax_);
            int x_ = 0;
            for (AbstractImage i: statistics) {
                image.drawImage(i, x_ + deltaWidth_, 0);
                x_ += i.getWidth();
            }
            AbstractImage image_ = img(_parent,_facade);
            image.setColor(GuiConstants.BLACK);
            image.drawString(fighterTranslatedName_, 0, h_);
            if (!ball.isEmpty()) {
                AbstractImage img_;
                int[][] b_ = _facade.getData().getMiniItem(ball);
                img_ = _parent.getBattle().getWindow().getTileRender().render(_parent.getBattle().getWindow().getImageFactory(), b_,sideLength_,sideLength_);
                image.drawImage(img_, width_ - img_.getWidth(), 0);
                //h_ += img_.getHeight();
            }
            h_ += heightString_;
            image.drawString(Long.toString(level), 0, h_);
            h_ += heightString_;
            image.setColor(GuiConstants.BLUE);
            image.drawString(percentExp.toNumberString(), 0, h_);
            h_ += heightString_;
            int rate_ = NumberUtil.parseInt(percentHp.toNumberString());
            int red_ = 255;
            int green_ = 255;
            green_ = green_ * rate_ / Rate.CENT;
            red_ = red_ * ((Rate.CENT - rate_) / Rate.CENT);
            image.setColor(GuiConstants.newColor(red_, green_, 0));
            image.drawString(StringUtil.concat(percentHp.toNumberString(),PER_CENT), 0, h_);
            image.drawImage(image_, 0, delta_);
            if (ko) {
                image.setColor(GuiConstants.RED);
                image.drawLine(0, h_, width_, height_);
                image.drawLine(0, height_, width_, h_);
            }
            image.dispose();
        }
        finalWidth = width_;
        finalHeight = height_;
//        if (!_name.isEmpty()) {
//            fighterTranslatedName = _facade.translatePokemon(fighterName);
//            if (playerTeam) {
//                image = ConverterBufferedImage.centerImage(data_.getMaxiPkBack().getVal(_name), width_, height_);
//            } else {
//                image = ConverterBufferedImage.centerImage(data_.getMaxiPkFront().getVal(_name), width_, height_);
//            }
//        } else {
//            fighterTranslatedName = DataBase.EMPTY_STRING;
//        }
        //setPreferredSize(new Dimension(width, height));
    }

    private AbstractImage img(FrontBattle _parent, FacadeGame _facade) {
        int widthImage_ = _facade.getMaxWidthPk();
        int heightImage_ = _facade.getMaxHeightPk();
        DataBase db_ = _facade.getData();
        AbstractImage image_;
        if (playerTeam) {
            image_ = _parent.getBattle().getWindow().getTileRender().centerImage(_parent.getBattle().getWindow().getImageFactory(), db_.getMaxiPkBack().getVal(fighterName).getImage(), widthImage_, heightImage_);
        } else {
            image_ = _parent.getBattle().getWindow().getTileRender().centerImage(_parent.getBattle().getWindow().getImageFactory(), db_.getMaxiPkFront().getVal(fighterName).getImage(), widthImage_, heightImage_);
        }
        return image_;
    }

    public void set(FrontBattle _parent, boolean _playerTeam,FacadeGame _facade, String _name) {
        playerTeam = _playerTeam;
        fighterName = _name;
        apply(_parent, _facade);
//        DataBase data_ = _facade.getData();
//        int widthImage_ = _facade.getMaxWidthPk();
//        int width_ = _facade.getMaxWidthPk();
//        fighterTranslatedName = DataBase.EMPTY_STRING;
//        if (!fighterName.isEmpty()) {
//            fighterTranslatedName = _facade.translatePokemon(fighterName);
//            int w_ = getFontMetrics(getFont()).stringWidth(fighterTranslatedName);
//            if (w_ > width_) {
//                width_ = w_;
//            }
//            w_ = getFontMetrics(getFont()).stringWidth(Short.toString(level));
//            if (w_ > width_) {
//                width_ = w_;
//            }
//            w_ = getFontMetrics(getFont()).stringWidth(percentHp+PER_CENT);
//            if (w_ > width_) {
//                width_ = w_;
//            }
//        }
//        int height_ = _facade.getMaxHeightPk();
//        int heightImage_ = _facade.getMaxHeightPk();
//        int heightString_ = getFontMetrics(getFont()).getHeight();
//        int h_ = heightString_;
//        height_ += getFontMetrics(getFont()).getHeight();
//        height_ += getFontMetrics(getFont()).getHeight();
//        height_ += getFontMetrics(getFont()).getHeight();
//        image = new BufferedImage(width_, height_, BufferedImage.TYPE_INT_ARGB);
//        if (!fighterName.isEmpty()) {
//            Graphics2D g_ = image.createGraphics();
//            BufferedImage image_;
//            if (playerTeam) {
//                image_ = ConverterBufferedImage.centerImage(data_.getMaxiPkBack().getVal(fighterName), widthImage_, heightImage_);
//            } else {
//                image_ = ConverterBufferedImage.centerImage(data_.getMaxiPkFront().getVal(fighterName), widthImage_, heightImage_);
//            }
//            g_.setColor(Color.BLACK);
//            g_.drawString(fighterTranslatedName, 0, h_);
//            h_ += heightString_;
//            g_.drawString(Short.toString(level), 0, h_);
//            h_ += heightString_;
//            int rate_ = Integer.parseInt(percentHp.toString());
//            int red_ = 255;
//            int green_ = 255;
//            green_ = green_ * rate_ / Fighter.RATE_CENT;
//            red_ = red_ * ((Fighter.RATE_CENT - rate_) / Fighter.RATE_CENT);
//            g_.setColor(GuiConstants.newColor(red_, green_, 0));
//            g_.drawString(percentHp+PER_CENT, 0, h_);
//            g_.drawImage(image_, 0, h_, null);
//            if (ko) {
//                g_.setColor(Color.RED);
//                g_.drawLine(0, h_, width_, height_);
//                g_.drawLine(0, height_, width_, h_);
//            }
//            g_.dispose();
//        }
//        finalWidth = width_;
//        finalHeight = height_;
//        if (!_name.isEmpty()) {
//            fighterTranslatedName = _facade.translatePokemon(fighterName);
//            if (playerTeam) {
//                image = ConverterBufferedImage.centerImage(data_.getMaxiPkBack().getVal(_name), width_, height_);
//            } else {
//                image = ConverterBufferedImage.centerImage(data_.getMaxiPkFront().getVal(_name), width_, height_);
//            }
//        } else {
//            fighterTranslatedName = DataBase.EMPTY_STRING;
//        }
        //setPreferredSize(new Dimension(width, height));
    }

    public AbstractImage getImage() {
        return image;
    }

    public String getFighterName() {
        return fighterName;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long _level) {
        level = _level;
    }

    public int getFinalWidth() {
        return finalWidth;
    }

    public int getFinalHeight() {
        return finalHeight;
    }

    public void setKo(boolean _ko) {
        ko = _ko;
    }

    public LgInt getPercentHp() {
        return percentHp;
    }

    public void setPercentHp(LgInt _percentHp) {
        percentHp = _percentHp;
    }

    public LgInt getPercentExp() {
        return percentExp;
    }

    public void setPercentExp(LgInt _percentExp) {
        percentExp = _percentExp;
    }

    public PointBattle getPoint() {
        return point;
    }

    public void setBall(String _ball) {
        ball = _ball;
    }

    public void setStatistics(CustList<AbstractImage> _statistics) {
        statistics = _statistics;
    }
}
