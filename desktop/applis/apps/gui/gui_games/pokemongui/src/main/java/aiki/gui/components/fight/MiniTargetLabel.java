package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Dimension;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import code.gui.AbsMetaLabel;
import code.gui.images.AbstractImage;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.AbsCompoFactory;

public final class MiniTargetLabel extends AbsMetaLabel {

    private AbstractImage image;

    private boolean selectable;

    private boolean selected;

    private int index;

    public MiniTargetLabel(AbsCompoFactory _compoFactory) {
        super(_compoFactory);
    }

    public void set(FacadeGame _facade, Battle _battle, String _name, int _index) {
        index = _index;
        DataBase data_ = _facade.getData();
        image = ConverterGraphicBufferedImage.decodeToImage(_battle.getWindow().getImageFactory(), data_.getMiniPk().getVal(_name));
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
    public void paintComponent(AbstractImage _g) {
        if (!selectable) {
            _g.setColor(Color.GRAY);
            _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        _g.drawImage(image, 0, 0);
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
