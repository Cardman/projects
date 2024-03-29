package code.vi.prot.impl.gui;

import code.gui.AbsCustComponent;
import code.gui.AbsSplitPane;
import code.gui.FrameUtil;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

public final class SplitPane extends CustComponent implements AbsSplitPane {

    private final JSplitPane component;

    private SplitPane(JSplitPane _component, AbsCustComponent _left, AbsCustComponent _right) {
        component = _component;
        procParents(_left, _right);
    }
    public static SplitPane vertical(AbsCustComponent _left, AbsCustComponent _right) {
        return new SplitPane(new JSplitPane(JSplitPane.VERTICAL_SPLIT, ((CustComponent)_left).getNatComponent(), ((CustComponent)_right).getNatComponent()),_left,_right);
    }
    public static SplitPane horizontal(AbsCustComponent _left, AbsCustComponent _right) {
        return new SplitPane(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, ((CustComponent)_left).getNatComponent(), ((CustComponent)_right).getNatComponent()),_left,_right);
    }

    private void procParents(AbsCustComponent _left, AbsCustComponent _right) {
        _left.setParent(this);
        getChildren().add(_left);
        _right.setParent(this);
        getChildren().add(_right);
    }

    @Override
    public JComponent getNatComponent() {
        return component;
    }

    public void setLeftComponent(AbsCustComponent _scroll) {
        FrameUtil.left(_scroll,this);
    }

    public void innerLeft(AbsCustComponent _scroll) {
        getChildren().first().setParent(null);
        _scroll.setParent(this);
        component.setLeftComponent(((CustComponent) _scroll).getNatComponent());
        getChildren().set(0, _scroll);
    }

    public void setRightComponent(AbsCustComponent _scroll) {
        FrameUtil.right(_scroll,this);
    }

    public void innerRight(AbsCustComponent _scroll) {
        getChildren().last().setParent(null);
        _scroll.setParent(this);
        component.setRightComponent(((CustComponent) _scroll).getNatComponent());
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

}
