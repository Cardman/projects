package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import code.gui.CommonCellRenderer;
import code.images.ConverterBufferedImage;
import code.maths.Rate;
import code.util.NatTreeMap;
import aiki.facade.FacadeGame;
import aiki.game.fight.BallNumberRate;

public class BallRenderer extends CommonCellRenderer {

    private static final String PERCENT = " %";

    private FacadeGame facade;

    private boolean selected;

    private BallNumberRate ball;

    private BufferedImage ballImage;

    private int maxWidthImage;

    private int maxWidthRate;

    private int maxWidthNumber;

    public BallRenderer(FacadeGame _facade) {
        facade = _facade;
    }

    public void setMaxWidth(NatTreeMap<String,BallNumberRate> _balls) {
        maxWidthImage = 0;
        maxWidthRate = 0;
        maxWidthNumber = 0;
        for (BallNumberRate b: _balls.values()) {
            String img_ = facade.getData().getMiniItems().getVal(b.getName());
            BufferedImage b_ = ConverterBufferedImage.decodeToImage(img_);
            Rate r_ = b.getRate();
            int w_ = getFontMetrics(getFont()).stringWidth(r_.toString());
            if (w_ > maxWidthRate) {
                maxWidthRate = w_;
            }
            w_ = getFontMetrics(getFont()).stringWidth(b.getNumber().toString());
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
    public Component getListCellRendererComponent(Object _value, int _index,
            boolean _isSelected, boolean _cellHasFocus) {
        selected = _isSelected;
        ball = (BallNumberRate)_value;
        String img_ = facade.getData().getMiniItems().getVal(ball.getName());
        ballImage = ConverterBufferedImage.decodeToImage(img_);
        setPreferredSize(new Dimension(100, ballImage.getHeight()));
        return this;
    }

    @Override
    protected void paintComponent(Graphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        _g.drawImage(ballImage, 0, 0, null);
        _g.setColor(Color.BLACK);
        _g.drawString(ball.getNumber().toString(), maxWidthImage, ballImage.getHeight());
        _g.drawString(ball.getRate().toString(), maxWidthImage +10+ maxWidthNumber, ballImage.getHeight());
        _g.drawString(ball.getPercent()+PERCENT, maxWidthImage +20+ maxWidthNumber+maxWidthRate, ballImage.getHeight());
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
