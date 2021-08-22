package aiki.gui.components.walk;
import java.awt.Color;
import java.awt.Dimension;

import aiki.facade.FacadeGame;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class PokemonRenderer extends CustCellRender<UsablePokemon> {

    private static final String PER_CENT = " %";

    private static final String CST_KO = "KO";

    private final int sideLength;

    private final FacadeGame facade;

    private int coords;

    private UsablePokemon pokemon;

    private AbstractImage miniImagePk;

    private String gender;

    private String remainHp;

    private String rateRemain;

    private boolean withItem;

    private AbstractImage miniImageItem;

    private boolean selected;

    private boolean oldSelected;

    private final boolean single;

    private LgInt intRate;

    private boolean ko;

    private int remainSteps;
    private AbstractImageFactory fact;

    public PokemonRenderer(AbstractImageFactory _fact,FacadeGame _facade, boolean _single) {
        fact = _fact;
        facade = _facade;
        single = _single;
        sideLength = facade.getMap().getSideLength();
    }

    public void setCoords(int _coords) {
        coords = _coords;
    }

    @Override
    public void getListCellRendererComponent(
            PreparedLabel _currentLab,
            int _index, boolean _selected, boolean _arg4) {
        pokemon = getList().get(_index);
        selected = _selected;
        if (pokemon instanceof PokemonPlayer) {
            PokemonPlayer pk_ = (PokemonPlayer) pokemon;
            int[][] img_ = facade.getData().getMiniPk().getVal(pk_.getName());
            miniImagePk = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
            remainHp = pk_.getRemainingHp().toNumberString();
            intRate = pk_.rateRemainHp(facade.getData());
            rateRemain = StringUtil.concat(intRate.toNumberString(),PER_CENT);
            gender = facade.translateGenders(pk_.getGender());
            withItem = !pk_.getItem().isEmpty();
            if (withItem) {
                img_ = facade.getData().getMiniItems().getVal(pk_.getItem());
                miniImageItem = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
            }
            oldSelected = false;
            ko = pk_.isKo();
            if (!single) {
                if (facade.getSecondSelectPkToHost() == _index) {
                    oldSelected = true;
                }
                if (facade.getFirstSelectPkToHost() == _index) {
                    oldSelected = true;
                }
            }
        } else {
            Egg egg_ = (Egg) pokemon;
            int[][] img_ = facade.getData().getMiniPk().getVal(egg_.getName());
            miniImagePk = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
            remainSteps = (int) (facade.getData().getPokemon(egg_.getName()).getHatchingSteps().ll() - egg_.getSteps());
        }
        _currentLab.setPreferredSize(new Dimension(coords * 2 + sideLength * 2, sideLength));
    }

    @Override
    protected AbstractImageFactory getImageFactory() {
        return fact;
    }
    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        if (pokemon instanceof PokemonPlayer) {
            PokemonPlayer pk_ = (PokemonPlayer) pokemon;
            _g.setColor(Color.BLACK);
            int h_ = 10;
            _g.drawImage(miniImagePk, 0, 0);
            _g.drawString(facade.translatePokemon(pk_.getName()), sideLength, h_);
            _g.drawString(Long.toString(pk_.getLevel()), coords + sideLength, h_);
            _g.drawString(facade.translateAbility(pk_.getAbility()), sideLength, h_ * 2);
            _g.drawString(gender, coords + sideLength , h_ * 2);
            _g.drawString(remainHp, sideLength, h_ * 3);
            if (ko) {
                _g.setColor(Color.BLACK);
                _g.drawString(CST_KO, coords + sideLength, h_ * 3);
            } else if (!rateRemain.isEmpty()) {
                int rate_ = NumberUtil.parseInt(intRate.toNumberString());
                int red_ = 255;
                int green_ = 255;
                green_ = green_ * rate_ / Rate.CENT;
                red_ = red_ * ((Rate.CENT - rate_) / Rate.CENT);
                _g.setColor(new Color(red_, green_, 0));
                _g.drawString(rateRemain, coords + sideLength, h_ * 3);
            }
            if (withItem) {
                _g.drawImage(miniImageItem, 2 * coords + sideLength, 0);
            }
        } else {
            Egg egg_ = (Egg) pokemon;
            int h_ = 10;
            _g.setColor(Color.BLACK);
            _g.drawImage(miniImagePk, 0, 0);
            _g.drawString(facade.translatePokemon(egg_.getName()), sideLength, h_);
            _g.drawString(Long.toString(egg_.getSteps()), coords + sideLength, h_);
            _g.drawString(Long.toString(remainSteps), coords + sideLength * 2, h_);
        }
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        } else if (oldSelected) {
            _g.setColor(Color.RED);
            _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        }
    }

    @Override
    public int getHeight() {
        return sideLength;
    }

    @Override
    public int getWidth() {
        return coords * 2 + sideLength * 2;
    }
}
