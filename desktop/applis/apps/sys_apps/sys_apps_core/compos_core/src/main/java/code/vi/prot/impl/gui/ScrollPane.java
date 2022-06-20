package code.vi.prot.impl.gui;

import code.gui.*;
import code.gui.images.MetaRect;

import javax.swing.*;
import java.awt.*;

public final class ScrollPane extends CustComponent implements AbsScrollPane {

    private final JScrollPane component;

    public ScrollPane(AbsCustComponent _center) {
        component = new JScrollPane(((CustComponent)_center).getNatComponent());
        setPar(_center);
        getChildren().add(_center);
    }

    public ScrollPane(AbsMetaLabelComInt _center) {
        this(_center.getPaintableLabel());
    }
    public ScrollPane(JScrollPane _scroll) {
        component = _scroll;
    }
    public ScrollPane() {
        component = new JScrollPane();
    }

    private void setPar(AbsCustComponent _center) {
        _center.setParent(this);
    }

    @Override
    public JComponent getNatComponent() {
        return component;
    }

    public void setViewportView(AbsCustComponent _graphic) {
        component.setViewportView(((CustComponent)_graphic).getNatComponent());
        setPar(_graphic);
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
    public void recalculateViewport() {
        component.getViewport().invalidate();
        component.getViewport().doLayout();
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
