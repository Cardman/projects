package aiki.gui.components.fight;


import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import aiki.game.fight.FighterPosition;
import aiki.gui.components.walk.IntTileRender;
import code.gui.AbsCustCellRenderGene;
import code.gui.ColorsGroupList;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class FighterRenderer implements AbsCustCellRenderGene<FighterPosition> {

    private static final String CST_KO = "KO";
    private static final String PER_CENT = " %";

    private int sideLength;

    private final FacadeGame facade;

    private Fighter fighter;

    private boolean selected;

    private final AbstractImageFactory fact;
    private AbstractImage pkImage;

    private LgInt intRate;

    private boolean ko;

    private boolean enabled;
    private final IntTileRender render;
    private int maxWidthName;
    private int maxWidthHealthPointsRate;

    public FighterRenderer(AbstractImageFactory _fact, FacadeGame _facade, IntTileRender _tileRender) {
        fact = _fact;
        facade = _facade;
        render = _tileRender;
    }

    public void initWidth(int _m, int _p) {
        sideLength = facade.getMap().getSideLength();
        maxWidthName = _m;
        maxWidthHealthPointsRate = _p;
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, FighterPosition _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        fighter = _info.getFighter();
        ko = fighter.estKo();
        intRate = fighter.rateRemainHp();
        selected = _isSelected;
        String name_ = fighter.getName();
        int[][] img_ = facade.getData().getMiniPk().getVal(name_);
//        pkImage = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
        pkImage = render.render(fact,img_,sideLength,sideLength);
        if (facade.getFight().getChoices().isEmpty()) {
            enabled = true;
        } else {
            enabled = facade.isChosableForLearningAndEvolving((byte) _index);
        }
        int w_ = NumberUtil.max(150,sideLength+maxWidthName+maxWidthHealthPointsRate);
        int h_ = NumberUtil.max(sideLength, 2 * _lab.getRealSize() + 2);
        AbstractImage i_ = fact.newImageRgb(w_, h_);
        i_.setFont(_lab);
        paintComponent(i_, w_, _lab);
        return i_;
    }

    public AbstractImageFactory getImageFactory() {
        return fact;
    }

    public void paintComponent(AbstractImage _g, int _w, MetaFont _lab) {
        if (!enabled) {
            _g.setColor(GuiConstants.newColor(127, 127, 127));
            _g.fillRect(0, 0, _w - 1, getHeight() -1);
        } else {
            _g.setColor(GuiConstants.WHITE);
            _g.fillRect(0, 0, _w - 1, getHeight() -1);
        }
        _g.drawImage(pkImage, 0, 0);
        _g.setColor(GuiConstants.BLACK);
        String name_ = fighter.getName();
        _g.drawString(facade.translatePokemon(name_), sideLength, _lab.getRealSize());
        if (ko) {
            _g.setColor(GuiConstants.BLACK);
            _g.drawString(CST_KO, sideLength+maxWidthName, getHeight() - 2);
        } else {
            int rate_ = NumberUtil.parseInt(intRate.toNumberString());
            int red_ = 255;
            int green_ = 255;
            green_ = green_ * rate_ / Rate.CENT;
            red_ = red_ * ((Rate.CENT - rate_) / Rate.CENT);
            _g.setColor(GuiConstants.newColor(red_, green_, 0));
            _g.drawString(StringUtil.concat(intRate.toNumberString(),PER_CENT), sideLength+maxWidthName, getHeight() - 2);
        }
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, _w - 1, getHeight() - 1);
        }
    }

    public int getHeight() {
        return sideLength;
    }

}
