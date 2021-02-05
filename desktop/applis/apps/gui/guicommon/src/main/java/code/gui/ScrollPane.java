package code.gui;

import javax.swing.*;

public final class ScrollPane extends CustComponent {

    private final JScrollPane component;

    public ScrollPane(CustComponent _center) {
        component = new JScrollPane(_center.getComponent());
        _center.setParent(this);
        getChildren().add(_center);
    }
    ScrollPane(JScrollPane _scroll) {
        component = _scroll;
    }
    public ScrollPane() {
        component = new JScrollPane();
    }

    @Override
    protected JComponent getComponent() {
        return component;
    }

    public void setViewportView(CustComponent _graphic) {
        component.setViewportView(_graphic.getComponent());
        _graphic.setParent(this);
        if (!getChildren().isEmpty()) {
            getChildren().first().setParent(null);
            getChildren().clear();
        }
        getChildren().add(_graphic);
    }

    public void setNullViewportView() {
        component.setViewportView(null);
        if (!getChildren().isEmpty()) {
            getChildren().first().setParent(null);
            getChildren().clear();
        }
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
