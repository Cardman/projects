package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import aiki.DataBase;
import aiki.facade.FacadeGame;
import code.gui.CustCellRender;
import code.gui.GraphicListable;
import code.gui.images.ConverterGraphicBufferedImage;

public class PokemonDataRenderer extends CustCellRender {

    private int sideLength;
    private int height;

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
    public JLabel getListCellRendererComponent(GraphicListable _list, Object _value,
            int _index,
            boolean _isSelected, boolean _cellHasFocus) {
        JLabel label_ = (JLabel) _list.getListComponents().get(_index);
        selected = _isSelected;
        String key_ = (String) _value;
        if (!key_.isEmpty()) {
            name = facade.translatePokemon(key_);
            int[][] img_ = facade.getData().getMiniPk().getVal(key_);
            pkImage = ConverterGraphicBufferedImage.decodeToImage(img_);
            height = pkImage.getHeight();
        } else {
            name = DataBase.EMPTY_STRING;
            pkImage = null;
            height = sideLength;
        }
        label_.setPreferredSize(new Dimension(100, height));
        return label_;
    }

    @Override
    public void paintComponent(Graphics _g) {
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

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return 100;
    }
}
