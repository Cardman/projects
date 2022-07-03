package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

public interface AbsPaintableLabel extends AbsCustComponent {
    void repaintLabel(AbstractImageFactory _fact);
    void requestFocusInWindow();
    void setEmptyIcon();
    void setIcon(AbstractImageFactory _fact, AbstractImage _icon);
    void setVerticalAlignment(int _alignment);
    void setHorizontalAlignment(int _alignment);
}
