package code.gui;

import javax.swing.*;

public final class ScrollPane extends CustComponent {

    private JScrollPane component;

    public ScrollPane(CustComponent _center) {
        component = new JScrollPane(_center.getComponent());
        _center.setParent(this);
        getChildren().add(_center);
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
    public void validate() {
        component.validate();
    }

    public ScrollBar getHorizontalScrollBar() {
        return new ScrollBar(component.getHorizontalScrollBar());
    }

    public ScrollBar getVerticalScrollBar() {
        return new ScrollBar(component.getVerticalScrollBar());
    }

    public void revalidate() {
        component.revalidate();
    }
}
