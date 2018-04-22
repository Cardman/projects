package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import aiki.DataBase;
import aiki.facade.FacadeGame;
import code.gui.PaintableLabel;
import code.gui.images.ConverterGraphicBufferedImage;

public class MiniTargetLabel extends PaintableLabel {

    private BufferedImage image;

    private boolean selectable;

    private boolean selected;

    private int index;

    public void set(FacadeGame _facade, String _name, int _index) {
        index = _index;
        DataBase data_ = _facade.getData();
        image = ConverterGraphicBufferedImage.decodeToImage(data_.getMiniPk().getVal(_name));
        setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
    }

    public void setSelected(int _index) {
        selected = index == _index;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    public void setSelectable(boolean _selectable) {
        selectable = _selectable;
    }

    @Override
    public void paintComponent(Graphics _g) {
        if (!selectable) {
            _g.setColor(Color.GRAY);
            _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        _g.drawImage(image, 0, 0, null);
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
