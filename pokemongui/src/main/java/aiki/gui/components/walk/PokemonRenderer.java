package aiki.gui.components.walk;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import code.gui.CustCellRender;
import code.gui.GraphicListable;
import code.gui.images.ConverterGraphicBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.Numbers;
import code.util.StringList;

public class PokemonRenderer extends CustCellRender {

    private static final String PER_CENT = " %";

    private static final String KO = "KO";

    private int sideLength;

    private FacadeGame facade;

    private int coords;

    private UsablePokemon pokemon;

    private Image miniImagePk;

    private String gender;

    private String remainHp;

    private String rateRemain;

    private boolean withItem;

    private BufferedImage miniImageItem;

    private boolean selected;

    private boolean oldSelected;

    private boolean single;

    private LgInt intRate;

    private boolean ko;

    private int remainSteps;

    public PokemonRenderer(FacadeGame _facade, boolean _single) {
        facade = _facade;
        single = _single;
        sideLength = facade.getMap().getSideLength();
    }

    public void setCoords(int _coords) {
        coords = _coords;
    }

    @Override
    public JLabel getListCellRendererComponent(
            GraphicListable _list, Object _arg1,
            int _index, boolean _selected, boolean _arg4) {
        JLabel label_ = (JLabel) _list.getListComponents().get(_index);
        pokemon = (UsablePokemon) _arg1;
        selected = _selected;
        if (pokemon instanceof PokemonPlayer) {
            PokemonPlayer pk_ = (PokemonPlayer) pokemon;
            int[][] img_ = facade.getData().getMiniPk().getVal(pk_.getName());
            miniImagePk = ConverterGraphicBufferedImage.decodeToImage(img_);
            remainHp = pk_.getRemainingHp().toNumberString();
            try {
                intRate = pk_.rateRemainHp(facade.getData());
                rateRemain = StringList.concat(intRate.toNumberString(),PER_CENT);
            } catch (RuntimeException _0) {
                rateRemain = DataBase.EMPTY_STRING;
            }
            gender = facade.translateGenders(pk_.getGender());
            withItem = !pk_.getItem().isEmpty();
            if (withItem) {
                img_ = facade.getData().getMiniItems().getVal(pk_.getItem());
                miniImageItem = ConverterGraphicBufferedImage.decodeToImage(img_);
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
            miniImagePk = ConverterGraphicBufferedImage.decodeToImage(img_);
            remainSteps = (int) (facade.getData().getPokemon(egg_.getName()).getHatchingSteps().ll() - egg_.getSteps());
        }
        label_.setPreferredSize(new Dimension(coords * 2 + sideLength * 2, sideLength));
        return label_;
    }

    @Override
    public void paintComponent(Graphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        if (pokemon instanceof PokemonPlayer) {
            PokemonPlayer pk_ = (PokemonPlayer) pokemon;
            _g.setColor(Color.BLACK);
            int h_ = 10;
            _g.drawImage(miniImagePk, 0, 0, null);
            _g.drawString(facade.translatePokemon(pk_.getName()), sideLength, h_);
            _g.drawString(Integer.toString(pk_.getLevel()), coords + sideLength, h_);
            _g.drawString(facade.translateAbility(pk_.getAbility()), sideLength, h_ * 2);
            _g.drawString(gender, coords + sideLength , h_ * 2);
            _g.drawString(remainHp, sideLength, h_ * 3);
            if (ko) {
                _g.setColor(Color.BLACK);
                _g.drawString(KO, coords + sideLength, h_ * 3);
            } else if (!rateRemain.isEmpty()) {
                int rate_ = Numbers.parseInt(intRate.toNumberString());
                int red_ = 255;
                int green_ = 255;
                green_ = green_ * rate_ / Rate.CENT;
                red_ = red_ * ((Rate.CENT - rate_) / Rate.CENT);
                _g.setColor(new Color(red_, green_, 0));
                _g.drawString(rateRemain, coords + sideLength, h_ * 3);
            }
            if (withItem) {
                _g.drawImage(miniImageItem, 2 * coords + sideLength, 0, null);
            }
        } else {
            Egg egg_ = (Egg) pokemon;
            int h_ = 10;
            _g.setColor(Color.BLACK);
            _g.drawImage(miniImagePk, 0, 0, null);
            _g.drawString(facade.translatePokemon(egg_.getName()), sideLength, h_);
            _g.drawString(Integer.toString(egg_.getSteps()), coords + sideLength, h_);
            _g.drawString(Integer.toString(remainSteps), coords + sideLength * 2, h_);
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
