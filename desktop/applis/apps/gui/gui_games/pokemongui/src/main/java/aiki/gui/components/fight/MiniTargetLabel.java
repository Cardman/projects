package aiki.gui.components.fight;



import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.gui.components.AbsMetaLabelPk;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;

public final class MiniTargetLabel extends AbsMetaLabelPk {

    private AbstractImage image;

    private boolean selectable;

    private boolean selected;

    private final byte selectedTarget;

    public MiniTargetLabel(AbsCompoFactory _compoFactory, byte _key) {
        super(_compoFactory);
        selectedTarget = _key;
    }

    public void set(FacadeGame _facade, Battle _battle, String _name) {
        DataBase data_ = _facade.getData();
//        image = ConverterGraphicBufferedImage.decodeToImage(_battle.getWindow().getImageFactory(), data_.getMiniPk().getVal(_name));
        image = _battle.getWindow().getTileRender().render(_battle.getImageFactory(), data_.getMiniPk().getVal(_name), data_.getMap().getSideLength(), data_.getMap().getSideLength());
        setPreferredSize(new MetaDimension(image.getWidth(),image.getHeight()));
    }

    public void setSelectedKey(int _index) {
        selected = selectedTarget == _index;
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
            _g.setColor(GuiConstants.GRAY);
        } else {
            _g.setColor(GuiConstants.WHITE);
        }
        _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        _g.drawImage(image, 0, 0);
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
