package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import aiki.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import code.images.ConverterBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringList;

public class TargetLabel {

    private static final String PER_CENT = " %";

    private boolean playerTeam;

    private BufferedImage image;

    private String fighterName;

    private String fighterTranslatedName;

    private short level;

    private int finalWidth;

    private int finalHeight;

    private boolean ko;

    private LgInt percentHp;

    private LgInt percentExp;

    private int xPoint;

    private int yPoint;

    private String ball = DataBase.EMPTY_STRING;

    private CustList<BufferedImage> statistics = new CustList<BufferedImage>();

    public static int getWidthStatistic(JLabel _parent, FacadeGame _facade) {
        FontMetrics fMet_ = _parent.getFontMetrics(_parent.getFont());
        int minValueStatis_ = _facade.getData().getMinBoost();
        int maxValueStatis_ = _facade.getData().getMaxBoost();
        int maxWidthValue_ = CustList.SIZE_EMPTY;
        for (int i = minValueStatis_; i < maxValueStatis_; i++) {
            String var_ = Integer.toString(i);
            int widthVar_ = fMet_.stringWidth(var_);
            if (widthVar_ > maxWidthValue_) {
                maxWidthValue_ = widthVar_;
            }
        }
        for (Statistic s: Statistic.getStatisticsWithBoost()) {
            int[][] type_ = _facade.getData().getAnimStatis().getVal(s.name());
            BufferedImage t_ = ConverterBufferedImage.decodeToImage(type_);
            if (t_.getWidth() > maxWidthValue_) {
                maxWidthValue_ = t_.getWidth();
            }
        }
        return maxWidthValue_;
    }

    public static int getHeightStatistic(JLabel _parent, FacadeGame _facade) {
        FontMetrics fMet_ = _parent.getFontMetrics(_parent.getFont());
        int maxWidthValue_ = fMet_.getHeight();
        int add_ = CustList.SIZE_EMPTY;
        for (Statistic s: Statistic.getStatisticsWithBoost()) {
            int[][] type_ = _facade.getData().getAnimStatis().getVal(s.name());
            BufferedImage t_ = ConverterBufferedImage.decodeToImage(type_);
            add_ = t_.getHeight();
        }
        return maxWidthValue_ + add_;
    }

