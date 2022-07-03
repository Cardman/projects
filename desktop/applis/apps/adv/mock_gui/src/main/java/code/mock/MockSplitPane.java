package code.mock;

import code.gui.AbsCustComponent;
import code.gui.AbsSplitPane;

public final class MockSplitPane extends MockCustComponent implements AbsSplitPane {
    private final boolean horizontal;
    private boolean continuousLayout;
    private boolean oneTouchExpandable;
    private int dividerLocation;
    private int dividerSize;

    public MockSplitPane(boolean _h,AbsCustComponent _left, AbsCustComponent _right) {
        this.horizontal = _h;
        procParents(_left, _right);
    }

    private void procParents(AbsCustComponent _left, AbsCustComponent _right) {
        _left.setParent(this);
        getChildren().add(_left);
        _right.setParent(this);
        getChildren().add(_right);
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    @Override
    public void setLeftComponent(AbsCustComponent _c) {
        if (_c.getParent() == null) {
            innerLeft(_c);
        }
    }

    @Override
    public void innerLeft(AbsCustComponent _c) {
        getChildren().first().setParent(null);
        _c.setParent(this);
        getChildren().set(0, _c);
    }

    @Override
    public void setRightComponent(AbsCustComponent _c) {
        if (_c.getParent() == null) {
            innerRight(_c);
        }
    }

    @Override
    public void innerRight(AbsCustComponent _c) {
        getChildren().last().setParent(null);
        _c.setParent(this);
        getChildren().set(1, _c);
    }

    @Override
    public boolean isContinuousLayout() {
        return continuousLayout;
    }

    @Override
    public void setContinuousLayout(boolean _b) {
        continuousLayout = _b;
    }

    @Override
    public boolean isOneTouchExpandable() {
        return oneTouchExpandable;
    }

    @Override
    public void setOneTouchExpandable(boolean _b) {
        oneTouchExpandable = _b;
    }

    @Override
    public int getDividerLocation() {
        return dividerLocation;
    }

    @Override
    public void setDividerLocation(int _i) {
        dividerLocation = _i;
    }

    @Override
    public int getDividerSize() {
        return dividerSize;
    }

    @Override
    public void setDividerSize(int _i) {
        dividerSize = _i;
    }
}
