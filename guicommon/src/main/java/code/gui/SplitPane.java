package code.gui;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

public class SplitPane extends CustComponent {

    private JSplitPane component;

    public SplitPane(int _orientation) {
        component = new JSplitPane(_orientation);
    }

    public SplitPane(int _orientation, CustComponent _left, CustComponent _right) {
        component = new JSplitPane(_orientation);
        _left.setParent(this);
        getChildren().add(_left);
        _right.setParent(this);
        getChildren().add(_right);
    }

    public SplitPane() {
        component = new JSplitPane();
    }

    @Override
    public JComponent getComponent() {
        return component;
    }

    public void setLeftComponent(CustComponent _scroll) {
        getChildren().first().setParent(null);
        _scroll.setParent(this);
        getChildren().set(0, _scroll);
    }

    public void setRightComponent(CustComponent _scroll) {
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

    @Override
    public int getWidth() {
        return component.getWidth();
    }

    public void setDividerLocation(int _i) {
        component.setDividerLocation(_i);
    }
}
