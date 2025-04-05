package code.gui;

import code.gui.images.MetaRect;

public interface AbsScrollPane extends AbsCustComponent {
    void setViewportView(AbsCustComponent _graphic);
    void setNullViewportView();
    int getHorizontalValue();
    void setHorizontalValue(int _value);
    int getVerticalValue();
    void setVerticalValue(int _value);
    void recalculateViewport();
    MetaRect viewRect();
}
