package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

public interface AbsPreparedLabel extends AbsCustComponent {
    void setIcon(AbstractImageFactory _fact, AbstractImage _icon);
}
