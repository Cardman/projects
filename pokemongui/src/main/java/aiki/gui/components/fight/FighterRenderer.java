package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import code.gui.CommonCellRenderer;
import code.images.ConverterBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;

public class FighterRenderer extends CommonCellRenderer {

    private static final String KO = "KO";
    private static final String PER_CENT = " %";

    private static int _sideLength_;

    private FacadeGame facade;

    private Fighter fighter;

    private boolean selected;

    private BufferedImage pkImage;

    private LgInt intRate;

    private boolean ko;

    private boolean enabled;

    public FighterRenderer(FacadeGame _facade) {
        facade = _facade;
        _sideLength_ = facade.getMap().getSideLength();
    }

    @Override
    public Component getListCellRendererComponent(Object _value, int _index,
            boolean _isSelected, boolean _cellHasFocus) {
        fighter = (Fighter) _value;
        ko = fighter.estKo();
        intRate = fighter.rateRemainHp();
        selected = _isSelected;
        String name_ = fighter.getName();
        String img_ = facade.getData().getMiniPk().getVal(name_);
        pkImage = ConverterBufferedImage.decodeToImage(img_);
        if (facade.getFight().getChoices().isEmpty()) {
            enabled = true;
        } else {
            enabled = facade.isChosableForLearningAndEvolving((byte) _index);
        }
        setPreferredSize(new Dimension(150, _sideLength_));
        return this;
    }

    @Override
    protected void paintComponent(Graphics _g) {
        if (!enabled) {
            _g.setColor(new Color(225, 225, 225));
            _g.fillRect(0, 0, getWidth() - 1, getHeight() -1);
        }
        _g.drawImage(pkImage, 0, 0, null);
        _g.setColor(Color.BLACK);
        String name_ = fighter.getName();
        _g.drawString(facade.translatePokemon(name_), _sideLength_, 10);
        if (ko) {
            _g.setColor(Color.BLACK);
            _g.drawString(KO, _sideLength_, getHeight());
        } else {
            int rate_ = Integer.parseInt(intRate.toNumberString());
            int red_ = 255;
            int green_ = 255;
            green_ = green_ * rate_ / Rate.CENT;
            red_ = red_ * ((Rate.CENT - rate_) / Rate.CENT);
            _g.setColor(new Color(red_, green_, 0));
            _g.drawString(StringList.concat(intRate.toNumberString(),PER_CENT), _sideLength_, getHeight());
        }
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
