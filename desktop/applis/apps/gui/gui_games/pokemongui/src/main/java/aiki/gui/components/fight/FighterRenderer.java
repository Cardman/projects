package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Dimension;

import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class FighterRenderer extends CustCellRender<Fighter> {

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
    public void getListCellRendererComponent(AbsPreparedLabel _currentLab, int _index,
                                             boolean _isSelected, boolean _cellHasFocus) {
        fighter = get(_index);
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
        _currentLab.setPreferredSize(new Dimension(150, sideLength));
    }

    @Override
    protected AbstractImageFactory getImageFactory() {
        return fact;
    }
    @Override
    public void paintComponent(AbstractImage _g) {
        if (!enabled) {
            _g.setColor(new Color(127, 127, 127));
            _g.fillRect(0, 0, getWidth() - 1, getHeight() -1);
        } else {
            _g.setColor(Color.WHITE);
            _g.fillRect(0, 0, getWidth() - 1, getHeight() -1);
        }
        _g.drawImage(pkImage, 0, 0);
        _g.setColor(Color.BLACK);
        String name_ = fighter.getName();
        _g.drawString(facade.translatePokemon(name_), sideLength, 10);
        if (ko) {
            _g.setColor(Color.BLACK);
            _g.drawString(CST_KO, sideLength, getHeight());
        } else {
            int rate_ = NumberUtil.parseInt(intRate.toNumberString());
            int red_ = 255;
            int green_ = 255;
            green_ = green_ * rate_ / Rate.CENT;
            red_ = red_ * ((Rate.CENT - rate_) / Rate.CENT);
            _g.setColor(new Color(red_, green_, 0));
            _g.drawString(StringUtil.concat(intRate.toNumberString(),PER_CENT), sideLength, getHeight());
        }
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    @Override
    public int getHeight() {
        return sideLength;
    }

    @Override
    public int getWidth() {
        return 150;
    }
}
