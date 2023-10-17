package aiki.gui.components.fight;



import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import code.gui.*;
import code.gui.images.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class FighterRenderer implements AbsCustCellRenderGene<Fighter> {

    private static final String CST_KO = "KO";
    private static final String PER_CENT = " %";

    private final int sideLength;

    private final FacadeGame facade;

    private Fighter fighter;

    private boolean selected;

    private final AbstractImageFactory fact;
    private AbstractImage pkImage;

    private LgInt intRate;

    private boolean ko;

    private boolean enabled;

    public FighterRenderer(AbstractImageFactory _fact, FacadeGame _facade) {
        fact = _fact;
        facade = _facade;
        sideLength = facade.getMap().getSideLength();
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, Fighter _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        fighter = _info;
        ko = fighter.estKo();
        intRate = fighter.rateRemainHp();
        selected = _isSelected;
        String name_ = fighter.getName();
        int[][] img_ = facade.getData().getMiniPk().getVal(name_);
        pkImage = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
        if (facade.getFight().getChoices().isEmpty()) {
            enabled = true;
        } else {
            enabled = facade.isChosableForLearningAndEvolving((byte) _index);
        }
        AbstractImage i_ = fact.newImageRgb(150, sideLength);
        paintComponent(i_);
        return i_;
    }

    public AbstractImageFactory getImageFactory() {
        return fact;
    }

    public void paintComponent(AbstractImage _g) {
        if (!enabled) {
            _g.setColor(GuiConstants.newColor(127, 127, 127));
            _g.fillRect(0, 0, 150 - 1, getHeight() -1);
        } else {
            _g.setColor(GuiConstants.WHITE);
            _g.fillRect(0, 0, 150 - 1, getHeight() -1);
        }
        _g.drawImage(pkImage, 0, 0);
        _g.setColor(GuiConstants.BLACK);
        String name_ = fighter.getName();
        _g.drawString(facade.translatePokemon(name_), sideLength, 10);
        if (ko) {
            _g.setColor(GuiConstants.BLACK);
            _g.drawString(CST_KO, sideLength, getHeight());
        } else {
            int rate_ = NumberUtil.parseInt(intRate.toNumberString());
            int red_ = 255;
            int green_ = 255;
            green_ = green_ * rate_ / Rate.CENT;
            red_ = red_ * ((Rate.CENT - rate_) / Rate.CENT);
            _g.setColor(GuiConstants.newColor(red_, green_, 0));
            _g.drawString(StringUtil.concat(intRate.toNumberString(),PER_CENT), sideLength, getHeight());
        }
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, 150 - 1, getHeight() - 1);
        }
    }

    public int getHeight() {
        return sideLength;
    }

}