    public void apply(JLabel _parent, FacadeGame _facade) {
        DataBase data_ = _facade.getData();
        int widthImage_ = _facade.getMaxWidthPk();
        int width_ = _facade.getMaxWidthPk();
        fighterTranslatedName = DataBase.EMPTY_STRING;
        int deltaWidth_ = 0;
        if (!fighterName.isEmpty()) {
            fighterTranslatedName = _facade.translatePokemon(fighterName);
            int imgWidth_ = 0;
            if (!ball.isEmpty()) {
                BufferedImage img_;
                int[][] b_ = _facade.getData().getMiniItems().getVal(ball);
                img_ = ConverterBufferedImage.decodeToImage(b_);
                imgWidth_ = img_.getWidth();
            }
            int w_ = _parent.getFontMetrics(_parent.getFont()).stringWidth(fighterTranslatedName);
            if (w_ > deltaWidth_) {
                deltaWidth_ = w_;
            }
            if (w_ + imgWidth_ > width_) {
                width_ = w_ + imgWidth_;
            }
            w_ = _parent.getFontMetrics(_parent.getFont()).stringWidth(Short.toString(level));
            if (w_ > deltaWidth_) {
                deltaWidth_ = w_;
            }
            if (w_ + imgWidth_ > width_) {
                width_ = w_ + imgWidth_;
            }
            w_ = _parent.getFontMetrics(_parent.getFont()).stringWidth(StringList.concat(percentExp.toNumberString(),PER_CENT));
            if (w_ > deltaWidth_) {
                deltaWidth_ = w_;
            }
            if (w_ + imgWidth_ > width_) {
                width_ = w_ + imgWidth_;
            }
            w_ = _parent.getFontMetrics(_parent.getFont()).stringWidth(StringList.concat(percentHp.toNumberString(),PER_CENT));
            if (w_ > deltaWidth_) {
                deltaWidth_ = w_;
            }
            if (w_ + imgWidth_ > width_) {
                width_ = w_ + imgWidth_;
            }
            w_ = deltaWidth_ + getWidthStatistic(_parent, _facade);
            if (w_ + imgWidth_ > width_) {
                width_ = w_ + imgWidth_;
            }
        }
        int height_ = _facade.getMaxHeightPk();
        int heightImage_ = _facade.getMaxHeightPk();
        int heightString_ = _parent.getFontMetrics(_parent.getFont()).getHeight();
        int h_ = heightString_;
        //h_ += getHeightStatistic(_facade);
//        if (!ball.isEmpty()) {
//            BufferedImage img_;
//            String b_ = _facade.getData().getMiniItems().getVal(ball);
//            img_ = ConverterBufferedImage.decodeToImage(b_);
//            height_ += img_.getHeight();
//        }
        int heightIni_ = height_;
        int headerHeight_ = CustList.SIZE_EMPTY;
        headerHeight_ += _parent.getFontMetrics(_parent.getFont()).getHeight();
        headerHeight_ += _parent.getFontMetrics(_parent.getFont()).getHeight();
        headerHeight_ += _parent.getFontMetrics(_parent.getFont()).getHeight();
        headerHeight_ += _parent.getFontMetrics(_parent.getFont()).getHeight();
        if (headerHeight_ > getHeightStatistic(_parent,_facade)) {
            height_ += headerHeight_;
        } else {
            height_ += getHeightStatistic(_parent,_facade);
        }
//        height_ += getFontMetrics(getFont()).getHeight();
//        height_ += getFontMetrics(getFont()).getHeight();
//        height_ += getFontMetrics(getFont()).getHeight();
//        height_ += getFontMetrics(getFont()).getHeight();
        //height_ += getHeightStatistic(_facade);
        int delta_ = height_ - heightIni_;
        if (!ball.isEmpty()) {
            BufferedImage img_;
            int[][] b_ = _facade.getData().getMiniItems().getVal(ball);
            img_ = ConverterBufferedImage.decodeToImage(b_);
            if (delta_ < img_.getHeight()) {
                delta_ = img_.getHeight();
                height_ = heightIni_ + img_.getHeight();
            }
        }
        if (fighterName.isEmpty()) {
            width_ = finalWidth;
            height_ = finalHeight;
            if (width_ == CustList.SIZE_EMPTY) {
                width_ = CustList.ONE_ELEMENT;
            }
            if (height_ == CustList.SIZE_EMPTY) {
                height_ = CustList.ONE_ELEMENT;
            }
        }
        image = new BufferedImage(width_, height_, BufferedImage.TYPE_INT_ARGB);
        if (!fighterName.isEmpty()) {
            int hMax_ = 0;
            int widthStatis_ = 0;
            for (BufferedImage i: statistics) {
                if (i.getHeight() > hMax_) {
                    hMax_ = i.getHeight();
                }
                widthStatis_ += i.getWidth();
            }
            Graphics2D g_ = image.createGraphics();
            g_.setColor(Color.WHITE);
            g_.fillRect(deltaWidth_, 0, widthStatis_, hMax_);
            int x_ = 0;
            for (BufferedImage i: statistics) {
                g_.drawImage(i, x_ + deltaWidth_, 0, null);
                x_ += i.getWidth();
            }
            BufferedImage image_;
            if (playerTeam) {
                image_ = ConverterBufferedImage.centerImage(data_.getMaxiPkBack().getVal(fighterName), widthImage_, heightImage_);
            } else {
                image_ = ConverterBufferedImage.centerImage(data_.getMaxiPkFront().getVal(fighterName), widthImage_, heightImage_);
            }
            g_.setColor(Color.BLACK);
            g_.drawString(fighterTranslatedName, 0, h_);
            if (!ball.isEmpty()) {
                BufferedImage img_;
                int[][] b_ = _facade.getData().getMiniItems().getVal(ball);
                img_ = ConverterBufferedImage.decodeToImage(b_);
                g_.drawImage(img_, width_ - img_.getWidth(), 0, null);
                //h_ += img_.getHeight();
            }
            h_ += heightString_;
            g_.drawString(Short.toString(level), 0, h_);
            h_ += heightString_;
            g_.setColor(Color.BLUE);
            g_.drawString(percentExp.toNumberString(), 0, h_);
            h_ += heightString_;
            int rate_ = Integer.parseInt(percentHp.toNumberString());
            int red_ = 255;
            int green_ = 255;
            green_ = green_ * rate_ / Rate.CENT;
            red_ = red_ * ((Rate.CENT - rate_) / Rate.CENT);
            g_.setColor(new Color(red_, green_, 0));
            g_.drawString(StringList.concat(percentHp.toNumberString(),PER_CENT), 0, h_);
            g_.drawImage(image_, 0, delta_, null);
            if (ko) {
                g_.setColor(Color.RED);
                g_.drawLine(0, h_, width_, height_);
                g_.drawLine(0, height_, width_, h_);
            }
            g_.dispose();
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

    public void set(JLabel _parent, boolean _playerTeam,FacadeGame _facade, String _name) {
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
//            g_.setColor(new Color(red_, green_, 0));
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

    public boolean isPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(boolean _playerTeam) {
        playerTeam = _playerTeam;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage _image) {
        image = _image;
    }

    public String getFighterName() {
        return fighterName;
    }

    public void setFighterName(String _fighterName) {
        fighterName = _fighterName;
    }

    public String getFighterTranslatedName() {
        return fighterTranslatedName;
    }

    public void setFighterTranslatedName(String _fighterTranslatedName) {
        fighterTranslatedName = _fighterTranslatedName;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
    }

    public int getFinalWidth() {
        return finalWidth;
    }

    public void setFinalWidth(int _finalWidth) {
        finalWidth = _finalWidth;
    }

    public int getFinalHeight() {
        return finalHeight;
    }

    public void setFinalHeight(int _finalHeight) {
        finalHeight = _finalHeight;
    }

    public boolean isKo() {
        return ko;
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

    public int getxPoint() {
        return xPoint;
    }

    public void setxPoint(int _xPoint) {
        xPoint = _xPoint;
    }

    public int getyPoint() {
        return yPoint;
    }

    public void setyPoint(int _yPoint) {
        yPoint = _yPoint;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String _ball) {
        ball = _ball;
    }

    public CustList<BufferedImage> getStatistics() {
        return statistics;
    }

    public void setStatistics(CustList<BufferedImage> _statistics) {
        statistics = _statistics;
    }
}
