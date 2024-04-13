package aiki.gui.components.fight;



import aiki.facade.FacadeGame;
import aiki.game.fight.BallNumberRate;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.AbsCompoFactory;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.NatStringTreeMap;
import code.util.core.StringUtil;

public class BallRenderer implements AbsCustCellRenderGene<BallNumberRate> {

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

    public void setMaxWidth(AbsCustComponent _fm, NatStringTreeMap<BallNumberRate> _balls, AbsCompoFactory _compoFactory) {
        maxWidthImage = 0;
        maxWidthRate = 0;
        maxWidthNumber = 0;
        for (BallNumberRate b: _balls.values()) {
            int[][] img_ = facade.getData().getMiniItems().getVal(b.getName());
            AbstractImage b_ = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
            MonteCarloNumber law_ = b.getLaw();
            int wEv_;
            if (law_.nbEvents() == 1) {
                Rate ev_ = law_.getEvent(0);
                wEv_ = _compoFactory.stringWidth(_fm.getMetaFont(),ev_.toNumberString());
            } else {
                wEv_ = 0;
            }
            int w_ = wEv_;
            if (w_ > maxWidthRate) {
                maxWidthRate = w_;
            }
            w_ = _compoFactory.stringWidth(_fm.getMetaFont(),b.getNumber().toNumberString());
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
    public AbstractImage getListCellRendererComponent(int _index, BallNumberRate _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        selected = _isSelected;
        ball = _info;
        int[][] img_ = facade.getData().getMiniItems().getVal(ball.getName());
        ballImage = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
        AbstractImage i_ = fact.newImageRgb(100, ballImage.getHeight());
        paintComponent(i_);
        return i_;
    }

    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, 100 - 1, getHeight() - 1);
        _g.drawImage(ballImage, 0, 0);
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(ball.getNumber().toNumberString(), maxWidthImage, ballImage.getHeight());
        MonteCarloNumber law_ = ball.getLaw();
        if (law_.nbEvents() == 1) {
            Rate ev_ = law_.getEvent(0);
            _g.drawString(ev_.toNumberString(), maxWidthImage +10+ maxWidthNumber, ballImage.getHeight());
            _g.drawString(StringUtil.concat(ev_.percent().toNumberString(),PERCENT), maxWidthImage +20+ maxWidthNumber+maxWidthRate, ballImage.getHeight());
        }
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, 100 - 1, getHeight() - 1);
        }
    }

    public AbstractImageFactory getImageFactory() {
        return fact;
    }

    public int getHeight() {
        return ballImage.getHeight();
    }

}
