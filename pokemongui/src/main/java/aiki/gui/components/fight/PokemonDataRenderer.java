package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import aiki.DataBase;
import aiki.facade.FacadeGame;
import code.gui.StringCellRenderer;
import code.images.ConverterBufferedImage;

public class PokemonDataRenderer extends StringCellRenderer {

    private int sideLength;

    private FacadeGame facade;

    private String noEvo;

    private String name;

    private boolean selected;

    private BufferedImage pkImage;

    public PokemonDataRenderer(FacadeGame _facade, String _noEvo) {
        facade = _facade;
        sideLength = facade.getMap().getSideLength();
        noEvo = _noEvo;
    }

    public void setNoEvo(String _noEvo) {
        noEvo = _noEvo;
    }

    @Override
    public Component getListCellRendererComponent(String _value,
            int _index,
            boolean _isSelected, boolean _cellHasFocus) {
        selected = _isSelected;
        if (!_value.isEmpty()) {
            name = facade.translatePokemon(_value);
            String img_ = facade.getData().getMiniPk().getVal(_value);
            pkImage = ConverterBufferedImage.decodeToImage(img_);
            setPreferredSize(new Dimension(100, pkImage.getHeight()));
        } else {
            name = DataBase.EMPTY_STRING;
            pkImage = null;
            setPreferredSize(new Dimension(100, sideLength));
        }
        return this;
    }

    @Override
    protected void paintComponent(Graphics _g) {
        if (!name.isEmpty()) {
            _g.drawImage(pkImage, 0, 0, null);
            _g.drawString(name, sideLength, getHeight());
        } else {
            _g.setColor(Color.WHITE);
            _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            _g.setColor(Color.BLACK);
            _g.drawString(noEvo, 0, getHeight());
        }
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
