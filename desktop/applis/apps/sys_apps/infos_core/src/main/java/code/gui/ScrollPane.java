package code.gui;

import javax.swing.*;

public final class ScrollPane extends CustComponent implements AbsScrollPane {

    private final JScrollPane component;

    public ScrollPane(AbsCustComponent _center) {
        component = new JScrollPane(((CustComponent)_center).getNatComponent());
        _center.setParent(this);
        getChildren().add(_center);
    }

    public ScrollPane(AbsMetaLabel _center) {
        this(_center.getPaintableLabel());
    }
    ScrollPane(JScrollPane _scroll) {
        component = _scroll;
    }
    public ScrollPane() {
        component = new JScrollPane();
    }

    @Override
    protected JComponent getNatComponent() {
        return component;
    }

    public void setViewportView(AbsCustComponent _graphic) {
        component.setViewportView(((CustComponent)_graphic).getNatComponent());
        _graphic.setParent(this);
        FrameUtil.removeChild(getChildren());
        getChildren().add(_graphic);
    }

    public void setNullViewportView() {
        component.setViewportView(null);
        FrameUtil.removeChild(getChildren());
    }

    public int getHorizontalValue() {
        return component.getHorizontalScrollBar().getValue();
    }
    public void setHorizontalValue(int _value) {
        component.getHorizontalScrollBar().setValue(_value);
    }
    public int getVerticalValue() {
        return component.getVerticalScrollBar().getValue();
    }
    public void setVerticalValue(int _value) {
        component.getVerticalScrollBar().setValue(_value);
    }
}
