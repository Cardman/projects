package code.gui;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

public final class SplitPane extends CustComponent {

    private JSplitPane component;

    public SplitPane(int _orientation, CustComponent _left, CustComponent _right) {
        if (getOrient(_orientation) == JSplitPane.VERTICAL_SPLIT) {
            component = new JSplitPane(JSplitPane.VERTICAL_SPLIT,_left.getComponent(),_right.getComponent());
        } else {
            component = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,_left.getComponent(),_right.getComponent());
        }
        _left.setParent(this);
        getChildren().add(_left);
        _right.setParent(this);
        getChildren().add(_right);
    }

    private static int getOrient(int _orientation) {
        if (_orientation == JSplitPane.VERTICAL_SPLIT) {
            return _orientation;
        }
        return JSplitPane.HORIZONTAL_SPLIT;
    }
    @Override
    protected JComponent getComponent() {
        return component;
    }

    public void setLeftComponent(CustComponent _scroll) {
        if (_scroll.getParent() != null) {
            return;
        }
        getChildren().first().setParent(null);
        _scroll.setParent(this);
        component.setLeftComponent(_scroll.getComponent());
        getChildren().set(0, _scroll);
    }

    public void setRightComponent(CustComponent _scroll) {
        if (_scroll.getParent() != null) {
            return;
        }
        getChildren().last().setParent(null);
        _scroll.setParent(this);
        component.setRightComponent(_scroll.getComponent());
        getChildren().set(1, _scroll);
    }

    public boolean isContinuousLayout() {
        return component.isContinuousLayout();
    }

    public void setContinuousLayout(boolean _b) {
        component.setContinuousLayout(_b);
    }

    public boolean isOneTouchExpandable() {
        return component.isOneTouchExpandable();
    }

    public void setOneTouchExpandable(boolean _b) {
        component.setOneTouchExpandable(_b);
    }

    public int getDividerLocation() {
        return component.getDividerLocation();
    }
    public void setDividerLocation(int _i) {
        component.setDividerLocation(_i);
    }

    public int getDividerSize() {
        return component.getDividerSize();
    }
    public void setDividerSize(int _i) {
        component.setDividerSize(_i);
    }

    public void revalidate() {
        component.revalidate();
    }
}
