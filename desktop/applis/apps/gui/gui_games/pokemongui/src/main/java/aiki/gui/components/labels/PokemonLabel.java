package aiki.gui.components.labels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import aiki.facade.FacadeGame;
import aiki.map.pokemon.enums.Gender;
import aiki.util.SortingPokemonPlayer;
import code.gui.CustGraphics;
import code.gui.images.ConverterGraphicBufferedImage;
import code.util.EnumMap;
import code.util.Ints;
import code.util.core.StringUtil;

public class PokemonLabel extends SelectableLabel {

    private static final String SPACE = " ";

    private static final int FIRST_LINE = HEIGTH_CHARS;

    private static final int SECOND_LINE = FIRST_LINE + HEIGTH_CHARS;

    private int sideLength;

    private SortingPokemonPlayer pokemon;

    private BufferedImage miniImagePk;

    private BufferedImage miniImageItem;

    private boolean withItem;

    private int xName;

    private int thirdColumn;

    private String gender;

    public PokemonLabel(SortingPokemonPlayer _pokemon) {
        pokemon = _pokemon;
    }

    public void setImagesResults(FacadeGame _facade) {
        int[][] miniPk_ = _facade.getData().getMiniPk().getVal(pokemon.getKeyName());
        miniImagePk = ConverterGraphicBufferedImage.decodeToImage(miniPk_);
        withItem = !pokemon.getKeyItem().isEmpty();
        if (withItem) {
            int[][] miniItem_ = _facade.getData().getMiniItems().getVal(pokemon.getKeyItem());
            miniImageItem = ConverterGraphicBufferedImage.decodeToImage(miniItem_);
        }
        sideLength = _facade.getMap().getSideLength();
    }

    public void refresh(EnumMap<Gender,String> _tr) {
        gender = _tr.getVal(pokemon.getGender());
    }

    public SortingPokemonPlayer getPokemon() {
        return pokemon;
    }

    public void setNameCoord(int _xName, int _thirdColumn) {
        xName = _xName;
        thirdColumn = _thirdColumn;
        int h_ = sideLength;
        if (h_ < SECOND_LINE) {
            h_ = SECOND_LINE;
        }
        setPreferredSize(new Dimension(thirdColumn + xName + sideLength * 2,h_));
    }

    public int getThirdColumnWidth() {
        Ints widths_ = new Ints();
        widths_.add(getFontMetrics(getFont()).stringWidth(StringUtil.concat(Long.toString(pokemon.getLevel()),SPACE)));
        widths_.add(getFontMetrics(getFont()).stringWidth(StringUtil.concat(gender,SPACE)));
        return (int)widths_.getMaximum(1);
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.setColor(Color.BLACK);
        _g.drawImage(miniImagePk, 0, 0);
        _g.drawString(pokemon.getName(), sideLength, FIRST_LINE);
        _g.drawString(Long.toString(pokemon.getLevel()), xName + sideLength, FIRST_LINE);
        _g.drawString(pokemon.getAbility(), sideLength, SECOND_LINE);
        _g.drawString(gender, xName + sideLength, SECOND_LINE);
        //_g.drawString(pokemon.getItem(), xItem, h_);
        if (withItem) {
            _g.drawImage(miniImageItem, thirdColumn + xName + sideLength, 0);
        }
        super.paintComponent(_g);
    }
}
