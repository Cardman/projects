package aiki.gui.components.fight;

import java.awt.Dimension;

import aiki.facade.FacadeGame;
import aiki.game.fight.BallNumberRate;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.maths.Rate;
import code.util.NatStringTreeMap;
import code.util.core.StringUtil;

public class BallRenderer extends CustCellRender<BallNumberRate> {

    private static final String PERCENT = " %";

    private final FacadeGame facade;

    private boolean selected;

    private BallNumberRate ball;

    private final AbstractImageFactory fact;
    private AbstractImage ballImage;

    private int maxWidthImage;

    private int maxWidthRate;

    private int maxWidthNumber;

    public BallRenderer(AbstractImageFactory _fact, FacadeGame _facade) {
        fact = _fact;
        facade = _facade;
    }

    public void setMaxWidth(AbsCustComponent _fm,NatStringTreeMap<BallNumberRate> _balls) {
        maxWidthImage = 0;
        maxWidthRate = 0;
        maxWidthNumber = 0;
        for (BallNumberRate b: _balls.values()) {
            int[][] img_ = facade.getData().getMiniItems().getVal(b.getName());
            AbstractImage b_ = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
            Rate r_ = b.getRate();
            int w_ = _fm.stringWidth(r_.toNumberString());
            if (w_ > maxWidthRate) {
                maxWidthRate = w_;
            }
            w_ = _fm.stringWidth(b.getNumber().toNumberString());
            if (w_ > maxWidthNumber) {
                maxWidthNumber = w_;
            }
            w_ = b_.getWidth();
            if (w_ > maxWidthImage) {
                maxWidthImage = w_;
            }
        }
    }

    @Override
    public void getListCellRendererComponent(AbsPreparedLabel _currentLab, int _index,
                                             boolean _isSelected, boolean _cellHasFocus) {
        selected = _isSelected;
        ball = get(_index);
        int[][] img_ = facade.getData().getMiniItems().getVal(ball.getName());
        ballImage = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
        _currentLab.setPreferredSize(new Dimension(100, ballImage.getHeight()));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        _g.drawImage(ballImage, 0, 0);
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(ball.getNumber().toNumberString(), maxWidthImage, ballImage.getHeight());
        _g.drawString(ball.getRate().toNumberString(), maxWidthImage +10+ maxWidthNumber, ballImage.getHeight());
        _g.drawString(StringUtil.concat(ball.getPercent(),PERCENT), maxWidthImage +20+ maxWidthNumber+maxWidthRate, ballImage.getHeight());
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    @Override
    protected AbstractImageFactory getImageFactory() {
        return fact;
    }

    @Override
    public int getHeight() {
        return ballImage.getHeight();
    }

    @Override
    public int getWidth() {
        return 100;
    }
}
