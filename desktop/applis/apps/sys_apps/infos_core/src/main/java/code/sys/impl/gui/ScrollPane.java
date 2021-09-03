package code.sys.impl.gui;

import code.gui.AbsCustComponent;
import code.gui.AbsMetaLabel;
import code.gui.AbsScrollPane;
import code.gui.FrameUtil;
import code.gui.images.MetaRect;

import javax.swing.*;
import java.awt.*;

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
    public ScrollPane(JScrollPane _scroll) {
        component = _scroll;
    }
    public ScrollPane() {
        component = new JScrollPane();
    }

    @Override
    public JComponent getNatComponent() {
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

    @Override
    public MetaRect viewRect() {
        try {
            Rectangle viewRect_ = component.getViewport().getViewRect();
            Point location_ = viewRect_.getLocation();
            return new MetaRect(location_.x,location_.y,viewRect_.width,viewRect_.height);
        } catch (Exception e) {
            return new MetaRect(-1,-1,-1,-1);
        }

    }
}
