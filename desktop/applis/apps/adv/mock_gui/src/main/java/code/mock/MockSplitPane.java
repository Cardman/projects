package code.mock;

import code.gui.AbsCustComponent;
import code.gui.AbsSplitPane;

public final class MockSplitPane extends MockCustComponent implements AbsSplitPane {
    private final boolean horizontal;
    private boolean continuousLayout;
    private boolean oneTouchExpandable;
    private int dividerLocation;
    private int dividerSize;
    private AbsCustComponent le;
    private AbsCustComponent ri;

    public MockSplitPane(boolean _h) {
        this.horizontal = _h;
    }

    public MockSplitPane(boolean _h,AbsCustComponent _left, AbsCustComponent _right) {
        this.horizontal = _h;
        le = _left;
        ri = _right;
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
    public void setLeftComponentNull() {
        removeLeft();
    }

//    @Override
    public void innerLeft(AbsCustComponent _c) {
        removeLeft();
        _c.setParent(this);
        le = _c;
        getChildren().add(0,le);
    }

    private void removeLeft() {
        if (le != null) {
            le.setParent(null);
            getChildren().removeObj(le);
        }
        le = null;
    }

    @Override
    public void setRightComponent(AbsCustComponent _c) {
        if (_c.getParent() == null) {
            innerRight(_c);
        }
    }

    @Override
    public void setRightComponentNull() {
        removeRight();
    }

    private void removeRight() {
        if (ri != null) {
            ri.setParent(null);
            getChildren().removeObj(ri);
        }
        ri = null;
    }

//    @Override
    public void innerRight(AbsCustComponent _c) {
        removeRight();
        _c.setParent(this);
        ri = _c;
        getChildren().add(ri);
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
