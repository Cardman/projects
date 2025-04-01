package code.vi.prot.impl.gui;

import code.gui.AbsCustComponent;
import code.gui.AbsSplitPane;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

public final class SplitPane extends CustComponent implements AbsSplitPane {

    private final JSplitPane component;

//    private SplitPane(JSplitPane _component, AbsCustComponent _left, AbsCustComponent _right) {
//        component = _component;
////        procParents(_left, _right);
//    }

    public SplitPane(JSplitPane _component) {
        component = _component;
    }
    public static SplitPane vertical() {
        JSplitPane sp_ = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp_.setLeftComponent(null);
        sp_.setRightComponent(null);
        return new SplitPane(sp_);
    }
    public static SplitPane verticalLeft(AbsCustComponent _left) {
        JSplitPane sp_ = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp_.setLeftComponent(((CustComponent) _left).getNatComponent());
        sp_.setRightComponent(null);
        return new SplitPane(sp_);
    }
    public static SplitPane verticalRight(AbsCustComponent _right) {
        JSplitPane sp_ = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp_.setLeftComponent(null);
        sp_.setRightComponent(((CustComponent) _right).getNatComponent());
        return new SplitPane(sp_);
    }
    public static SplitPane vertical(AbsCustComponent _left, AbsCustComponent _right) {
        return new SplitPane(new JSplitPane(JSplitPane.VERTICAL_SPLIT, ((CustComponent)_left).getNatComponent(), ((CustComponent)_right).getNatComponent()));
    }
    public static SplitPane horizontal() {
        JSplitPane sp_ = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp_.setLeftComponent(null);
        sp_.setRightComponent(null);
        return new SplitPane(sp_);
    }
    public static SplitPane horizontalLeft(AbsCustComponent _left) {
        JSplitPane sp_ = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp_.setLeftComponent(((CustComponent) _left).getNatComponent());
        sp_.setRightComponent(null);
        return new SplitPane(sp_);
    }
    public static SplitPane horizontalRight(AbsCustComponent _right) {
        JSplitPane sp_ = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp_.setLeftComponent(null);
        sp_.setRightComponent(((CustComponent) _right).getNatComponent());
        return new SplitPane(sp_);
    }
    public static SplitPane horizontal(AbsCustComponent _left, AbsCustComponent _right) {
        return new SplitPane(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, ((CustComponent)_left).getNatComponent(), ((CustComponent)_right).getNatComponent()));
    }

//    private void procParents(AbsCustComponent _left, AbsCustComponent _right) {
//        _left.setParent(this);
//        getChildren().add(_left);
//        _right.setParent(this);
//        getChildren().add(_right);
//    }

    @Override
    public JComponent getNatComponent() {
        return component;
    }

    public void setLeftComponent(AbsCustComponent _scroll) {
//        FrameUtil.left(_scroll,this);
        component.setLeftComponent(((CustComponent) _scroll).getNatComponent());
//        getChildren().set(0, _scroll);
    }

//    public void innerLeft(AbsCustComponent _scroll) {
//        getChildren().first().setParent(null);
//        _scroll.setParent(this);
//        component.setLeftComponent(((CustComponent) _scroll).getNatComponent());
//        getChildren().set(0, _scroll);
//    }

    @Override
    public void setLeftComponentNull() {
        component.setLeftComponent(null);
    }

    public void setRightComponent(AbsCustComponent _scroll) {
//        FrameUtil.right(_scroll,this);
        component.setRightComponent(((CustComponent) _scroll).getNatComponent());
//        getChildren().set(1, _scroll);
    }

//    public void innerRight(AbsCustComponent _scroll) {
//        getChildren().last().setParent(null);
//        _scroll.setParent(this);
//        component.setRightComponent(((CustComponent) _scroll).getNatComponent());
//        getChildren().set(1, _scroll);
//    }

    @Override
    public void setRightComponentNull() {
        component.setRightComponent(null);
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
