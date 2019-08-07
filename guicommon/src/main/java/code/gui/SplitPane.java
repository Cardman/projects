package code.gui;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

public final class SplitPane extends CustComponent {

    private JSplitPane component;

    public SplitPane(int _orientation, CustComponent _left, CustComponent _right) {
        component = new JSplitPane(_orientation,_left.getComponent(),_right.getComponent());
        _left.setParent(this);
        getChildren().add(_left);
        _right.setParent(this);
        getChildren().add(_right);
    }

    @Override
    public JComponent getComponent() {
        return component;
    }

    public void setLeftComponent(CustComponent _scroll) {
        if (_scroll.getParent() != null) {
            return;
        }
        getChildren().first().setParent(null);
        _scroll.setParent(this);
        getChildren().set(0, _scroll);
    }

    public void setRightComponent(CustComponent _scroll) {
        if (_scroll.getParent() != null) {
            return;
        }
        getChildren().last().setParent(null);
        _scroll.setParent(this);
        getChildren().set(1, _scroll);
    }

    public void setAlignmentY(float _leftAlignment) {
        component.setAlignmentY(_leftAlignment);
    }

    public void setContinuousLayout(boolean _b) {
        component.setContinuousLayout(_b);
    }

    public void setOneTouchExpandable(boolean _b) {
        component.setOneTouchExpandable(_b);
    }

    public void setDividerLocation(int _i) {
        component.setDividerLocation(_i);
    }

}
