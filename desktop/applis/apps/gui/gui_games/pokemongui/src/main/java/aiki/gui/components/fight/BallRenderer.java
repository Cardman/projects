package aiki.gui.components.fight;


import aiki.facade.FacadeGame;
import aiki.game.fight.BallNumberRate;
import aiki.gui.components.walk.IntTileRender;
import code.gui.AbsCustCellRenderGene;
import code.gui.ColorsGroupList;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class BallRenderer implements AbsCustCellRenderGene<BallNumberRate> {

    private static final String PERCENT = " %";

    private final FacadeGame facade;

    private boolean selected;

    private BallNumberRate ball;

    private final AbstractImageFactory fact;
    private AbstractImage ballImage;

//    private int maxWidthImage;

    private int maxWidthRate;

    private int maxWidthNumber;
    private final IntTileRender render;
    private int maxPercent;
    private int sideLength;

    public BallRenderer(AbstractImageFactory _fact, FacadeGame _facade, IntTileRender _tileRender) {
        fact = _fact;
        facade = _facade;
        render = _tileRender;
    }

    public void setMaxWidth(int _widthNb, int _widthRate, int _maxPerc) {
//        maxWidthImage = 0;
        maxWidthNumber = _widthNb;
        maxWidthRate = _widthRate;
        sideLength = facade.getMap().getSideLength();
        maxPercent = _maxPerc;
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, BallNumberRate _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        selected = _isSelected;
        ball = _info;
        int[][] img_ = facade.getData().getMiniItems().getVal(ball.getName());
//        ballImage = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
        ballImage = render.render(fact,img_,facade.getMap().getSideLength(),facade.getMap().getSideLength());
        int w_ = NumberUtil.max(100, sideLength+20+ maxWidthNumber+maxWidthRate+maxPercent);
        int h_ = NumberUtil.max(sideLength, _lab.getRealSize() + 2);
        AbstractImage i_ = fact.newImageRgb(w_, h_);
        i_.setFont(_lab);
        paintComponent(i_, w_);
        return i_;
    }

    public void paintComponent(AbstractImage _g, int _w) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, _w - 1, getHeight() - 1);
        _g.drawImage(ballImage, 0, 0);
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(ball.getNumber().toNumberString(), sideLength, ballImage.getHeight() - 2);
        MonteCarloNumber law_ = ball.getLaw();
        if (law_.nbEvents() == 1) {
            Rate ev_ = law_.getEvent(0);
            _g.drawString(ev_.toNumberString(), sideLength +10+ maxWidthNumber, ballImage.getHeight() - 2);
            _g.drawString(StringUtil.concat(ev_.percent().toNumberString(),PERCENT), sideLength +20+ maxWidthNumber+maxWidthRate, ballImage.getHeight() - 2);
        }
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, _w - 1, getHeight() - 1);
        }
    }

    public AbstractImageFactory getImageFactory() {
        return fact;
    }

    public int getHeight() {
        return ballImage.getHeight();
    }

}
